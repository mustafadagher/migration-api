package com.migration.flyway;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.migration.flyway.api.MigrationAPI;
import com.migration.flyway.facade.MigrationFacade;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Mustafa Dagher
 */
@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Scope(scopeName = SCOPE_REQUEST)
    public MigrationFacade migrationFacade() {
        return new MigrationAPI();
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Migration").apiInfo(apiInfo()).select().paths(regex("/migration.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Migration API").description("A Service API to perform the flyway database migration")
                .contact("Mustafa Dagher [dagher.mustafa@gmail.com]").build();
    }
}
