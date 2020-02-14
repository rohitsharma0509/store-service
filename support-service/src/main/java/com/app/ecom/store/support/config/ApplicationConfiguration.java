package com.app.ecom.store.support.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

	@LoadBalanced
	@Bean("restTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	
}
