package org.example.config.impl;

import lombok.SneakyThrows;
import org.example.annotations.Value;
import org.example.config.ObjectConfigurator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ValueAnnotationObjectConfigurator implements ObjectConfigurator {
    private Map<String, String> propertiesMap;
    @SneakyThrows
    public ValueAnnotationObjectConfigurator(){
        String path =  ClassLoader.getSystemClassLoader().getResource("application.propetries").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line->line.split("=")).collect(toMap(arr->arr[0], arr->arr[1]));

    }
    @Override
    @SneakyThrows
    public void configure(Object t) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()){
            Value annotation = field.getAnnotation(Value.class);

            if (annotation != null){
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }
}
