package com.app.ecom.store.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AddressLookupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressLookupServiceApplication.class, args);
	}

}
