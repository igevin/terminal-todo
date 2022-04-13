package com.igevin.terminaltodo.core.ui;

import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.CommandExecutorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TerminalTodo {
    @Autowired
    private CommandExecutorMapper commandExecutorMapper;

    public void start() {
        System.out.println("请输入命令...\n");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine().trim();

        while (!line.equals("exit")) {
            if (!nextLoop(line)) {
                runCommand(line);
            }
            line = in.nextLine().trim();
        }
    }

    private boolean nextLoop(String line) {
        return "".equals(line.trim());
    }

    private void runCommand(String line) {
        Command command = Command.parseFromInput(line);
        CommandExecutor commandExecutor = commandExecutorMapper.getCommandExecutor(command);
        try {
            commandExecutor.execute(line);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n\n请重新输入命令...");
        }
    }


}
