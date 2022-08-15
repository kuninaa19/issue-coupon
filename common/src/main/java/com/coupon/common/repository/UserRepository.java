package com.coupon.common.repository;

import com.coupon.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select  u FROM User u WHERE u.email = :email")
    User findByEmailWithLocking(@Param("email") String email);
}