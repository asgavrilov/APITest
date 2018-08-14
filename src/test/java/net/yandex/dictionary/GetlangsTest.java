package net.yandex.dictionary;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class GetlangsTest extends BaseTest{

    @Test
    public void getLangTest() {
        GetlangsSteps getLang = new GetlangsSteps();
        String myPath = getLang.getPathFormated(getLang.MY_API_KEY);

        given()
                .when()
                .get(getLang.API_URL + myPath)
                .then()
                .statusCode(200)
                .body("$",hasItem("ru-ru"))
                .body("$",hasItem("pt-en"))
                .body("$",hasItem("uk-uk"));
    }
}
