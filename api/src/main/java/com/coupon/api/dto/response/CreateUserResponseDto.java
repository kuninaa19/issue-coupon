package com.coupon.api.dto.response;

import com.coupon.common.model.User;

public class CreateUserResponseDto {
    private final Long id;
    private final String email;

    public CreateUserResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static CreateUserResponseDto of(User user) {
        return new CreateUserResponseDto(user.getId(), user.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}


