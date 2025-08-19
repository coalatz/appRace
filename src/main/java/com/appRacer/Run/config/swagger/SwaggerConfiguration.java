package com.appRacer.Run.config.swagger;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import aj.org.objectweb.asm.commons.Method;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import org.springdoc.core.customizers.OpenApiCustomizer;
import io.swagger.v3.oas.models.Operation;              
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.PathItem.HttpMethod;


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

    @Bean
    OpenApiCustomizer globalResponsesCustomizer() {
        return openApi -> {
            openApi.getPaths().forEach((pathUrl, pathItem) -> {
            	
                pathItem.readOperationsMap().forEach((httpMethod, operation) -> {

                    Content internalErrorContent = new Content()
                            .addMediaType("application/json",
                                    new MediaType().example("{ \"mensagem\": \"Internal server error\" }"));
                    operation.getResponses().addApiResponse("500",
                            new ApiResponse().description("Internal error").content(internalErrorContent));

                    if (httpMethod == PathItem.HttpMethod.PUT || httpMethod == PathItem.HttpMethod.PATCH || httpMethod == PathItem.HttpMethod.POST) {
                        Content validationErrorContent = new Content()
                                .addMediaType("application/json",
                                        new MediaType().example("{ \"mensagem\": \"Validation error\" }"));
                        operation.getResponses().addApiResponse("400",
                                new ApiResponse().description("Invalid request").content(validationErrorContent));
                    }
                });
            });
        };
    }
}