import com.zooplus.forex.Application;
import com.zooplus.forex.model.CurrencyEnum;
import com.zooplus.forex.model.CurrencyQuery;
import com.zooplus.forex.service.CurrencyConversionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

import static com.zooplus.forex.model.CurrencyEnum.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestConversionServiceIntegration {
    @Autowired
    private CurrencyConversionService conversionService;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final String DOUBLE_FORMAT = "%.2f";

    @Test
    public void test1() throws Exception{
        Assert.assertEquals("12567.25", convert("13.04.2016", 150., AUD, JPY));
    }

    @Test
    public void test2() throws Exception{
        Assert.assertEquals("2208.37", convert("13.04.2016", 350.12, PLN, CZK));
    }

    private String convert(String date, Double amount, CurrencyEnum srcCurrency,
                      CurrencyEnum dstCurrency) throws Exception{

        CurrencyQuery query = new CurrencyQuery();
        query.setDate(DATE_FORMAT.parse(date));
        query.setSrcCurrency(srcCurrency);
        query.setDstCurrency(dstCurrency);
        query.setAmount(amount);
        conversionService.covert(query);
        String result = String.format(DOUBLE_FORMAT, query.getConvertedAmount());
        return result;
    }
}
