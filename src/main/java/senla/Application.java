package senla;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.controller.*;


public class Application {
    public static void main(String[] args) {
        ApplicationContext context =new AnnotationConfigApplicationContext("senla");



        Runnable sessionTask = () -> {
            SessionController sessionController = context.getBean(SessionController.class);
            sessionController.deleteById(1L);
            sessionController.update(2, """
                {
                      "startSession" : "2024-04-17T08:35:00",
                      "finalSession" : "2024-05-12T09:30:00"
                }
                """);
            System.out.println("Session: \n" + sessionController.findAll());

        };
        sessionTask.run();


    }
}