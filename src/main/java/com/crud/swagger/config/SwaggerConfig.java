package com.crud.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // Swagger configuration for User API
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Users")  // Group name for Users API
                .apiInfo(apiInfo())  // Custom API information
                .select()
                .paths(PathSelectors.regex("/api/users.*"))  // Match all paths starting with /api/users
                .build();
    }

    // Swagger configuration for Address API
    @Bean
    public Docket addressApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Addresses")  // Group name for Address API
                .apiInfo(apiInfo())  // Custom API information
                .select()
                .paths(PathSelectors.regex("/api/addresses.*"))  // Match all paths starting with /api/addresses
                .build();
    }

    // API info for the Swagger documentation
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Test API")
                .description("Glenn Maxwell API documentation for Users and Addresses")
                .termsOfServiceUrl("")
                .license("GlennMaxwell Rest API License")
                .licenseUrl("")
                .version("1.0")
                .build();
    }
}