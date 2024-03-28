package senla.config;

import senla.context.ApplicationContext;

public interface ObjectConfigurer {
    void configure(Object t, ApplicationContext context);
}
