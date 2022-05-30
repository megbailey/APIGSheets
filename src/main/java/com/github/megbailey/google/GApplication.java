package com.github.megbailey.google;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/*
    This is the main thread of the execution.
 */
@SpringBootApplication
public class GApplication {
    private static GLogHandler logger = new GLogHandler();

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(GApplication.class, args);
    }



    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }


        };
    }
}
