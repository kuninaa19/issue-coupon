package com.coupon.api.services;


import com.coupon.api.dto.request.CreateUserRequestDto;
import com.coupon.api.dto.response.CreateUserResponseDto;
import com.coupon.api.dto.response.FindUserResponseDto;
import com.coupon.api.errors.errorcode.CustomErrorCode;
import com.coupon.api.errors.exception.RestApiException;
import com.coupon.common.model.User;
import com.coupon.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.coupon.common.model.User.createUser;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public FindUserResponseDto getUser(String email){
        User anResult = userRepository.findByEmail(email);
        if (anResult == null) {
            throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
        }

        return FindUserResponseDto.of(anResult);
    }

    @Transactional
    public CreateUserResponseDto CreateUser(CreateUserRequestDto createUserRequestDto) {
        User anResult = userRepository.findByEmail(createUserRequestDto.getEmail());
        if (anResult != null) {
            throw new RestApiException(CustomErrorCode.ALREADY_EXIST_RESOURCE);
        }

        User anUser = userRepository.save(createUser(createUserRequestDto.getEmail()));

        return CreateUserResponseDto.of(anUser);
    }
}
