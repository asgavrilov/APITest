package org.freesound.tests;

import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
//import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class IntegrationTest {

    private static final String URL = "https://freesound.org/";

    private static final String API_PATH = "apiv2/search/text/";

    private static final String API_KEY = "rcBTauOX7I4qJbioXWtd04GWp1f9lLq9RFd2rXpJ";

    private static String query = "dogs";

    private static String filename;

    @BeforeMethod
    public  void beforeTest() throws UnirestException {
       String json = Unirest.get(URL + API_PATH)
                .queryString("token", API_KEY)
                .queryString("query", query)
                .asString()
                .getBody()
                .toString();

         filename = JsonPath.read(json, "$.results[0].name");
    }

    @Test
    public void restValidationTest() {
        open(URL +"search/?q=dogs");

        $$(".sound_filename")
                .get(0)
                .should(text(IntegrationTest.filename));
    }
}
