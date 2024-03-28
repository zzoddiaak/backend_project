package org.example.config;

import org.example.context.ApplicationContext;
import org.example.factory.ObjectFactory;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);

}
