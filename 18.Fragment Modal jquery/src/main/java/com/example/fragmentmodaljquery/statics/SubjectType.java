package com.example.fragmentmodaljquery.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubjectType {
    GENERAL("GENERAL", "Đại cương"),
    BASIC_MAJOR( "BASIC_MAJOR", "Cơ sở ngành"),
    SPECIALIZED("SPECIALIZED", "Chuyên ngành");

    public String code;
    public String name;


}
