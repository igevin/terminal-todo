package com.igevin.terminaltodo.supporting.config;

import com.google.common.eventbus.EventBus;
import com.igevin.terminaltodo.supporting.encryption.Encryption;
import com.igevin.terminaltodo.supporting.encryption.impl.Sha512Encryption;
import com.igevin.terminaltodo.supporting.id.AtomicIdGenerator;
import com.igevin.terminaltodo.supporting.id.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public IdGenerator getIdGenerator() {
//        return new TimestampIdGenerator();
        return new AtomicIdGenerator();
    }
    @Bean
    public Encryption encryption() {
        return new Sha512Encryption();
    }

    @Bean(name = "eventBus")
    public EventBus eventBus() {
        return new EventBus();
    }
}
