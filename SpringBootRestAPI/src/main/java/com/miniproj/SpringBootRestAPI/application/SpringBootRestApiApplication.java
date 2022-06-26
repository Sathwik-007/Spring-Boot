package com.miniproj.SpringBootRestAPI.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author sathwikpinninti
 *
 */
@SpringBootApplication
@ComponentScan("com.miniproj.SpringBootRestAPI")
public class SpringBootRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApplication.class, args);
	}

}
