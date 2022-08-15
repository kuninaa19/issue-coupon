package com.coupon.common.repository;

import com.coupon.common.model.Coupon;

public interface CustomCouponRepository {

    Coupon existCouponQuantity(Long id);
}
