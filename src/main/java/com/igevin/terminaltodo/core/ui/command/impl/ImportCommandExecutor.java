package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.todo.CurrentTodoList;
import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.impl.helper.CommandParseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(Command.IMPORT_NAME)
public class ImportCommandExecutor implements CommandExecutor {
    @Autowired
    private CurrentTodoList todoList;
    @Autowired
    private CommandParseHelper commandParseHelper;

    @Override
    public void execute(String line) {
        parseCommand(line);
        System.out.println("succeed to import tasks");
    }

    private void parseCommand(String line) {
        List<String> segments = commandParseHelper.getAsNSegments(line, 3);
        if (segments.size() < 2) {
            throw new RuntimeException("invalid parameters");
        }
        String param = segments.get(1).toLowerCase();
        String fileFullName;
        if ("--overwrite".equalsIgnoreCase(param)) {
            fileFullName = segments.get(2);
            todoList.getTodoList().overwriteTasks(fileFullName);
            return;
        }
        fileFullName = segments.get(1);
        todoList.getTodoList().importTasks(fileFullName);
    }
}
