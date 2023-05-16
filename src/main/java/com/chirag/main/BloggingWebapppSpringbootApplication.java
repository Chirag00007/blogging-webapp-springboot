package com.chirag.main;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.chirag.main")
@EnableJpaRepositories(basePackages = "com.chirag.main.repositiories")
public class BloggingWebapppSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingWebapppSpringbootApplication.class, args);

    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
