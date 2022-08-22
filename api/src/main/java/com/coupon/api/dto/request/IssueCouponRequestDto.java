package com.coupon.api.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IssueCouponRequestDto {
    private String email;

    public IssueCouponRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
