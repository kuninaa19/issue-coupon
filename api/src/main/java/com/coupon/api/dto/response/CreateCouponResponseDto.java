package com.coupon.api.dto.response;

import com.coupon.common.model.Coupon;

import java.util.Date;

public class CreateCouponResponseDto {
    private final Long id;
    private final String name;
    private final int quantity;
    private final Date createdAt;

    public CreateCouponResponseDto(Long id, String name, int quantity, Date createdAt) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public static CreateCouponResponseDto of(Coupon coupon) {
        return new CreateCouponResponseDto(coupon.getId(), coupon.getName(), coupon.getQuantity(), coupon.getCreatedAt());
    }

    public Long getId() {
        return id;
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
