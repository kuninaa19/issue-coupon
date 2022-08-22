package com.coupon.api.dto.request;

import org.springframework.lang.NonNull;

public class CreateUserRequestDto {
    @NonNull
    private String email;

    public CreateUserRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
