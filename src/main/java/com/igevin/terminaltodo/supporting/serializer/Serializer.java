package com.igevin.terminaltodo.supporting.serializer;

public interface Serializer {
    String serialize(Object object);
    <T> T deserialize(String data, Class<T> clazz);
}
