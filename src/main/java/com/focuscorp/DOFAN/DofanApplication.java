package com.focuscorp.DOFAN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DofanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DofanApplication.class, args);
	}

}
