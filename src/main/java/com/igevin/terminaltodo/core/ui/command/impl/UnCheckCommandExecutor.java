package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import org.springframework.stereotype.Component;

@Component(Command.UNCHECK_NAME)
public class UnCheckCommandExecutor implements CommandExecutor {
    @Override
    public void execute(String line) {
        System.out.println("run uncheck command");
    }
}
