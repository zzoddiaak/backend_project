package senla;

import senla.Application;
import senla.context.ApplicationContext;
import senla.controller.Controller;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("senla");

        Controller controller = context.getObject(Controller.class);
        controller.execute();

    }
}
