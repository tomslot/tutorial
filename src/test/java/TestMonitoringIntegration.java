import com.zooplus.forex.Application;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.*;
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