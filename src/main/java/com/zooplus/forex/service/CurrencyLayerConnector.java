package com.zooplus.forex.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CurrencyLayerConnector {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String ACCESS_KEY = "fce3bea21fef3d3ca2a114df213a7031";
    private static final String REQUEST_URL_PATTERN = "http://apilayer.net/api/historical?access_key=%s&date=%s";
    private static final Logger log = LoggerFactory.getLogger(CurrencyLayerConnector.class);
    private static final String CACHED_RESPONSE = "{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2016-04-13\",\"timestamp\":1460539812,\"source\":\"USD\",\"quotes\":{\"USDAED\":3.67295,\"USDAFN\":68.389999,\"USDALL\":121.799495,\"USDAMD\":483.790009,\"USDANG\":1.789788,\"USDAOA\":162.755005,\"USDARS\":14.49995,\"USDAUD\":1.304223,\"USDAWG\":1.79,\"USDAZN\":1.526401,\"USDBAM\":1.725396,\"USDBBD\":2,\"USDBDT\":78.623947,\"USDBGN\":1.725402,\"USDBHD\":0.37704,\"USDBIF\":1561.050049,\"USDBMD\":0.99995,\"USDBND\":1.34845,\"USDBOB\":6.909906,\"USDBRL\":3.490803,\"USDBSD\":1.002385,\"USDBTC\":0.002356,\"USDBTN\":66.544998,\"USDBWP\":10.84745,\"USDBYR\":19850,\"USDBZD\":1.994968,\"USDCAD\":1.27912,\"USDCDF\":926.999681,\"USDCHF\":0.961402,\"USDCLF\":0.0246,\"USDCLP\":672.695007,\"USDCNY\":6.477096,\"USDCOP\":3019.75,\"USDCRC\":535.200012,\"USDCUC\":1,\"USDCUP\":1.000062,\"USDCVE\":96.876999,\"USDCZK\":23.884501,\"USDDJF\":177.625018,\"USDDKK\":6.576905,\"USDDOP\":45.854992,\"USDDZD\":108.514999,\"USDEEK\":13.739773,\"USDEGP\":8.8798,\"USDERN\":16.180235,\"USDETB\":21.502001,\"USDEUR\":0.883861,\"USDFJD\":2.07125,\"USDFKP\":0.659797,\"USDGBP\":0.702642,\"USDGEL\":2.244981,\"USDGGP\":0.702636,\"USDGHS\":3.827504,\"USDGIP\":0.690947,\"USDGMD\":42.830002,\"USDGNF\":7608.049805,\"USDGTQ\":7.736502,\"USDGYD\":207.210007,\"USDHKD\":7.75585,\"USDHNL\":22.587504,\"USDHRK\":6.600797,\"USDHTG\":61.681301,\"USDHUF\":275.015015,\"USDIDR\":13145,\"USDILS\":3.77745,\"USDIMP\":0.702636,\"USDINR\":66.55545,\"USDIQD\":1107.099976,\"USDIRR\":30281.000196,\"USDISK\":124.110001,\"USDJEP\":0.702636,\"USDJMD\":121.690002,\"USDJOD\":0.709301,\"USDJPY\":109.269997,\"USDKES\":101.141502,\"USDKGS\":68.900299,\"USDKHR\":4039.449951,\"USDKMF\":434.006256,\"USDKPW\":899.999985,\"USDKRW\":1148.535034,\"USDKWD\":0.30167,\"USDKYD\":0.820027,\"USDKZT\":336.374939,\"USDLAK\":8115.450195,\"USDLBP\":1506.502842,\"USDLKR\":145.380005,\"USDLRD\":84.669998,\"USDLSL\":14.744501,\"USDLTL\":3.048697,\"USDLVL\":0.62055,\"USDLYD\":1.369802,\"USDMAD\":9.66815,\"USDMDL\":19.669976,\"USDMGA\":3174.800049,\"USDMKD\":54.305001,\"USDMMK\":1181.800049,\"USDMNT\":2011.999504,\"USDMOP\":7.988403,\"USDMRO\":344.000189,\"USDMUR\":35.029999,\"USDMVR\":15.370287,\"USDMWK\":679.099976,\"USDMXN\":17.480398,\"USDMYR\":3.87605,\"USDMZN\":52.430298,\"USDNAD\":14.7445,\"USDNGN\":198.960007,\"USDNIO\":28.318199,\"USDNOK\":8.21275,\"USDNPR\":106.471997,\"USDNZD\":1.443939,\"USDOMR\":0.38505,\"USDPAB\":1.00219,\"USDPEN\":3.265802,\"USDPGK\":3.12185,\"USDPHP\":46.067001,\"USDPKR\":104.675003,\"USDPLN\":3.786701,\"USDPYG\":5582.488272,\"USDQAR\":3.64185,\"USDRON\":3.95115,\"USDRSD\":108.349998,\"USDRUB\":65.411697,\"USDRWF\":773.719971,\"USDSAR\":3.75015,\"USDSBD\":7.937008,\"USDSCR\":14.25345,\"USDSDG\":6.099101,\"USDSEK\":8.10785,\"USDSGD\":1.34906,\"USDSHP\":0.701602,\"USDSLL\":3969.999862,\"USDSOS\":612.000223,\"USDSRD\":5.372496,\"USDSTD\":21522.5,\"USDSVC\":8.727016,\"USDSYP\":219.856995,\"USDSZL\":14.744503,\"USDTHB\":35.033001,\"USDTJS\":7.869598,\"USDTMT\":3.5,\"USDTND\":2.00655,\"USDTOP\":2.190704,\"USDTRY\":2.851903,\"USDTTD\":6.584251,\"USDTWD\":32.324001,\"USDTZS\":2191.050049,\"USDUAH\":25.475013,\"USDUGX\":3343.000282,\"USDUSD\":1,\"USDUYU\":30.92963,\"USDUZS\":2888.389893,\"USDVEF\":9.9499,\"USDVND\":22305,\"USDVUV\":109.610001,\"USDWST\":2.541343,\"USDXAF\":578.674927,\"USDXAG\":0.062325,\"USDXAU\":0.000803,\"USDXCD\":2.69913,\"USDXDR\":0.71025,\"USDXOF\":578.674927,\"USDXPF\":105.272552,\"USDYER\":249.501725,\"USDZAR\":14.73425,\"USDZMK\":5156.103848,\"USDZMW\":9.289636,\"USDZWL\":322.355011}}";

    public static Map<String, Double> getExchangeRatesForDate(Date date){
        String quotesString = getOnlineQuotesString(date);
//        String quotesString = null;

        if (quotesString == null) {
            log.info("Failed to obtain online online quotes, using cached data for 13.04.2016");
            quotesString = CACHED_RESPONSE;
        }

        return parseResponseBody(quotesString);
    }

    private static String getOnlineQuotesString(Date date){
        String requestUrl = String.format(REQUEST_URL_PATTERN, ACCESS_KEY, DATE_FORMAT.format(date));
        log.info("Getting quotes from URL {}", requestUrl);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(requestUrl);

        try{
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);

            return responseBody;
        } catch (Exception e){
            log.error("Error connecting to CurrencyLayer endpoint: {}", e);
        }

        return null;
    }

    private static Map<String, Double> parseResponseBody(String responseBody) {
        JSONObject jsonResponse = new JSONObject(responseBody);
        JSONObject quotes = jsonResponse.getJSONObject("quotes");
        HashMap<String, Double> result = new HashMap();

        for (String key : quotes.keySet()){
            result.put(key, quotes.getDouble(key));
        }

        return result;
    }
}
