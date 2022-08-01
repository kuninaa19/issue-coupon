package com.coupon.api.dto.request;

import com.sun.istack.NotNull;

public class UserCreateRequestDto {
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }
}
