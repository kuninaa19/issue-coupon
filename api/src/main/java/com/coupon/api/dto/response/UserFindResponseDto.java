package com.coupon.api.dto.response;

import com.coupon.common.model.User;

/** TODO 추후 유저가 소유한 쿠폰 정보 추가
 *
 * */
public class UserFindResponseDto {
    private final Long id;
    private final String email;

    public UserFindResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static UserFindResponseDto of(User user) {
        return new UserFindResponseDto(user.getId(), user.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
