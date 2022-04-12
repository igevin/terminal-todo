package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
public class DeleteCommandExecutor implements CommandExecutor {
    @Override
    public void execute() {
        System.out.println("run delete command");
    }
}
