package org.example.config.impl;

import lombok.SneakyThrows;
import org.example.annotations.Component;
import org.example.config.ObjectConfigurator;
import org.example.factory.ObjectFactory;

import java.lang.reflect.Field;
import java.util.Objects;

public class ComponentAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t){
        for (Field field : t.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(Component.class)){
                field.setAccessible(true);
                Object object = ObjectFactory.getInstance().createObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
