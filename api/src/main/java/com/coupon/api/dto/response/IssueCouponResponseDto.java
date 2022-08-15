package com.coupon.api.dto.response;

import com.coupon.common.model.UserCoupon;

import java.util.Date;

public class IssueCouponResponseDto {
    private final String id;
    private final String userEmail;
    private final String couponName;
    private final Date createdAt;

    public IssueCouponResponseDto(String id, String userEmail, String couponName, Date createdAt) {
        this.id = id;
        this.userEmail = userEmail;
        this.couponName = couponName;
        this.createdAt = createdAt;
    }

    public static IssueCouponResponseDto of(UserCoupon userCoupon) {
        return new IssueCouponResponseDto(userCoupon.getId(), userCoupon.getUser().getEmail(), userCoupon.getCoupon().getName(), userCoupon.getCreatedAt());
    }

    public String getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getCouponName() {
        return couponName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
