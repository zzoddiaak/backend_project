package org.example;

import org.example.context.ApplicationContext;
import org.example.controller.Controller;
import org.example.entity.ParametersHolders;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = Application.run("org.example.main");

        ParametersHolders.ParametersHolder holder = new ParametersHolders().new ParametersHolder();
        holder.printSomeText(); // Этот метод должен вывести "Hello, World!"

        Controller controller = context.getObject(Controller.class);

        controller.execute();

    }
}