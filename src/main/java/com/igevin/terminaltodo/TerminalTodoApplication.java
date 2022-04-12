package com.igevin.terminaltodo;

import com.igevin.terminaltodo.core.ui.TerminalTodo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class TerminalTodoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TerminalTodoApplication.class, args);
        TerminalTodo todo = context.getBean(TerminalTodo.class);
        todo.start();
    }



}
