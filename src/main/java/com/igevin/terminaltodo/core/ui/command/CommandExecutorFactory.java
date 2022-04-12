package com.igevin.terminaltodo.core.ui.command;

import com.igevin.terminaltodo.core.ui.command.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutorFactory {
    @Autowired
    private UnknownCommandExecutor unknownCommandExecutor;
    @Autowired
    private ListCommandExecutor listCommandExecutor;
    @Autowired
    private NewCommandExecutor newCommandExecutor;
    @Autowired
    private CheckCommandExecutor checkCommandExecutor;
    @Autowired
    private UnCheckCommandExecutor unCheckCommandExecutor;
    @Autowired
    private UpdateCommandExecutor updateCommandExecutor;
    @Autowired
    private DeleteCommandExecutor deleteCommandExecutor;

    public CommandExecutor getCommandExecutor(Command command) {
        switch (command) {
            case NEW:
                return newCommandExecutor;
            case LIST:
                return listCommandExecutor;
            case CHECK:
                return checkCommandExecutor;
            case UPDATE:
                return updateCommandExecutor;
            case UNCHECK:
                return unCheckCommandExecutor;
            case DELETE:
                return deleteCommandExecutor;
            default:
                return unknownCommandExecutor;
        }
    }
}
