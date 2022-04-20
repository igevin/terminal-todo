package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.todo.TodoTask;
import com.igevin.terminaltodo.core.todo.CurrentTodoList;
import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(Command.ADD_NAME)
public class AddCommandExecutor implements CommandExecutor {
    @Autowired
    private CurrentTodoList todoList;

    @Override
    public void execute(String line) {
        System.out.println("run add task command");
        String content = parseTaskContent(line);
        TodoTask task = todoList.getTodoList().addTask(content);
        System.out.println("新增任务：" + task.toStringBrief());
    }

    private String parseTaskContent(String line) {
        int firstSpace = line.indexOf(" ");
        if (firstSpace == -1 || firstSpace == line.length() - 1) {
            return "";
        }
        return line.substring(firstSpace + 1).trim();
    }
}
