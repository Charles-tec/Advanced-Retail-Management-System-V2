package com.armsV2.armsApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class ArmsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArmsApiApplication.class, args);
	}


	@Bean
	public BCryptPasswordEncoder passwordDecoder() {
		return new BCryptPasswordEncoder();
	}

}
