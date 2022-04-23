package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.todo.CurrentTodoList;
import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.impl.helper.CommandParseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(Command.EXPORT_NAME)
public class ExportCommandExecutor implements CommandExecutor {
    @Autowired
    private CurrentTodoList todoList;
    @Autowired
    private CommandParseHelper commandParseHelper;

    @Override
    public void execute(String line) {
        String fileFullName = getFileFullNameFromCommand(line);
        todoList.getTodoList().exportTasks(fileFullName);
        System.out.println("Succeed to export tasks");
    }

    private String getFileFullNameFromCommand(String line) {
        List<String> segments = commandParseHelper.getAsNSegments(line, 2);
        if (segments.size() < 2) {
            throw new RuntimeException("invalid parameters");
        }
        return segments.get(1);
    }
}
