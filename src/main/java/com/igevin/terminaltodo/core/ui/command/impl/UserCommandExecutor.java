package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.impl.helper.CommandParseHelper;
import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component(Command.USER_NAME)
public class UserCommandExecutor implements CommandExecutor {
    @Autowired
    private CommandParseHelper commandParseHelper;
    @Autowired
    private UserService userService;

    private final Map<String, Runnable> commandMap = new HashMap<>();
    private final Scanner in = new Scanner(System.in);

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
        System.out.println("请输入用户名：");
        String username = in.next().trim();
        System.out.println("请输入密码：");
        String password = in.next().trim();
        User user = userService.createUser(username, password);
        System.out.println("user created: " + user);
    }

    private void executeList() {
        System.out.println(userService.listUsers());
    }

    private void executeCurrent() {
        System.out.println(userService.getCurrentUser().orElse(null));
    }

    private void executeSwitch() {
        System.out.println("请输入用户名：");
        String username = in.next().trim();
        boolean succeed = userService.switchUser(username);
        if (succeed) {
            System.out.println("切换用户成功");
            return;
        }
        System.out.println("要切换的用户未登录");
    }

    private void executeLogin() {
        System.out.println("请输入用户名：");
        String username = in.next().trim();
        System.out.println("请输入密码：");
        String password = in.next().trim();
        if (userService.login(username, password)) {
            System.out.println("login succeed");
            return;
        }
        System.out.println("login failed");
    }

    private void executeLogout() {
        if (!userService.getCurrentUser().isPresent()) {
            return;
        }
        userService.logout(userService.getCurrentUser().get());
        System.out.println("logout succeed");
    }

    private void executeDefault() {
        System.out.println("unknown parameter");
    }
}
