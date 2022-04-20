package com.igevin.terminaltodo.core.todo.persistence.impl.mysql;

import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.todo.persistence.TodoTaskEntity;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MysqlUserTodoTaskService implements UserTodoTaskService {
    @Autowired
    private TodoTaskMapper todoTaskMapper;
    @Override
    public TodoTask createTodoTask(TodoTask todoTask) {
        todoTaskMapper.createTodoTask(todoTask.toEntity());
        return todoTask;
    }

    @Override
    public TodoTask updateTodoTask(TodoTask todoTask) {
        todoTaskMapper.updateTodoTask(todoTask.toEntity());
        return todoTask;
    }

    @Override
    public Optional<TodoTask> getTaskById(long taskId) {
        TodoTaskEntity entity = todoTaskMapper.getById(taskId);
        return entity == null ? Optional.empty() : Optional.of(new TodoTask(entity));
    }

    @Override
    public void removeTask(long taskId) {
        todoTaskMapper.removeTodoTaskById(taskId);
    }

    @Override
    public List<TodoTask> listTasks(long todoListId, Boolean checked) {
        List<TodoTaskEntity> entities = todoTaskMapper.listTasks(todoListId, checked);
        return entities.stream().map(TodoTask::new).collect(Collectors.toList());
    }

}
