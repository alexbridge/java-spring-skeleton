package com.github.alexbridge.web.v1;

import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import java.util.HashSet;
import java.util.ResourceBundle;

@RestController("ApiDocVersion1")
@RequestMapping("/v1")
@EnableSwagger2
public class ApiDocController {

	private final ResourceBundle apiPropsBundle = ResourceBundle.getBundle("com.github.alexbridge.web.v1.api");

	@Autowired
	private DocumentationCache documentationCache;

	@Autowired
	private ServiceModelToSwagger2Mapper mapper;

	@Autowired
	private JsonSerializer jsonSerializer;

	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody ResponseEntity<Json> get() {

		Documentation documentation = documentationCache.documentationByGroup("v1");
		if (documentation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Swagger swagger = mapper.mapDocumentation(documentation);
		return new ResponseEntity<>(jsonSerializer.toJson(swagger), HttpStatus.OK);
	}

	@Bean("Version1ApiDoc")
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.regex("/v1/messages.*"))
				.build()
				.apiInfo(apiInfo())
				.host(apiPropsBundle.getString("api.host"))
				.produces(new HashSet<>() {{
					add("application/json");
				}})
				.consumes(new HashSet<>() {{
					add("application/json");
				}})
				.groupName("v1");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(apiPropsBundle.getString("api.title"))
				.description(apiPropsBundle.getString("api.desc"))
				.contact(
						new Contact(
								apiPropsBundle.getString("contact.name"),
								apiPropsBundle.getString("contact.url"),
								apiPropsBundle.getString("contact.mail")
						)
				)
				.version("1.0")
				.build();
	}
}
