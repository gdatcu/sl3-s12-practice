package ro.curs.qa.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MoreApiTest {

    @Test
    public void testPublicApiEndpoint() {

        System.out.println("Rulare test API simplu...");

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/2") // Un API public simplu
                .then()
                .log().all()
                .assertThat()
                .statusCode(200) // Verifică status code-ul
                .body("userId", equalTo(1))
                .body("id", equalTo(2));
//                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")); // Verifică o valoare din răspuns

        System.out.println("Test API simplu a trecut!");
    }
}

