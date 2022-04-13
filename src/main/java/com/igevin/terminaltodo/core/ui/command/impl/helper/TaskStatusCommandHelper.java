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
    @Autowired
    private CommandParseHelper commandParseHelper;

    public TaskStatusCommandHelper(TodoList todoList) {
        this.todoList = todoList;
    }

    public void execute(String line, boolean checked) {
        long taskId = commandParseHelper.parseTaskId(line);
        changeTaskStatus(taskId, checked);
        System.out.println("任务状态已修改为： " + (checked ? "已完成" : "未完成"));
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
