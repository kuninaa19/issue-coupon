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

    @Column(name = "coupon_name", nullable = false, unique = true)
    private String name;

    private Long quantity;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public Coupon(String name, Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Coupon() {

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
