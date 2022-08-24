package com.coupon.api.services;

import com.coupon.api.dto.request.CreateCouponRequestDto;
import com.coupon.api.dto.request.IssueCouponRequestDto;
import com.coupon.api.dto.response.CreateCouponResponseDto;
import com.coupon.api.dto.response.FindCouponResponseDto;
import com.coupon.api.dto.response.IssueCouponResponseDto;
import com.coupon.api.errors.errorcode.CustomErrorCode;
import com.coupon.api.errors.exception.RestApiException;
import com.coupon.common.model.Coupon;
import com.coupon.common.model.User;
import com.coupon.common.model.UserCoupon;
import com.coupon.common.repository.CouponRepository;
import com.coupon.common.repository.UserCouponRepository;
import com.coupon.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.coupon.common.model.UserCoupon.createUserCoupon;
import static com.coupon.common.model.Coupon.createCoupon;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    public List<FindCouponResponseDto> findCoupons() {
        List<Coupon> coupons = couponRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        if (coupons.isEmpty()) {
            throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
        }

        return coupons.stream().map(FindCouponResponseDto::of).collect(Collectors.toList());
    }

    public FindCouponResponseDto findCoupon(Long couponId) {
        Coupon aCoupon = couponRepository.findById(couponId).orElse(null);
        if (aCoupon == null) {
            throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
        }

        return FindCouponResponseDto.of(aCoupon);
    }

    @Transactional
    public CreateCouponResponseDto coupon(CreateCouponRequestDto createCouponRequestDto) {
        Coupon anResult = couponRepository.findByName(createCouponRequestDto.getName());
        if (anResult != null) {
            throw new RestApiException(CustomErrorCode.ALREADY_EXIST_RESOURCE);
        }

        Coupon aCoupon = couponRepository.save(createCoupon(createCouponRequestDto.getName(), createCouponRequestDto.getQuantity()));

        return CreateCouponResponseDto.of(aCoupon);
    }

    @Transactional(timeout = 3, rollbackFor = InterruptedException.class)
    public IssueCouponResponseDto userIssueCoupon(Long coupon_id, IssueCouponRequestDto issueCouponRequestDto) {
        User anUser = userRepository.findByEmail(issueCouponRequestDto.getEmail());
        if (anUser == null) {
            throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
        }
        if (userCouponRepository.exist(anUser.getId(), coupon_id)) {
            throw new RestApiException(CustomErrorCode.ALREADY_EXIST_RESOURCE);
        }

        Coupon aCoupon = couponRepository.existRestQuantity(coupon_id);
        if (aCoupon == null) {
            throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
        }

        UserCoupon issuedCoupon = userCouponRepository.save(createUserCoupon(aCoupon, anUser));
        aCoupon.removeRestQuantity();

        return IssueCouponResponseDto.of(issuedCoupon);
    }
}
