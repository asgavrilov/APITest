package ru.ya.translate;

public enum EndPointUrl {

    TRANSLATE("/translate");
    String path;
    EndPointUrl(String url) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String addPath(String additionalPath) {
        return path + additionalPath;
    }
}
