package com.coupon.api.dto.response;

import com.coupon.common.model.User;

/** TODO 추후 유저가 소유한 쿠폰 정보 추가
 *
 * */
public class FindUserResponseDto {
    private final Long id;
    private final String email;

    public FindUserResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static FindUserResponseDto of(User user) {
        return new FindUserResponseDto(user.getId(), user.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
