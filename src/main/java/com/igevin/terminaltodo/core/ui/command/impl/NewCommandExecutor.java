package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import org.springframework.stereotype.Component;

@Component(Command.NEW_NAME)
public class NewCommandExecutor implements CommandExecutor {
    @Override
    public void execute(String line) {
        System.out.println("run new command");
    }
}
