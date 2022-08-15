package com.coupon.common.model;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = @Index(columnList = "coupon_name"))
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COUPON_NAME", nullable = false, unique = true)
    private String name;

    private Long quantity;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    public static Coupon createCoupon(String name, Long quantity) {
        Coupon aCoupon = new Coupon();
        aCoupon.name = name;
        aCoupon.quantity = quantity;

        return aCoupon;
    }

    public Boolean existCouponQuantity() {
        return this.getQuantity() > 0;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
