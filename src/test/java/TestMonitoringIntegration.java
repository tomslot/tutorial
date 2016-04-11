import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.zooplus.forex.Application;
import org.apache.http.HttpStatus;
import org.aspectj.lang.annotation.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class TestMonitoringIntegration {

    @Test
    public void canAccessUsers(){
        when().
                get("/monitoring/users").
        then().
                statusCode(HttpStatus.SC_OK).
                body(containsString("ForexUser"));
    }
}