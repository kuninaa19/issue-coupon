package com.coupon.common.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "email"))
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String email;

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