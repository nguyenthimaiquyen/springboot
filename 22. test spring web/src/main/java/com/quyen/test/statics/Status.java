package com.quyen.test.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PENDING("PENDING", "pending"),
    APPROVED( "APPROVED", "approved"),
    REJECTED("REJECTED", "rejected");

    public String code;
    public String name;

}
