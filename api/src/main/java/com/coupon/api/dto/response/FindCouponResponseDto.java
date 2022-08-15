package com.coupon.api.dto.response;

import com.coupon.common.model.Coupon;

import java.util.Date;

public class FindCouponResponseDto {
    private final String name;
    private final int quantity;
    private final Date createdAt;

    public FindCouponResponseDto(String name, int quantity, Date createdAt) {
        this.name = name;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public  static FindCouponResponseDto of(Coupon coupon){
        return new FindCouponResponseDto(coupon.getName(), coupon.getQuantity(), coupon.getCreatedAt());
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
