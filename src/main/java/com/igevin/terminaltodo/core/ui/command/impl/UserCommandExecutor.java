package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.impl.helper.CommandParseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(Command.USER_NAME)
public class UserCommandExecutor implements CommandExecutor {
    @Autowired
    private CommandParseHelper commandParseHelper;
    private final Map<String, Runnable> commandMap = new HashMap<>();

    {
        initCommandMap();
    }

    private void initCommandMap() {
        commandMap.put("default", this::executeDefault);
        commandMap.put("new", this::executeNew);
        commandMap.put("list", this::executeList);
        commandMap.put("current", this::executeCurrent);
        commandMap.put("switch", this::executeSwitch);
        commandMap.put("login", this::executeLogin);
        commandMap.put("logout", this::executeLogout);
    }

    @Override
    public void execute(String line) {
        System.out.println("user command");
        parseCommand(line);
    }

    private void parseCommand(String line) {
        List<String> segments = commandParseHelper.getAsNSegments(line, 3);
        if (segments.size() < 2) {
            throw new RuntimeException("invalid parameters");
        }
        String command = segments.get(1).toLowerCase();
        commandMap.getOrDefault(command, commandMap.get("default")).run();
    }

    private void executeNew() {
        System.out.println("new");
    }

    private void executeList() {
        System.out.println("list");
    }

    private void executeCurrent() {
        System.out.println("current");
    }

    private void executeSwitch() {
        System.out.println("switch");
    }

    private void executeLogin() {
        System.out.println("login");
    }

    private void executeLogout() {
        System.out.println("logout");
    }

    private void executeDefault() {
        System.out.println("unknown parameter");
    }
}
