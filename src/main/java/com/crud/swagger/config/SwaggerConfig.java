package com.crud.swagger.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
//Swagger UI: http://localhost:8080/api/swagger-ui/index.html
//OpenAPI Docs: http://localhost:8080/api/v3/api-docs
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GlennMaxwell API")
                        .description("Glenn Maxwell API documentation for Users and Addresses")
                        .version("1.0")
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("GlennMaxwell Rest API License")
                                .url("https://www.glennmaxwell.com/license"))
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Support")
                                .email("support@glennmaxwell.com")
                                .url("https://www.glennmaxwell.com/support"))
                );
    }

}
