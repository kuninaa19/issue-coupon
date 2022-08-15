package com.coupon.api.dto.request;

import org.springframework.lang.NonNull;

public class CreateCouponRequestDto {
    @NonNull
    private String name;
    @NonNull
    private int quantity;

    public CreateCouponRequestDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
