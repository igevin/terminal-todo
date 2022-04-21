package com.igevin.terminaltodo.core.todo.persistence.config;

import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoTaskService;
import com.igevin.terminaltodo.core.todo.persistence.impl.mysql.MysqlUserTodoListService;
import com.igevin.terminaltodo.core.todo.persistence.impl.mysql.MysqlUserTodoTaskService;
import com.igevin.terminaltodo.core.todo.persistence.impl.redis.RedisUserTodoListService;
import com.igevin.terminaltodo.core.todo.persistence.impl.redis.RedisUserTodoTaskService;
import com.igevin.terminaltodo.supporting.config.properties.PersistenceConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoPersistenceConfig {
    @Autowired
    private PersistenceConfigProperties persistenceConfigProperties;
    @Autowired
    private MysqlUserTodoListService mysqlUserTodoListService;
    @Autowired
    private RedisUserTodoListService redisUserTodoListService;
    @Autowired
    private MysqlUserTodoTaskService mysqlUserTodoTaskService;
    @Autowired
    private RedisUserTodoTaskService redisUserTodoTaskService;

    @Bean("userTodoListService")
    public UserTodoListService userTodoListService() {
        if ("mysql".equals(persistenceConfigProperties.getStorage())) {
            return mysqlUserTodoListService;

        }
        return redisUserTodoListService;
    }

    @Bean("userTodoTaskService")
    public UserTodoTaskService userTodoTaskService() {
        if ("mysql".equals(persistenceConfigProperties.getStorage())) {
            return mysqlUserTodoTaskService;

        }
        return redisUserTodoTaskService;
    }
}
