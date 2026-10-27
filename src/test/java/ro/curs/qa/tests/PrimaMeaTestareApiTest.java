package ro.curs.qa.tests;

import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PrimaMeaTestareApiTest {

    // Inițializăm loggerul SLF4J pentru această clasă
    private static final Logger log = LoggerFactory.getLogger(PrimaMeaTestareApiTest.class);

    @Test
    public void verificaEndpointPublic() {
        // Înlocuim System.out.println cu log.info pentru a testa profilul Maven
        log.info("=== MAVEN a lansat Testul! INFO ===");

        // Apelăm API-ul public și adăugăm log().all() în REST Assured pentru vizibilitate completă
        given()
                .log().all() // Loghează detaliile request-ului (URL, Headers, etc.)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/10")
                .then()
                .log().all() // Loghează detaliile response-ului primit de la server
                .statusCode(200)
                .body("id", equalTo(10));

        log.warn("=== Test Trecut cu Succes! WARN! ===");

        log.info("=== Test Trecut cu Succes! INFO ===");
    }
}
