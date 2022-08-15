package com.coupon.api.controllers;


import com.coupon.api.dto.request.CreateCouponRequestDto;
import com.coupon.api.dto.request.IssueCouponRequestDto;
import com.coupon.api.dto.response.CreateCouponResponseDto;
import com.coupon.api.dto.response.FindCouponResponseDto;
import com.coupon.api.dto.response.IssueCouponResponseDto;
import com.coupon.api.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;

    @GetMapping("")
    public ResponseEntity<List<FindCouponResponseDto>> findCoupons() {
        return ResponseEntity.ok(couponService.findCoupons());
    }

    @GetMapping("{couponId}")
    public ResponseEntity<FindCouponResponseDto> findCoupon(@PathVariable Long couponId) {
        return ResponseEntity.ok().body(couponService.findCoupon(couponId));
    }

    @PostMapping("")
    public ResponseEntity<CreateCouponResponseDto> createCoupon(@RequestBody CreateCouponRequestDto createCouponRequestDto) {
        CreateCouponResponseDto aCoupon = couponService.coupon(createCouponRequestDto);

        return ResponseEntity.created(URI.create("/coupons/" + aCoupon.getId())).body(aCoupon);
    }

    /**
     * 유저 쿠폰 발급
     */
    @PostMapping("{couponId}/issue")
    public ResponseEntity<IssueCouponResponseDto> UserIssueCoupon(@PathVariable Long couponId, @RequestBody IssueCouponRequestDto issueCouponRequestDto) {
        IssueCouponResponseDto aIssuedCoupon = couponService.userIssueCoupon(couponId, issueCouponRequestDto);
        return ResponseEntity.created(URI.create("/users/" + aIssuedCoupon.getUserEmail())).body(aIssuedCoupon);
    }
}
