package com.adri.ej31;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.adri.ej31.feign")
public class Ejerciciodb1SpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ejerciciodb1SpringApplication.class, args);
	}

}
