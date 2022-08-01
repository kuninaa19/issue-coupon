package com.coupon.api.dto.response;

import com.coupon.common.model.User;

public class UserCreateResponseDto {
    /**
     * TODO final 변수를 써도 되나?
     * */
    private final Long id;
    private final String email;

    public UserCreateResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static UserCreateResponseDto of(User user) {
        return new UserCreateResponseDto(user.getId(), user.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}


