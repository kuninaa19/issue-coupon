package com.coupon.api.errors.errorcode;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Cannot find resource."),
    ALREADY_EXIST_RESOURCE(HttpStatus.BAD_REQUEST,"Already Exist Resource"),

    ;

    private final HttpStatus httpStatus;
    private final String message;

}