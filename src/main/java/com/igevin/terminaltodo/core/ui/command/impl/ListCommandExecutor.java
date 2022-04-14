package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.todo.CurrentTodoList;
import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(Command.LIST_NAME)
public class ListCommandExecutor implements CommandExecutor {
    @Autowired
    private CurrentTodoList todoList;

    @Override
    public void execute(String line) {
        Parameter param = parse(line);
        executeWithParam(param);
    }


    private Parameter parse(String line) {
        String[] params = line.toLowerCase().split(" ");
        if (params.length > 2) {
            throw new RuntimeException("命令错误，正确命令为 'list', 或 'list --all', 或 'list --checked', 或 'list --todo'");
        }
        String param = params.length == 1 ? "--unchecked" : params[1];
        return Parameter.fromValue(param);
    }

    private void executeWithParam(Parameter parameter) {
        switch (parameter) {
            case ALL:
                printTasks(todoList.getTodoList().listAllTasks());
                break;
            case CHECKED:
                printTasks(todoList.getTodoList().listCheckedTasks());
                break;
            default:
                printTasks(todoList.getTodoList().listUncheckedTasks());
                break;
        }
    }

    private void printTasks(List<TodoTask> tasks) {
        System.out.println("任务列表为：");
        for (TodoTask task : tasks) {
            System.out.println(task.toStringBrief());
        }
    }

    @AllArgsConstructor
    private enum Parameter {
        ALL("--all"),
        CHECKED("--checked"),
        UNCHECKED("--unchecked");
        private final String value;

        public static Parameter fromValue(String value) {
            for (Parameter param : values()) {
                if (param.value.equals(value)) {
                    return param;
                }
            }
            throw new RuntimeException("invalid list param");
        }
    }
}
