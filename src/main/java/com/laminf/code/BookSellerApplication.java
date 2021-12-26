package com.laminf.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
//@PropertySource("classpath:application-${spring.profiles.active:prod}.properties")
public class BookSellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSellerApplication.class, args);
	}

}
