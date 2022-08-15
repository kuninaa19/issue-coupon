package com.coupon.common.model;

import org.hibernate.PropertyNotFoundException;
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

    private int quantity;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    public static Coupon createCoupon(String name, int quantity) {
        Coupon aCoupon = new Coupon();
        aCoupon.name = name;
        aCoupon.quantity = quantity;

        return aCoupon;
    }

    public void removeQuantity() {
        int restQuantity = this.quantity - 1;
        if (restQuantity < 0) {
            throw new PropertyNotFoundException("Have not enough quantity");
        }
        this.quantity = restQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
