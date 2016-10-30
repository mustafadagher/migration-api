package com.migration.flyway;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;
import static springfox.documentation.builders.PathSelectors.regex;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
    @Scope(scopeName = SCOPE_PROTOTYPE)
    public Flyway flyway() {
        return new Flyway();
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Migration").apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
                .paths(regex("/migration.*")).build();
    }

    private ApiInfo apiInfo() {

        final Contact contact = new Contact("Mustafa Dagher", "https://github.com/mustafadagher", "dagher.mustafa@gmail.com");
        return new ApiInfoBuilder().title("Migration API").description("A Service API to perform the flyway database migration").contact(contact)
                .version("0.0.2").build();
    }
}
