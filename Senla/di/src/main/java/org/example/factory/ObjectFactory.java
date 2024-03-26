package org.example.factory;

import lombok.SneakyThrows;
import org.example.config.Config;
import org.example.config.ObjectConfigurator;
import org.example.config.impl.JavaConfig;
import org.example.annotations.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private Config config;
    public static ObjectFactory getInstance(){return ourInstance;}
    @SneakyThrows
    private ObjectFactory(){
        config = new JavaConfig("org.example", new HashMap<>(Map.of()));
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }
@SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()){
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(ObjectConfigurator -> ObjectConfigurator.configure(t));

        return t;
    }
}
