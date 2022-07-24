package com.coupon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.coupon.common")
@EnableJpaRepositories("com.coupon.common.repository")
@EnableJpaAuditing
@SpringBootApplication
public class CouponApplication {
	public static void main(String[] args) {
		SpringApplication.run(CouponApplication.class, args);
	}
}
