package com.userService.userService;

import com.userService.userService.config.RabbitConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class UserServiceApplication {

	public static void main(String[] args) {

		PropertyConfigurator.configure(UserServiceApplication.class.getResource("/log4j.properties"));
		SpringApplication.run(UserServiceApplication.class, args);

	}


}
