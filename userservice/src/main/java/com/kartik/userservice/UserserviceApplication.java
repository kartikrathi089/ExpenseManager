package com.kartik.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserserviceApplication.class, args);
	}

}
