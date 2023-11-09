package com.krglow.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    @Configuration
    public class SpringdocConfig {

        @Bean
        OpenAPI customOpenAPI() {
            return new OpenAPI().info(new Info().title("task-manager").version("1.0").description("Aplikacja do zarządzania zadaniami"));
        }
    }

}
