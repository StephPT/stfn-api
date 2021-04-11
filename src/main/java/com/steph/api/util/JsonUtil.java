package com.steph.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonUtil<T> {

    private static final ObjectMapper mapper = new ObjectMapper();

    public String jsonConverter(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString((T) object);
    }

    public T objectConverter(String json, Class<?> clazz) throws IOException {
        return (T) mapper.readValue(json, clazz);
    }

}
