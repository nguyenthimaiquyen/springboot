package com.quyen.springthymeleaf.statics;

public enum Level {
    A("Loại A"),
    B("Loại B"),
    C("Loại C"),
    D("Loại D"),
    E("Loại E"),
    F("Loại F");

    public String value;

    Level(String value) {
        this.value = value;
    }

}
