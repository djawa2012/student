package com.edu.student.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class JsonToPOJO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    public <T> Optional<T> mapResourceAsPOJO (String filename, Class<T> valueType) {
        try {
            T t = objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream(filename), valueType);
            return Optional.of(t);
        } catch (IOException e) {
            logger.warn("Unable to map " + filename, e);
        }
        return Optional.empty();
    }
}
