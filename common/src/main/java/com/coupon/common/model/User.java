package com.coupon.common.model;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
}