package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.impl.helper.TaskStatusCommandHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(Command.CHECK_NAME)
public class CheckCommandExecutor implements CommandExecutor {
    @Autowired
    private TaskStatusCommandHelper taskStatusCommandHelper;

    @Override
    public void execute(String line) {
        taskStatusCommandHelper.execute(line, true);
    }
}
