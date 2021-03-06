package com.igevin.terminaltodo.core.todo.persistence.impl.redis;

import com.igevin.terminaltodo.core.todo.TodoTask;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoTaskService;
import com.igevin.terminaltodo.supporting.serializer.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RedisUserTodoTaskService implements UserTodoTaskService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private Serializer serializer;
    private final String listPrefix = "todo::task::";
    private final String detailPrefix = "todo::task::detail";
    @Override
    public TodoTask createTodoTask(TodoTask todoTask) {
        addTaskToList(todoTask);
        saveTaskDetail(todoTask);
        return todoTask;
    }

    private void addTaskToList(TodoTask todoTask) {
        String key = listPrefix + todoTask.getListId();
        redisTemplate.opsForList().rightPush(key, todoTask.getId().toString());
    }

    private void saveTaskDetail(TodoTask todoTask) {
        String key = detailPrefix + todoTask.getId();
        String value = serializer.serialize(todoTask);
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public TodoTask updateTodoTask(TodoTask todoTask) {
        saveTaskDetail(todoTask);
        return todoTask;
    }

    @Override
    public Optional<TodoTask> getTaskById(long taskId) {
        String value = getTaskStr(taskId);
        return value == null ? Optional.empty() : Optional.of(deserializeTask(value));
    }

    private String getTaskStr(long taskId) {
        String key = detailPrefix + taskId;
        return redisTemplate.opsForValue().get(key);
    }

    private TodoTask deserializeTask(String taskStr) {
        return serializer.deserialize(taskStr, TodoTask.class);
    }

    @Override
    public void removeTask(long taskId) {
        String todoTaskStr = getTaskStr(taskId);
        if (todoTaskStr == null) {
            return;
        }
        TodoTask todoTask = deserializeTask(todoTaskStr);
        String detailKey = detailPrefix + taskId;
        redisTemplate.delete(detailKey);
        String listKey = listPrefix + todoTask.getListId();
        redisTemplate.opsForList().remove(listKey, 1, String.valueOf(taskId));
    }

    @Override
    public List<TodoTask> listTasks(long todoListId, Boolean checked) {
//        String key = listPrefix + todoListId;
//        Long size = redisTemplate.opsForList().size(key);
//        if (size == null) {
//            return null;
//        }
//        List<String> taskIds = redisTemplate.opsForList().range(key, 0, size - 1);
//        if (taskIds == null) {
//            return null;
//        }
//        List<TodoTask> tasks =taskIds.stream().map(x -> getTaskStr(Long.parseLong(x)))
//                .filter(Objects::nonNull)
//                .map(this::deserializeTask)
//                .collect(Collectors.toList());
        List<Long> taskIds = getTaskIds(todoListId);
        List<TodoTask> tasks =taskIds.stream().map(this::getTaskStr)
                .filter(Objects::nonNull)
                .map(this::deserializeTask)
                .collect(Collectors.toList());

        if (checked == null) {
            return tasks;
        }
        return tasks.stream().filter(x -> x.isChecked() == checked).collect(Collectors.toList());
    }

    private List<Long> getTaskIds(long todoListId) {
        String key = listPrefix + todoListId;
        Long size = redisTemplate.opsForList().size(key);
        if (size == null) {
            return new ArrayList<>();
        }
        List<String> taskIds = redisTemplate.opsForList().range(key, 0, size - 1);
        if (taskIds == null) {
            return new ArrayList<>();
        }
        return taskIds.stream().map(Long::parseLong).collect(Collectors.toList());
    }

    @Override
    public void clearTasks(long todoListId) {
        List<Long> taskIds = getTaskIds(todoListId);
        taskIds.forEach(this::removeTask);

    }

    @Override
    public void addTasks(List<TodoTask> tasks, long todoListId) {
        tasks.forEach(this::createTodoTask);
    }

    @Override
    public void addOrUpdateTasks(List<TodoTask> tasks, long todoListId) {
        Set<Long> taskIdSet = new HashSet<>(getTaskIds(todoListId));
        tasks.forEach(task -> {
            if (taskIdSet.contains(task.getId())) {
                this.updateTodoTask(task);
                return;
            }
            createTodoTask(task);
        });
    }

}
