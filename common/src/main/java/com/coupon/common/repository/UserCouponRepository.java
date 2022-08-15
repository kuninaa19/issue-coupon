package com.coupon.common.repository;

import com.coupon.common.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long>, CustomUserCouponRepository {
}
