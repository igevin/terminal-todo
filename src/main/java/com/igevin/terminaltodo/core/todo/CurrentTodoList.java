package com.igevin.terminaltodo.core.todo;

import com.igevin.terminaltodo.core.TodoList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component("currentTodoList")
@Getter
public class CurrentTodoList {
    @Setter
    private TodoList todoList = new TodoList();
}
