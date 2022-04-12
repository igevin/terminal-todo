package com.igevin.terminaltodo.supporting;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextTool implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBeanByClass(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    public static Object getBeanByName(String name) {
        return context.getBean(name);
    }

    public static <T> T getSpecificBean(String name, Class<T> beanClass) {
        return context.getBean(name, beanClass);
    }
}
