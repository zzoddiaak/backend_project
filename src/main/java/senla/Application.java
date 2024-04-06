package senla;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.controller.*;


public class Application {
    public static void main(String[] args) {
        ApplicationContext context =new AnnotationConfigApplicationContext("senla");

        UserController userController = context.getBean(UserController.class);
        userController.deleteById(1L);
        System.out.println("User: \n" +userController.findAll());

        PsychologistController psychologistController = context.getBean(PsychologistController.class);
        psychologistController.deleteById(2L);
        System.out.println("Psychologist: \n" +psychologistController.findAll());

        RoleController roleController = context.getBean(RoleController.class);
        System.out.println("Role: \n" + roleController.findById(2L));

        ClientCardController clientCardController = context.getBean(ClientCardController.class);
        System.out.println("Client Card: \n" + clientCardController.findAll());

        CourseController courseController = context.getBean(CourseController.class);
        System.out.println("Course: \n" + courseController.findById(1L));

        OrderController orderController = context.getBean(OrderController.class);
        psychologistController.deleteById(1L);
        System.out.println("Order: \n" + orderController.findAll());

        PaymentStatusController paymentStatusController = context.getBean(PaymentStatusController.class);
        System.out.println("Payment Status: \n" + paymentStatusController.findAll());

        RequestStatusController requestControllerController = context.getBean(RequestStatusController.class);
        System.out.println("Request Status: \n" + requestControllerController.findById(1L));

        ReviewController reviewController = context.getBean(ReviewController.class);
        reviewController.deleteById(2L);
        System.out.println("Review: \n" + reviewController.findAll());

        SessionController sessionController = context.getBean(SessionController.class);
        System.out.println("Session: \n" + sessionController.findAll());





    }
}