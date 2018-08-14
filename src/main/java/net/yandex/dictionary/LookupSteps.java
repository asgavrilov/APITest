package net.yandex.dictionary;

public class LookupSteps extends BaseSteps {

    public static final String API_URL = "/lookup";

    public String getPathFormated(String key, String languageFormat, String text) {
        return String.format("?key=%s&lang=%s&text=%s", key, languageFormat, text);
    }

}
