package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
public class CheckCommandExecutor implements CommandExecutor {
    @Override
    public void execute() {
        System.out.println("run check command");
    }
}
