package senla.config.implemet;

import lombok.SneakyThrows;
import senla.annotations.Value;
import senla.config.ObjectConfigurer;
import senla.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ValueAnnotationObjectConfigurator implements ObjectConfigurer {
    private final PropertyManager propertyManager = PropertyManager.getInstance();

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);

            if (annotation != null) {
                String value = annotation.value().isEmpty() ? propertyManager.getProperty(field.getName()) : propertyManager.getProperty(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }
}
