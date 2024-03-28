package org.example.config.impl;

import lombok.SneakyThrows;
import org.example.annotations.Autowired;
import org.example.config.ObjectConfigurator;
import org.example.context.ApplicationContext;

import java.lang.reflect.Method;

public class AutowiredSetterAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Method method : t.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Autowired.class) && method.getName().startsWith("set") && method.getParameterCount() == 1) {
                Class<?> parameterType = method.getParameterTypes()[0];
                Object object = context.getObject(parameterType);
                method.invoke(t, object);
            }
        }
    }
}
