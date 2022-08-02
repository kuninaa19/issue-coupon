package com.coupon.api.services;


import com.coupon.api.dto.request.UserCreateRequestDto;
import com.coupon.api.dto.response.UserCreateResponseDto;
import com.coupon.api.dto.response.UserFindResponseDto;
import com.coupon.api.errors.errorcode.CustomErrorCode;
import com.coupon.api.errors.exception.RestApiException;
import com.coupon.common.model.User;
import com.coupon.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserFindResponseDto getUser(String email){
        User anResult = userRepository.findByEmail(email);
        if (anResult == null) {
            throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
        }

        return UserFindResponseDto.of(anResult);
    }

    @Transactional
    public UserCreateResponseDto CreateUser(UserCreateRequestDto userCreateRequestDto) {
        User anResult = userRepository.findByEmail(userCreateRequestDto.getEmail());
        if (anResult != null) {
            throw new RestApiException(CustomErrorCode.ALREADY_EXIST_RESOURCE);
        }

        User anUser = userRepository.save(new User(userCreateRequestDto.getEmail()));

        return UserCreateResponseDto.of(anUser);
    }
}
