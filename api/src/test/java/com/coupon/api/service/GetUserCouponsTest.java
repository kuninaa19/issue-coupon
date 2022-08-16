package com.coupon.api.service;

import com.coupon.common.model.UserCoupon;
import com.coupon.common.repository.UserCouponRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetUserCouponsTest {
    @Autowired
    private UserCouponRepository userCouponRepository;
    private ArrayList<String> userEmails;


    @BeforeEach
    void getUserEmails() {
        this.userEmails = new UserEmailDummy().getUserEmail();
    }

    @Test
    @Order(1)
    @DisplayName("특정 이메일(유저)가 소유한 쿠폰 갯수 구하기")
    void getUserCoupons() {
        final int USER_COUPON = 10;
        String userEmail = userEmails.get(0);

        List<UserCoupon> userCoupons = userCouponRepository.findUserCouponByEmail(userEmail);
        int userCouponSize = userCoupons.size();

        assertEquals(USER_COUPON, userCouponSize);
    }
}
