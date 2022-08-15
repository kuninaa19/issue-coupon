package com.coupon.common.repository;

public interface CustomUserCouponRepository {
    Boolean exist(Long userId, Long couponId);
}
