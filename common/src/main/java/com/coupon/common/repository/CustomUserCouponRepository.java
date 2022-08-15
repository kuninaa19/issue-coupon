package com.coupon.common.repository;

import com.coupon.common.model.UserCoupon;

import java.util.List;

public interface CustomUserCouponRepository {
    Boolean exist(Long userId, Long couponId);

    /**
     * 테스트용 쿼리 메소드
     * 특정 쿠폰이름으로 발곱된 쿠폰 가져오기
     * */
    List<UserCoupon> findIssuedCouponByCouponId(Long couponId);
}
