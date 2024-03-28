package org.example.config.impl;

import lombok.SneakyThrows;
import org.example.annotations.Autowired;
import org.example.config.ObjectConfigurator;
import org.example.context.ApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AutowiredConstructorAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context){
        Method[] methods = t.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Autowired.class) && method.getName().startsWith("set") && method.getParameterTypes().length == 1) {
                method.setAccessible(true);

                Class<?> paramType = method.getParameterTypes()[0];
                Object param = context.getObject(paramType);

                method.invoke(t, param);
            }
        }
    }
}
