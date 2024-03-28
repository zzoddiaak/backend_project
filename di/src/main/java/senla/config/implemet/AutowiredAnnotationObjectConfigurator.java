package senla.config.implemet;

import lombok.SneakyThrows;
import senla.annotations.Autowired;
import senla.config.ObjectConfigurer;
import senla.context.ApplicationContext;

import java.lang.reflect.Constructor;

public class AutowiredAnnotationObjectConfigurator implements ObjectConfigurer {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        Constructor<?>[] constructors = t.getClass().getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(Autowired.class) && constructor.getParameterTypes().length != 0) {
                constructor.setAccessible(true);

                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];

                for (int i = 0; i < parameterTypes.length; i++)
                    parameters[i] = context.getObject(parameterTypes[i]);

                constructor.newInstance(parameters);
                break;
            }
        }
    }
}