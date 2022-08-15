package com.coupon.common.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserCoupon {
    @Id
    @Column(name = "USER_COUPON_ID")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "COUPON_ID")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    public static UserCoupon createUserCoupon(Coupon coupon, User user) {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.coupon = coupon;
        userCoupon.user = user;

        return userCoupon;
    }

    @PrePersist
    public void generateId() {
        if (this.id != null) {
            throw new IllegalArgumentException("Already exist id");
        }
        String uuid = UUID.randomUUID().toString();
        this.id = uuid + new Date().getTime();
    }

    public String getId() {
        return id;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public User getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
