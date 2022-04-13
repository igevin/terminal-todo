package com.igevin.terminaltodo.supporting.config;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.supporting.id.IdGenerator;
import com.igevin.terminaltodo.supporting.id.TimestampIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public IdGenerator getIdGenerator() {
        return new TimestampIdGenerator();
    }

    @Bean
    public TodoList getTodoList() {
        return new TodoList();
    }
}
