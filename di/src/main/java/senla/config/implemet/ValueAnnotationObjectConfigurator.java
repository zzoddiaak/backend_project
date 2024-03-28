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
    private Properties appProps;

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        if (appProps == null) {
            loadAppProperties(context);
        }

        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);

            if (annotation != null) {
                String value = annotation.value().isEmpty() ? appProps.getProperty(field.getName()) : appProps.getProperty(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }

    @SneakyThrows
    private void loadAppProperties(ApplicationContext context) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";

        appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
    }
}
