package com.igevin.terminaltodo.core.todo;

import com.igevin.terminaltodo.core.todo.persistence.TodoTaskEntity;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoTaskService;
import com.igevin.terminaltodo.supporting.ApplicationContextTool;
import com.igevin.terminaltodo.supporting.id.IdGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@ToString
public class TodoTask {
    private final Long id;
    private String content;
    private boolean checked;
    @Setter
    private Long listId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private static final IdGenerator idGenerator;
    private static final UserTodoTaskService todoTaskService;

    static {
        idGenerator = ApplicationContextTool.getBeanByClass(IdGenerator.class);
        todoTaskService = ApplicationContextTool.getSpecificBean("userTodoTaskService", UserTodoTaskService.class);
    }

    private void init() {
        createTime = LocalDateTime.now();
        updateTime = createTime;
    }

    public TodoTask(String content) {
        init();
        id = idGenerator.nextId();
        this.content = content;
        checked = false;
    }
    public TodoTask(String content, long listId) {
        this(content);
        this.listId = listId;
    }

    public TodoTask(TodoTaskEntity entity) {
        id = entity.getId();;
        content = entity.getContent();
        checked = entity.isChecked();
        listId = entity.getListId();
        createTime = entity.getCreateTime();
        updateTime = entity.getUpdateTime();
    }

    private void updateUpdateTime() {
        updateTime = LocalDateTime.now();
    }

    public String toStringBrief() {
        return String.format("id=%s, content=%s, isChecked=%s", id, content, checked);
    }

    private TodoTask changeTaskStatus(boolean checked) {
        updateUpdateTime();
        this.checked = checked;
        todoTaskService.updateTodoTask(this);
        return this;
    }

    public TodoTask checkTask() {
        return changeTaskStatus(true);
    }

    public TodoTask uncheckTask() {
        return changeTaskStatus(false);
    }

    public TodoTask modifyTask(String content) {
        updateUpdateTime();
        this.content = content;
        return this;
    }

    public TodoTask modifyTask(String content, boolean checked) {
        this.checked = checked;
        return modifyTask(content);
    }

    public TodoTaskEntity toEntity() {
        TodoTaskEntity entity = new TodoTaskEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}
