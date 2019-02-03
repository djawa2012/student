package com.edu.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsondb.JsonDBTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.edu.student")
public class Config {

    private static final String DB_PATH = "/tmp/students.db";

    @Bean
    public JsonDBTemplate jsonDBTemplate () {
        return new JsonDBTemplate(DB_PATH, "com.edu.student");
    }

    @Bean
    public ObjectMapper objectMapper () {
        return new ObjectMapper();
    }
}
