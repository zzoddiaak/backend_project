package senla.config.implemet;

import lombok.SneakyThrows;
import senla.annotations.Autowired;
import senla.config.ObjectConfigurer;
import senla.context.ApplicationContext;

import java.lang.reflect.Method;

public class AutowiredSetterAnnotationObjectConfigurator implements ObjectConfigurer {
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
