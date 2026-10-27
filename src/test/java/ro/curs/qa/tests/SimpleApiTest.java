package ro.curs.qa.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SimpleApiTest {

    private static final Logger log = LoggerFactory.getLogger(SimpleApiTest.class);

    @Test
    public void testPublicApiEndpoint() {

        log.info("=== START: Rulare test API simplu... ===");

        try {
            given()
                    .when()
                    .get("https://jsonplaceholder.typicode.com/posts/2")
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200)

                    // 1. REST Assured Built-in Validation: This will FAIL the test if it takes more than 2000 milliseconds
                    .time(lessThan(2000L), TimeUnit.MILLISECONDS)

                    .body("userId", equalTo(1))
                    .body("id", equalTo(2));
//                    .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));

            log.info("=== SUCCES: Test API simplu a trecut corect! ===");

        } catch (AssertionError e) {
            // 2. Catching the response time failure specifically to log it as a Warning or Error
            if (e.getMessage().contains("time")) {
                log.warn("=== WARNING: Serverul a răspuns corect, dar testul de performanță a picat! ===");
            }
            log.error("=== ERROR: Validarea testului a picat! ===", e);
            throw e;
        }
    }
}
