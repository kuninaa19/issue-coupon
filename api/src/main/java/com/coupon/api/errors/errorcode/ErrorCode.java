package com.coupon.api.errors.errorcode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String name();
    HttpStatus getHttpStatus();
    String getMessage();
}