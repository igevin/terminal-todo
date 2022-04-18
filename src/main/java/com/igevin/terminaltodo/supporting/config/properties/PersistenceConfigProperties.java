package com.igevin.terminaltodo.supporting.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("terminal-todo.persistence")
public class PersistenceConfigProperties {
    private String storage = "mysql";
}
