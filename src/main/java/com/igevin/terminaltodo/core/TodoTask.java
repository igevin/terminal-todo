package com.igevin.terminaltodo.core;

import com.igevin.terminaltodo.supporting.ApplicationContextTool;
import com.igevin.terminaltodo.supporting.id.IdGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class TodoTask {
    private final Long id;
    private String content;
    private boolean checked;
    private final LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime;
    @Getter(AccessLevel.NONE)
    private IdGenerator idGenerator;

    private void init() {
        updateTime = createTime;
        idGenerator = ApplicationContextTool.getBeanByClass(IdGenerator.class);
    }

    public TodoTask(String content) {
        init();
        id = idGenerator.nextId();
        this.content = content;
        checked = false;
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
}
