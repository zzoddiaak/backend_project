package org.example.factory;

import lombok.Setter;
import lombok.SneakyThrows;
import org.example.config.Config;
import org.example.config.ObjectConfigurator;
import org.example.config.impl.JavaConfig;
import org.example.annotations.Value;
import org.example.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {
    private static ObjectFactory objectFactory;

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    @SneakyThrows
    public ObjectFactory(ApplicationContext context){
        this.context = context;
        //config = new JavaConfig("org.example", new HashMap<>(Map.of()));
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }
@SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = create(implClass);

        configure(t);
        invokeInit(implClass, t);

    return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }
    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t,context));
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        if(implClass.getDeclaredConstructors().length == 1 && Arrays.stream(implClass.getDeclaredConstructors()).iterator().next().getParameterTypes().length == 0)
            return implClass.getDeclaredConstructor().newInstance();

        Constructor<?> constructor = Arrays.stream(implClass.getDeclaredConstructors()).iterator().next();
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++)
            parameters[i] = context.getObject(parameterTypes[i]);

        return (T) constructor.newInstance(parameters);
    }
}
