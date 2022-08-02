package com.coupon.api.services;

import com.coupon.api.dto.request.CouponCreateRequestDto;
import com.coupon.api.dto.response.CouponCreateResponseDto;
import com.coupon.api.errors.errorcode.CustomErrorCode;
import com.coupon.api.errors.exception.RestApiException;
import com.coupon.common.model.Coupon;
import com.coupon.common.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {
    private final CouponRepository couponRepository;

    @Transactional
    public CouponCreateResponseDto createCoupon(CouponCreateRequestDto couponCreateRequestDto) {
        Coupon anResult = couponRepository.findByName(couponCreateRequestDto.getName());
        if (anResult != null) {
            throw new RestApiException(CustomErrorCode.ALREADY_EXIST_RESOURCE);
        }

        Coupon aCoupon = couponRepository.save(new Coupon(couponCreateRequestDto.getName(), couponCreateRequestDto.getQuantity()));

        return CouponCreateResponseDto.of(aCoupon);
    }
}
