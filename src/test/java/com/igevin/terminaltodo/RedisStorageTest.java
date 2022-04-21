package com.igevin.terminaltodo;

import com.igevin.terminaltodo.core.todo.TodoList;
import com.igevin.terminaltodo.core.todo.TodoTask;
import com.igevin.terminaltodo.supporting.serializer.Serializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisStorageTest {
    @Autowired
    private Serializer serializer;

    @Test
    public void testSerializer() {
        TodoList todoList = new TodoList("gevin");
        String value = serializer.serialize(todoList);
        System.out.println(value);
        TodoList list = serializer.deserialize(value, TodoList.class);
        System.out.println(list);
        TodoTask todoTask = new TodoTask("task1", todoList.getId());
        value = serializer.serialize(todoTask);
        System.out.println(value);
        TodoTask task = serializer.deserialize(value, TodoTask.class);
        System.out.println(task);
    }
}
