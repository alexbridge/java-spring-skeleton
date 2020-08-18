package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(Application.class)
				.properties(new HashMap<String, Object>() {{
					put("spring.application.name", "Messages");
					put("server.port", System.getenv("APP_PORT"));
					put("spring.jackson.property-naming-strategy", "SNAKE_CASE");
				}})
				.run(args);
	}
}
