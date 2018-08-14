package net.yandex.dictionary;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;


public class LookupTest extends BaseTest {

    @Test
    public void lookupTest() {
        RestAssured.useRelaxedHTTPSValidation();
        LookupSteps lookup = new LookupSteps();
        String myPath = lookup.getPathFormated(lookup.MY_API_KEY, "en-ru", "business");

        given()
                .when()
                .get(lookup.API_URL + myPath)
                .then()
                .statusCode(200)
                .body("def[0].text", equalTo("business"))
                .body("def[0].tr.text", hasItem("бизнес"))
                .body("def[0].pos", equalTo("noun"));
    }
}
