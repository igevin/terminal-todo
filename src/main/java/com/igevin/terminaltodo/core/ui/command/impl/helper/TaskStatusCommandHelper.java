package com.igevin.terminaltodo.core.ui.command.impl.helper;

import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.todo.CurrentTodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskStatusCommandHelper {
    @Autowired
    private CurrentTodoList todoList;
    @Autowired
    private CommandParseHelper commandParseHelper;


    public void execute(String line, boolean checked) {
        long taskId = commandParseHelper.parseTaskId(line);
        Optional<TodoTask> todoTask = todoList.getTodoList().getTaskById(taskId);
        if (!todoTask.isPresent()) {
            System.out.println("任务不存在");
            return;
        }
        changeTaskStatus(todoTask.get(), checked);
        System.out.println("任务状态已修改为： " + (checked ? "已完成" : "未完成"));
    }

    private void changeTaskStatus(TodoTask task, boolean checked) {
        if (checked) {
            task.checkTask();
            return;
        }
        task.uncheckTask();
    }
}
