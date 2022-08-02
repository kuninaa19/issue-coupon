package com.coupon.api.dto.request;

public class CouponCreateRequestDto {
    private String name;
    private Long quantity;

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }
}
