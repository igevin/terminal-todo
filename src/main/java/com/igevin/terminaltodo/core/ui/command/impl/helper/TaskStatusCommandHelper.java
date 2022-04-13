package com.igevin.terminaltodo.core.ui.command.impl.helper;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.TodoTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskStatusCommandHelper {
    @Autowired
    private TodoList todoList;

    public TaskStatusCommandHelper(TodoList todoList) {
        this.todoList = todoList;
    }

    public void execute(String line, boolean checked) {
        long taskId = parseTaskId(line);
        changeTaskStatus(taskId, checked);
        System.out.println("任务状态已修改为： " + (checked ? "已完成" : "未完成"));
    }

    private long parseTaskId(String line) {
        int firstSpace = line.indexOf(" ");
        if (firstSpace == -1 || firstSpace == line.length() - 1) {
            return 0;
        }
        return Long.parseLong(line.substring(firstSpace + 1).trim());
    }

    private void changeTaskStatus(long taskId, boolean checked) {
        Optional<TodoTask> todoTask = todoList.getTaskById(taskId);
        if (!todoTask.isPresent()) {
            return;
        }
        if (checked) {
            todoTask.get().checkTask();
            return;
        }
        todoTask.get().uncheckTask();
    }
}
