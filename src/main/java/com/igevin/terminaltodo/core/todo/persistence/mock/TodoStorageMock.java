package com.igevin.terminaltodo.core.todo.persistence.mock;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.todo.UserTodoLists;
import com.igevin.terminaltodo.core.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TodoStorageMock {
    @Autowired
    private UserTodoLists userTodoLists;
//    private final TodoList anonymousTodoList = new TodoList();
    private final List<TodoTask> tasks = new LinkedList<>();

    public TodoList getUserDefaultTodoList(User user) {
        return userTodoLists.getUserTodoListMap().getOrDefault(user, null);
    }


    public TodoTask addTask(TodoTask task) {
        tasks.add(task);
        return task;
    }

    public TodoTask addTask(String content) {
        return addTask(new TodoTask(content));
    }

    public Optional<TodoTask> getTaskById(long taskId) {
        return tasks.stream().parallel()
                .filter(x -> x.getId().equals(taskId))
                .findAny();
    }

    public void removeTask(long taskId) {
        TodoTask task = getTaskById(taskId).orElse(null);
        tasks.remove(task);
    }


    public List<TodoTask> listAllTasks() {
        return tasks;
    }

    public List<TodoTask> listUncheckedTasks() {
        return tasks.stream().filter(x -> !x.isChecked()).collect(Collectors.toList());
    }

    public List<TodoTask> listCheckedTasks() {
        return tasks.stream().filter(TodoTask::isChecked).collect(Collectors.toList());
    }
}
