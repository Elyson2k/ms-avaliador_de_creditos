package com.example.eurekaserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaservApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaservApplication.class, args);
	}

}
