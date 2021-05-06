package spring.boilerplate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;

@SpringBootApplication
public class Application {
	static final String port;
	static {
		String appPort = System.getenv("APP_PORT");
		port = appPort == null || appPort.isBlank() ? "8090" : appPort;
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(Application.class)
				.properties(new HashMap<String, Object>() {{
					put("server.port", port);
				}})
				.run(args);
	}
}
