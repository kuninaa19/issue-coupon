package com.coupon.common.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "email"))
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<UserCoupon> coupons = new ArrayList<UserCoupon>();

    public User(String email) {
        this.email = email;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}