package com.dxschool.lightme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LightmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightmeApplication.class, args);
	}

}
