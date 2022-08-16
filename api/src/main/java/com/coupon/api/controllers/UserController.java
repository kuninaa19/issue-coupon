package com.coupon.api.controllers;

import com.coupon.api.dto.request.CreateUserRequestDto;
import com.coupon.api.dto.response.CreateUserResponseDto;
import com.coupon.api.dto.response.FindUserCouponResponseDto;
import com.coupon.api.dto.response.FindUserResponseDto;
import com.coupon.api.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("{email}")
    public ResponseEntity<FindUserResponseDto> getUser(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.getUser(email));
    }

    @GetMapping("{email}/coupon")
    public ResponseEntity<List<FindUserCouponResponseDto>> getUserCoupons(@PathVariable String email){
        return ResponseEntity.ok().body(userService.getUserCoupons(email));
    }

    @PostMapping("")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        return new ResponseEntity<>(userService.CreateUser(createUserRequestDto), HttpStatus.CREATED);
    }
}
