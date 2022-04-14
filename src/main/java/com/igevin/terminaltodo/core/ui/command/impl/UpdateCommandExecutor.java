package com.igevin.terminaltodo.core.ui.command.impl;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.todo.CurrentTodoList;
import com.igevin.terminaltodo.core.ui.command.Command;
import com.igevin.terminaltodo.core.ui.command.CommandExecutor;
import com.igevin.terminaltodo.core.ui.command.impl.helper.CommandParseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component(Command.UPDATE_NAME)
public class UpdateCommandExecutor implements CommandExecutor {
    @Autowired
    private CurrentTodoList todoList;
    @Autowired
    private CommandParseHelper commandParseHelper;

    @Override
    public void execute(String line) {
        List<String> segments = commandParseHelper.getAsNSegments(line, 3);
        long id = Long.parseLong(segments.get(1));
        String content = segments.get(2);
        updateTask(id, content);
    }

    private void updateTask(long id, String content) {
        TodoTask task = todoList.getTodoList().getTaskById(id).orElse(null);
        if (task == null) {
            System.out.println("任务不存在");
            return;
        }
        task.modifyTask(content);
        System.out.println("任务已更新：" + task.toStringBrief());
    }
}
