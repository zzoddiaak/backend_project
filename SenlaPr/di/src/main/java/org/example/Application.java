package org.example;

import org.example.config.impl.JavaConfig;
import org.example.context.ApplicationContext;
import org.example.factory.ObjectFactory;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static ApplicationContext run(String packageToScan){
        JavaConfig config = new JavaConfig(packageToScan, new HashMap<>());
        ApplicationContext context = ApplicationContext.getInstance(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        return  context;
    }
}
