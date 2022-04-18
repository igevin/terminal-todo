package com.igevin.terminaltodo.core.user.persistence.config;

import com.igevin.terminaltodo.core.user.persistence.UserStorageService;
import com.igevin.terminaltodo.core.user.persistence.impl.MysqlUserStorageService;
import com.igevin.terminaltodo.core.user.persistence.impl.RedisUserStorageService;
import com.igevin.terminaltodo.supporting.config.properties.PersistenceConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPersistenceConfig {
    @Autowired
    private PersistenceConfigProperties persistenceConfigProperties;
    @Autowired
    private MysqlUserStorageService mysqlUserStorageService;
    @Autowired
    private RedisUserStorageService redisUserStorageService;

    @Bean("userStorageService")
    public UserStorageService userStorageService() {
        if ("mysql".equals(persistenceConfigProperties.getStorage())) {
            return mysqlUserStorageService;

        }
        return redisUserStorageService;
    }
}
