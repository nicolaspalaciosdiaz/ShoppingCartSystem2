package com.example.ShoppingCartSystem2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ShoppingCartSystem2Application {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartSystem2Application.class, args);
	}

}
