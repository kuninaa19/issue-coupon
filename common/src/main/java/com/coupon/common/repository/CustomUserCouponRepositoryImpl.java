package com.coupon.common.repository;

import com.coupon.common.model.UserCoupon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.coupon.common.model.QCoupon.coupon;
import static com.coupon.common.model.QUser.user;
import static com.coupon.common.model.QUserCoupon.userCoupon;

public class CustomUserCouponRepositoryImpl extends QuerydslRepositorySupport implements CustomUserCouponRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CustomUserCouponRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(UserCoupon.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Boolean exist(Long userId, Long couponId) {
        Integer result = jpaQueryFactory.selectOne()
                .from(userCoupon).join(userCoupon.user, user).join(userCoupon.coupon, coupon)
                .where(user.id.eq(userId).and(coupon.id.eq(couponId)))
                .fetchFirst();

        return result != null;
    }

    @Override
    public List<UserCoupon> findUserCouponByEmail(String email) {
        return jpaQueryFactory.selectFrom(userCoupon).join(userCoupon.coupon, coupon).fetchJoin().join(userCoupon.user, user)
                .where(userCoupon.user.email.eq(email))
                .orderBy(userCoupon.createdAt.asc())
                .fetch();
    }

    @Override
    public List<UserCoupon> findIssuedCouponByCouponId(Long couponId) {
        return jpaQueryFactory
                .selectFrom(userCoupon)
                .join(userCoupon.coupon, coupon)
                .where(userCoupon.coupon.id.eq(couponId))
                .fetch();
    }

}