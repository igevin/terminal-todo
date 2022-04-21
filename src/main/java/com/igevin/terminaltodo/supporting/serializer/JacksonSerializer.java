package com.igevin.terminaltodo.supporting.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.io.Files;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JacksonSerializer implements Serializer {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            // and possibly other configuration, modules, then:
            .build();

    @Override
    public String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(String data, Class<T> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SneakyThrows
    public void serializeToFile(Object object, String fileFullName) {
        File file = getOrCreateFile(fileFullName);

        objectMapper.writeValue(file, object);
    }

    @Override
    @SneakyThrows
    public <T> T deserializeFromFile(String fileFullName, Class<T> clazz) {
        File file = getOrCreateFile(fileFullName);
        return objectMapper.readValue(file, clazz);
    }

    @SneakyThrows
    private File getOrCreateFile(String fileFullName) {
        File file = new File(fileFullName);
        Files.createParentDirs(file);
        Files.touch(file);
        return file;
    }
}
