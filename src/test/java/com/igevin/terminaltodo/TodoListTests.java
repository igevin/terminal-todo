package com.igevin.terminaltodo;

import com.igevin.terminaltodo.core.todo.TodoList;
import com.igevin.terminaltodo.core.todo.TodoTask;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import com.igevin.terminaltodo.supporting.serializer.Serializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TodoListTests {
    @Autowired
    @Qualifier("userTodoListService")
    private UserTodoListService todoListService;
    @Autowired
    private Serializer serializer;

    @Test
    void contextLoads() {
    }

    @Test
    public void testExportTasks() {
        TodoList todoList = getTodoList();
        String filePath = "/tmp/data";
        todoList.exportTasks(filePath);
    }

    private TodoList getTodoList() {
        return todoListService.getDefaultTodoList("gevin");
    }

    @Test
    public void testLoadTasksFromFile() {
        String filePath = "/tmp/data";
        List<TodoTask> tasks = serializer.deserializeListFromFile(filePath, TodoTask.class);
        System.out.println(tasks);
    }

    @Test
    public void testOverwriteTasks() {
        TodoList todoList = getTodoList();
        String filePath = "/tmp/data";
        System.out.println(todoList.overwriteTasks(filePath));
    }

    @Test
    public void testImportTasks() {
        TodoList todoList = getTodoList();
        String filePath = "/tmp/data";
        System.out.println(todoList.importTasks(filePath));
    }
}
