package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class CreditCardApplyApplicationMain implements ApplicationRunner {
	public static void main(String[] args) {
		SpringApplication.run(CreditCardApplyApplicationMain.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		log.info("Credit Card Apply Application is started.");
	}
}
