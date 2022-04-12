package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import org.springframework.stereotype.Component;

@Component(Command.CHECK_NAME)
public class CheckCommandExecutor implements CommandExecutor {
    @Override
    public void execute() {
        System.out.println("run check command");
    }
}
