package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
public class UnknownCommandExecutor implements CommandExecutor {
    @Override
    public void execute() {
        System.out.println("unknown command, ignore it");
    }
}
