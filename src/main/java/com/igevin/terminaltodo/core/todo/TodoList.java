package com.igevin.terminaltodo.core.todo;

import com.igevin.terminaltodo.core.todo.persistence.TodoListEntity;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import com.igevin.terminaltodo.supporting.ApplicationContextTool;
import com.igevin.terminaltodo.supporting.id.IdGenerator;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
public class TodoList {
    private final Long id;
    private String username;
    private LocalDateTime createTime = LocalDateTime.now();
    private static final UserTodoListService userTodoListService;
    private static final IdGenerator idGenerator;

    static {
        idGenerator = ApplicationContextTool.getBeanByClass(IdGenerator.class);
        userTodoListService = ApplicationContextTool.getSpecificBean("userTodoListService", UserTodoListService.class);
    }

    public TodoList() {
//        tasks = new LinkedList<>();
        this.id = idGenerator.nextId();
    }

    public TodoList(String username) {
        this();
        this.username = username;
    }

    public TodoList(TodoListEntity entity) {
        id = entity.getId();
        username = entity.getUsername();
        createTime = entity.getCreateTime();
    }


//    public TodoTask addTask(TodoTask task) {
//        tasks.add(task);
//        return task;
//    }

    public TodoTask addTask(String content) {
//        return addTask(new TodoTask(content));
        return userTodoListService.addTask(content, id);
    }

    public Optional<TodoTask> getTaskById(long taskId) {
//        return tasks.stream().parallel()
//                .filter(x -> x.getId().equals(taskId))
//                .findAny();
        return userTodoListService.getTaskById(taskId);
    }

    public void removeTask(long taskId) {
//        TodoTask task = getTaskById(taskId).orElse(null);
//        tasks.remove(task);
        userTodoListService.removeTask(taskId);
    }


    public List<TodoTask> listAllTasks() {
//        return tasks;
        return userTodoListService.listAllTasks(id);
    }

    public List<TodoTask> listUncheckedTasks() {
//        return tasks.stream().filter(x -> !x.isChecked()).collect(Collectors.toList());
        return userTodoListService.listUncheckedTasks(id);
    }

    public List<TodoTask> listCheckedTasks() {
//        return tasks.stream().filter(TodoTask::isChecked).collect(Collectors.toList());
        return userTodoListService.listCheckedTasks(id);
    }

    public TodoListEntity toEntity() {
        TodoListEntity todoListEntity = new TodoListEntity();
        BeanUtils.copyProperties(this, todoListEntity);
        return todoListEntity;
    }


}
