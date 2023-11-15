package com.quyen.qlhd.statics;

public enum CustomerType {
    INDIVIDUAL("Cá nhân"),
    ADMIN_UNIT_REPRE("Đại diện đơn vị hành chính"),
    BUSINESS_UNIT_REPRE("Đại diện đơn vị kinh doanh");

    public String value;

    CustomerType(String value) {
        this.value = value;
    }

}
