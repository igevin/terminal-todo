package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import org.springframework.stereotype.Component;

@Component(Command.UNKNOWN_NAME)
public class UnknownCommandExecutor implements CommandExecutor {
    @Override
    public void execute(String line) {
        System.out.println("unknown command, ignore it");
    }
}
