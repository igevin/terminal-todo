package com.igevin.terminaltodo.supporting.serializer;

public interface Serializer {
    String serialize(Object object);
    <T> T deserialize(String data, Class<T> clazz);
    void serializeToFile(Object object, String fileFullName);
    <T> T deserializeFromFile(String fileFullName, Class<T> clazz);
}
