package com.coupon.api.dto.response;

import com.coupon.common.model.UserCoupon;

import java.util.Date;

public class FindUserCouponResponseDto {
    private final String userCouponId;
    private final String couponName;
    private final Date createdAt;

    public FindUserCouponResponseDto(String userCouponId, String couponName, Date createdAt) {
        this.userCouponId = userCouponId;
        this.couponName = couponName;
        this.createdAt = createdAt;
    }

    public static FindUserCouponResponseDto of(UserCoupon userCoupon) {
        return new FindUserCouponResponseDto(userCoupon.getId(), userCoupon.getCoupon().getName(), userCoupon.getCreatedAt());
    }

    public String getUserCouponId() {
        return userCouponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
