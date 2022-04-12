package com.igevin.terminaltodo.core.ui.command;

import com.igevin.terminaltodo.core.ui.command.impl.UnknownCommandExecutor;
import com.igevin.terminaltodo.supporting.ApplicationContextTool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutorMapper {
    @Autowired
    private UnknownCommandExecutor unknownCommandExecutor;

    public CommandExecutor getCommandExecutor(Command command) {
        try {
            return ApplicationContextTool.getSpecificBean(command.name(), CommandExecutor.class);
        } catch (BeansException e) {
            return unknownCommandExecutor;
        }
    }
}
