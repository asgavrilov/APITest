package ru.ya.translate.tests;


import org.testng.annotations.Test;
import ru.ya.translate.DictionaryClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DictionaryApiTests {

    @Test
    public void firstTest() {


        DictionaryClient dictionaryClient = new DictionaryClient();
        String translation = dictionaryClient.sendGet("Привет", "ru-en" );
        assertThat(translation, equalTo("{\"code\":200,\"lang\":\"ru-en\",\"text\":[\"Hi\"]}"));
    }
}
