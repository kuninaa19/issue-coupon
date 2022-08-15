package com.coupon.api.dto.request;

import org.springframework.lang.NonNull;

public class CreateCouponRequestDto {
    @NonNull
    private String name;
    @NonNull
    private Long quantity;

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }
}
