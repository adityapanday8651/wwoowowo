package com.wowment.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication 
@EnableAutoConfiguration
@EnableScheduling
@EnableMongoRepositories({"com.wowment.mongo.repository"})
@ComponentScan({"com.wowment.service","com.wowment.mongo.repository","com.wowment"})
public class WowmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(WowmentApplication.class, args);
	}
}