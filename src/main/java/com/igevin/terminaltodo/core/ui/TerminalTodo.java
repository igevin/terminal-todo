package com.igevin.terminaltodo.core.ui;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.CommandExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TerminalTodo {
    @Autowired
    private CommandExecutorFactory commandExecutorFactory;

    public void start() {
        System.out.println("请输入命令");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("exit")) {
            System.out.println(line + ":");
            Command command = Command.parseFromInput(line);
            CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
            commandExecutor.execute();
            line = in.nextLine();
        }
    }


}
