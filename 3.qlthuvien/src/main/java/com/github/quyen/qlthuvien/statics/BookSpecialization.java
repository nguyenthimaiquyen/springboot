package com.github.quyen.qlthuvien.statics;

public enum BookSpecialization {
    KHTN("Khoa học tự nhiên"),
    VHNT("Văn học – Nghệ thuật"),
    DTVT("Điện tử Viễn thông"),
    CNTT("Công nghệ thông tin");

    public String name;

    BookSpecialization(String name) {
        this.name = name;
    }
}
