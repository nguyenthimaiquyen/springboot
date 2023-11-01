package com.github.quyen.qlthuvien.statics;

public enum ReaderType {
    SINHVIEN("sinh viên"),
    HOCVIENCAOHOC("học viên cao học"),
    GIAOVIEN("giáo viên");

    public String name;

    ReaderType(String name) {
        this.name = name;
    }
}
