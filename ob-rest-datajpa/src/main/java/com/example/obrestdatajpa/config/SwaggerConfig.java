package com.example.obrestdatajpa.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/*
 * Configuracion para la generacion de documentacion de la API REST
 * http://localhost:3000/swagger-ui/
 */
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo("Spring Boot Book API REST", 
				"Library Api rest docs" , 
				"1.0", 
				"https://www.google.com", 
				new Contact("Alan", "https://www.google.com", "alan@mail.com"), 
				"MIT", 
				"https://www.google.com", 
				Collections.EMPTY_LIST);
	}
}
