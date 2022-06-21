package com.microservices.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"com.microservices.controller", "com.microservices.service"})
@EntityScan("com.microservices.entity")
@EnableJpaRepositories("com.microservices.repository")
@EnableFeignClients("com.microservices.feignclients")
@EnableEurekaClient
public class StudentServiceApplication {
	
	@Value("${address.service.url}")
	private String addressServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		WebClient webClient = WebClient.builder().baseUrl(addressServiceUrl).build();
		return webClient;
		
	}
}
