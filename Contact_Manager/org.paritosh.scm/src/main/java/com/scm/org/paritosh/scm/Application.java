package com.scm.org.paritosh.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.scm.org.paritosh.repository")
@SpringBootApplication(scanBasePackages="com.scm.org.paritosh.implementation")
@ComponentScan(basePackages = "com.scm.org")
@EntityScan("com.scm.org.paritosh.entity") 


public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
