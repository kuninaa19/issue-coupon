package com.coupon.api.dto.response;

import com.coupon.common.model.Coupon;

import java.util.Date;

public class CouponCreateResponseDto {
    private final String name;
    private final Long quantity;
    private final Date createdAt;

    public CouponCreateResponseDto(String name, Long quantity, Date createdAt) {
        this.name = name;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public static CouponCreateResponseDto of(Coupon coupon) {
        return new CouponCreateResponseDto(coupon.getName(), coupon.getQuantity(), coupon.getCreatedAt());
    }

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
