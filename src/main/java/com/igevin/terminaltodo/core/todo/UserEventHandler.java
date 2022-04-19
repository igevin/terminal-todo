package com.igevin.terminaltodo.core.todo;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import com.igevin.terminaltodo.core.user.event.UserCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn(value = {"eventBus", "userTodoListService"})
public class UserEventHandler {
    @Autowired
    private EventBus eventBus;
    @Autowired
    private UserTodoListService userTodoListService;

    @PostConstruct
    private void init() {
        eventBus.register(this);
    }

    @Subscribe
    private void handUserCreateEvent(UserCreateEvent event) {
        userTodoListService.createTodoList(new TodoList(event.getUser().getUsername()));
    }
}
