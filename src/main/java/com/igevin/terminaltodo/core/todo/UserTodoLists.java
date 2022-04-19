package com.igevin.terminaltodo.core.todo;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.event.UserCreateEvent;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@DependsOn(value = "eventBus")
@Getter
@Deprecated
public class UserTodoLists {
    @Autowired
    private EventBus eventBus;
    private final Map<User, TodoList> userTodoListMap = new HashMap<>(10);

    @PostConstruct
    private void init() {
        eventBus.register(this);
    }

    @Subscribe
    private void handUserCreateEvent(UserCreateEvent event) {
        TodoList todoList = new TodoList();
        userTodoListMap.put(event.getUser(), todoList);
    }
}
