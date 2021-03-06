package com.app.ecom.store.masterdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MasterDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterDataApplication.class, args);
	}

}
