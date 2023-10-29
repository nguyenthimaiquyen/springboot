package com.quyen.springmvc02.statics;

public enum ReaderType {
    STUDENT("Sinh viên"),
    TEACHER("Giáo viên"),
    POSTGRADUATE("Học viên cao học");

    public String value;

    ReaderType(String value) {
        this.value = value;
    }
}
