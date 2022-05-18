package com.test.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MiniAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniAssignmentApplication.class, args);
	}

}
