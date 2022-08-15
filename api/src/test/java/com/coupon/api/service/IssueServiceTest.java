package com.coupon.api.service;

import com.coupon.api.dto.request.CreateCouponRequestDto;
import com.coupon.api.dto.request.IssueCouponRequestDto;
import com.coupon.api.dto.response.CreateCouponResponseDto;
import com.coupon.api.services.CouponService;
import com.coupon.common.model.UserCoupon;
import com.coupon.common.repository.UserCouponRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class IssueServiceTest {

    @Autowired
    private CouponService couponService;
    @Autowired
    private UserCouponRepository userCouponRepository;
    private Long couponId;
    private int quantity;
    private ArrayList<String> userEmails;


    @BeforeEach
    void getUserEmails() {
        this.userEmails = new UserEmailDummy().getUserEmail();
    }

    @BeforeEach
    void setCouponId() {
        final String name = this.getRandomNumberUsingInts(100) + "% 할인쿠폰 테스트";
        final int quantity = 4;

        CreateCouponResponseDto createCouponResponseDto = couponService.coupon(new CreateCouponRequestDto(name, quantity));
        this.couponId = createCouponResponseDto.getId();
        this.quantity = createCouponResponseDto.getQuantity();
    }

    @Test
    @DisplayName("쿠폰 수량이 4개인 쿠폰에 6명이 달려들어서 딱 4명만 GET 해야한다.")
    void issueCoupon() throws InterruptedException {
        final int USER = 6;

        CountDownLatch countDownLatch = new CountDownLatch(USER);

        List<IssueWorker> workers = Stream
                .generate(() -> new IssueWorker(this.couponId, countDownLatch))
                .limit(1000)
                .collect(Collectors.toList());

        workers.forEach(worker -> new Thread(worker).start());
        countDownLatch.await();

        List<UserCoupon> issuedCoupons = userCouponRepository.findIssuedCouponByCouponId(couponId);
        int issuedCouponSize = issuedCoupons.size();

        assertEquals(this.quantity, issuedCouponSize);

    }

    private int getRandomNumberUsingInts(int max) {
        Random random = new Random();
        return random.ints(0, max)
                .findFirst()
                .getAsInt();
    }

    private class IssueWorker implements Runnable {
        private final Long couponId;
        private final IssueCouponRequestDto issueCouponRequestDto;
        private final CountDownLatch countDownLatch;

        public IssueWorker(Long couponId, CountDownLatch countDownLatch) {
            this.couponId = couponId;
            this.countDownLatch = countDownLatch;
            int random = getRandomNumberUsingInts(userEmails.size());
            this.issueCouponRequestDto = new IssueCouponRequestDto(userEmails.get(random));
        }

        @Override
        public void run() {
            try {
                couponService.userIssueCoupon(couponId, issueCouponRequestDto);
            } catch (Exception ignored) {
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
