package ru.ya.translate.tests;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.RestAssured;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.ya.translate.EndPointUrl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;


public class RestAssuredTest {
    private static final String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
    private static final String API_KEY = "trnsl.1.1.20180818T130230Z.732edf87b11dd383.e5c36503be59705e245e8fb34f3d0f3b8751d024";

    @BeforeClass
    public static void beforeAll() {
        RestAssured.baseURI = "https://translate.yandex.net/api/v1.5/tr.json/";
    }

    protected static String getPathFormated(String key, String text, String languageFormat) {
        return String.format("?key=%s&text=%s&lang=%s", key, text, languageFormat);
    }

    public static void main(String[] args) throws UnirestException {
        String response = Unirest.get(API_URL +
                getPathFormated(API_KEY, "Привет,мир", "ru-en")).asString().getBody();
        System.out.println(response);
    }

    @Test
    public void TC1_dictionaryTest() {
        RestAssured.useRelaxedHTTPSValidation();
        String additionalPath = getPathFormated(API_KEY, "Привет,мир", "ru-en");
        given()
                .header("User-Agent", "Chrome")
                .header("JWT", "jwt_token")
                .when()
                .get(API_URL + EndPointUrl.TRANSLATE.addPath(additionalPath))
                .then()
                .statusCode(200)
                .body("text", hasItem("Hello world"))
                .body("lang", equalTo("ru-en"))
                .body("code", equalTo(200));
    }
}


// мой ключ dict.1.1.20180801T094516Z.c5fb9055823d3715.582323e484f5556682587be45f0b1fd4781f215a