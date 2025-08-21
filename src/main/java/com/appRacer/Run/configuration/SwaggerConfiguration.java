package com.appRacer.Run.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfiguration {

    @Bean
    OpenAPI customOpenAPI() {
    	return new OpenAPI()
    			.info(new Info()
    					.title("Project APP Race")
    					.version("1.0.0")
    					.description("api for testing the racing application")
    					);
	}
}
