package com.quyen.phanconglaixe.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {
    A("A", "Loại A"),
    B("B", "Loại B"),
    C("C", "Loại C"),
    D("D", "Loại D"),
    E("E", "Loại E"),
    F("F", "Loại F");

    public String code;
    public String name;


}
