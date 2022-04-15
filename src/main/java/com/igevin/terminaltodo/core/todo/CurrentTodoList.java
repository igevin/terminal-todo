package com.igevin.terminaltodo.core.todo;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.user.event.UserSwitchEvent;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("currentTodoList")
@DependsOn(value = "eventBus")
@Getter
public class CurrentTodoList {
    @Autowired
    private EventBus eventBus;
    @Autowired
    private UserTodoLists userTodoLists;

    private final TodoList anonymousTodoList = new TodoList();
    private TodoList todoList = anonymousTodoList;

    @PostConstruct
    private void init() {
        eventBus.register(this);
    }

    @Subscribe
    private void handUserSwitchEvent(UserSwitchEvent event) {
        this.todoList = userTodoLists.getUserTodoListMap().getOrDefault(event.getUser(), anonymousTodoList);
    }
}
