package com.coupon.api.controllers;


import com.coupon.api.dto.request.CouponCreateRequestDto;
import com.coupon.api.dto.response.CouponCreateResponseDto;
import com.coupon.api.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @PostMapping("")
    public ResponseEntity<CouponCreateResponseDto> createCoupon(@RequestBody CouponCreateRequestDto couponCreateRequestDto) {
        return new ResponseEntity<>(couponService.createCoupon(couponCreateRequestDto), HttpStatus.CREATED);
    }
}
