package com.dataService.dataService;

import com.dataService.dataService.config.RabbitConfiguration;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
@Import(RabbitConfiguration.class)
public class DataServiceApplication {

    public static void main(String[] args) {

        PropertyConfigurator.configure(DataServiceApplication.class.getResource("/application.properties"));
        SpringApplication.run(DataServiceApplication.class, args);
    }
}
