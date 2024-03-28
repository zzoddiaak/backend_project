package org.example.config.impl;

import lombok.SneakyThrows;
import org.example.annotations.Component;
import org.example.config.ObjectConfigurator;
import org.example.context.ApplicationContext;
import org.example.factory.ObjectFactory;

import java.lang.reflect.Field;

public class AutowiredAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context){
        for (Field field : t.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(Component.class)){
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
