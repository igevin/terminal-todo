package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.todo.CurrentTodoList;
import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.impl.helper.CommandParseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = Command.DELETE_NAME)
public class DeleteCommandExecutor implements CommandExecutor {
    @Autowired
    private CurrentTodoList todoList;
    @Autowired
    private CommandParseHelper commandParseHelper;

    @Override
    public void execute(String line) {
        long id = commandParseHelper.parseTaskId(line);
        todoList.getTodoList().removeTask(id);
        System.out.println("已移除任务");
    }
}
