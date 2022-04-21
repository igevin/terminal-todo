package com.igevin.terminaltodo;

import com.igevin.terminaltodo.core.todo.TodoList;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoListTests {
    @Autowired
    @Qualifier("userTodoListService")
    private UserTodoListService todoListService;

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
}
