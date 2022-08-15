package com.coupon.common.repository;

import com.coupon.common.model.Coupon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.coupon.common.model.QCoupon.coupon;
import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

public class CustomCouponRepositoryImpl extends QuerydslRepositorySupport implements CustomCouponRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CustomCouponRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Coupon.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Coupon existCouponQuantity(Long id) {
        return jpaQueryFactory.selectFrom(coupon)
                .where(coupon.id.eq(id).and(coupon.quantity.gt(0)))
                .setLockMode(PESSIMISTIC_WRITE)
                .fetchOne();
    }
}
