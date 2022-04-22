package com.igevin.terminaltodo.supporting.serializer;

import java.util.List;

public interface Serializer {
    String serialize(Object object);
    <T> T deserialize(String data, Class<T> clazz);
    <T> List<T> deserializeList(String data, Class<T> clazz);
    void serializeToFile(Object object, String fileFullName);
    <T> T deserializeFromFile(String fileFullName, Class<T> clazz);
    <T> List<T> deserializeListFromFile(String fileFullName, Class<T> clazz);
}
