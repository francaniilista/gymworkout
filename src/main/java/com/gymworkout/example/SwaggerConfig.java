package com.gymworkout.example;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pfranca on 8/16/2016.
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {
    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    private ApiInfo getApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Gym Workout REST API")
                .description("Gym Workout Api for creating and managing workout plan")
                .termsOfServiceUrl("http://example.com/terms-of-service")
                .contact("francaniilista@gmail.com")
                .license("MIT License")
                .licenseUrl("http://opensource.org/licenses/MIT")
                .build();
        return apiInfo;
    }

    @Bean
    public SwaggerSpringMvcPlugin apiConfiguration() {
        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
        swaggerSpringMvcPlugin
            .apiInfo(getApiInfo())
            .apiVersion("1.0")
            .includePatterns("/api/*.*")
            .swaggerGroup("api");

        swaggerSpringMvcPlugin.useDefaultResponseMessages(false);
        return swaggerSpringMvcPlugin;
    }
}