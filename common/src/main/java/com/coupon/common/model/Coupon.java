package com.coupon.common.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = @Index(columnList = "coupon_name"))
@DynamicUpdate
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_name", nullable = false, unique = true)
    private String name;

    private Long quantity;

    private Date createdAt;
}
