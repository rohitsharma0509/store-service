package com.app.ecom.store.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserTokenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTokenServiceApplication.class, args);
	}

}
