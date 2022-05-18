package com.test.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.test.sample.service.MovieService;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "com.test.sample")

public class MiniAssignmentApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(MiniAssignmentApplication.class);

	@Autowired
	private MovieService movieService;
	public static void main(String[] args) {
		SpringApplication.run(MiniAssignmentApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		movieService.loadCsvFileDataInDB();
		
	}

}
