package com.coupon.common.repository;

import com.coupon.common.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CustomCouponRepository {
    Coupon findByName(String name);
}
