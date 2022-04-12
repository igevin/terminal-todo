package com.igevin.terminaltodo.core;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TerminalTodo {
    public void start() {
        System.out.println("请输入命令");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("exit")) {
            line = in.nextLine();
            System.out.println(line);
        }
    }
}
