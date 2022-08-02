package com.coupon.api.controllers;

import com.coupon.api.dto.request.UserCreateRequestDto;
import com.coupon.api.dto.response.UserCreateResponseDto;
import com.coupon.api.dto.response.UserFindResponseDto;
import com.coupon.api.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("{email}")
    public ResponseEntity<UserFindResponseDto> getUser(
            @PathVariable String email) {
        return ResponseEntity.ok().body(userService.getUser(email));
    }

    @PostMapping("")
    public ResponseEntity<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        return new ResponseEntity<>(userService.CreateUser(userCreateRequestDto), HttpStatus.CREATED);
    }
}
