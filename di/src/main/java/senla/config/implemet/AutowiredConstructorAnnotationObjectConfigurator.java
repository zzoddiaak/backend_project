package senla.config.implemet;

import lombok.SneakyThrows;
import senla.annotations.Autowired;
import senla.config.ObjectConfigurer;
import senla.context.ApplicationContext;

import java.lang.reflect.Field;

public class AutowiredConstructorAnnotationObjectConfigurator implements ObjectConfigurer {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);

                Object object = context.getObject(field.getType());

                field.set(t, object);
            }
        }
    }
}
