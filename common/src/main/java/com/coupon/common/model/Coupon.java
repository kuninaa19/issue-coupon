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
    @Column(name = "REST_QUANTITY", nullable = false)
    private int restQuantity;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @Version
    private Integer version;


    public static Coupon createCoupon(String name, int quantity) {
        Coupon aCoupon = new Coupon();
        aCoupon.name = name;
        aCoupon.quantity = quantity;
        aCoupon.restQuantity = quantity;

        return aCoupon;
    }

    public void removeRestQuantity() {
        int restQuantity = this.restQuantity - 1;
        if (restQuantity < 0) {
            throw new PropertyNotFoundException("Have not enough quantity");
        }
        this.restQuantity = restQuantity;
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
