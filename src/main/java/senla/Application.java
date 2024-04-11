package senla;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.controller.*;


public class Application {
    public static void main(String[] args) {
        ApplicationContext context =new AnnotationConfigApplicationContext("senla");


        ClientCardController clientCardController = context.getBean(ClientCardController.class);
        clientCardController.deleteById(1L);
        clientCardController.update(2, """
                { 
                    "diagnosis" : "Стресс",
                    "recommendations" : "Лежать"
                }                                                }
                """);
        System.out.println("Client Card: \n" + clientCardController.findAll());

        CourseController courseController = context.getBean(CourseController.class);
        courseController.deleteById(1L);
        courseController.update(2, """
                { 
                    "courseName" : "Лечение депрессии",
                    "coursePrice" : 233.3343
                }                                                }
                """);
        System.out.println("Course: \n" + courseController.findAll());

        OrderController orderController = context.getBean(OrderController.class);
        orderController.deleteById(1L);
        orderController.update(2, """
                { 
                    "totalPrice" : 364.6003,
                    "discounts" : 131.6900
                }                                                }
                """);
        System.out.println("Order: \n" + orderController.findAll());

        PaymentController paymentController = context.getBean(PaymentController.class);
        paymentController.deleteById(1L);
        paymentController.update(2, """
                { 
                      "url" : "https://github.com/zzoddiaak/Senla/tree/homework-4-spring-ioc"
                    
                } 
                """);
        System.out.println("Payment: \n" + paymentController.findAll());

        PaymentStatusController paymentStatusController = context.getBean(PaymentStatusController.class);
        paymentStatusController.deleteById(1L);
        paymentStatusController.update(2, """
                { 
                    "paymentStatus" : "PENDING"                                  \s
                                                                  }                                                }
                """);
        System.out.println("Payment Status: \n" + paymentStatusController.findAll());

        PermissionController permissionController = context.getBean(PermissionController.class);
        permissionController.deleteById(1L);
        permissionController.update(2, """
                { 
                    "permissionName" : "DELETE"
                }                                                }
                """);
        System.out.println("Permission \n" + permissionController.findAll());

        PsychologistController psychologistController = context.getBean(PsychologistController.class);
        psychologistController.deleteById(1L);
        psychologistController.update(2, """
                {
                    "experience" : 39,
                    "hourlyRate" : 39.3,
                    "ratingValue" : 39                    
                }
                """);
        System.out.println("Psychologist: \n" +psychologistController.findAll());

        RequestStatusController requestStatusController = context.getBean(RequestStatusController.class);
        requestStatusController.deleteById(1L);
        requestStatusController.update(2, """
                {
                      "requestStatus" : "PENDING"
                    
                }
                """);
        System.out.println("Request Status: \n" + requestStatusController.findAll());

        ReviewController reviewController = context.getBean(ReviewController.class);
        reviewController.deleteById(1L);
        reviewController.update(2, """
                {
                    "rating" : 36,
                    "commentReviews" : "Nice"
                }
                """);
        System.out.println("Review: \n" + reviewController.findAll());

        RoleController roleController = context.getBean(RoleController.class);
        roleController.deleteById(1L);
        roleController.update(2, """
                {
                      "roleName" : "CLIENT"
                }
                """);
        System.out.println("Role: \n" + roleController.findById(2L));

        SessionRequestController sessionRequestController = context.getBean(SessionRequestController.class);
        sessionRequestController.deleteById(1L);
        sessionRequestController.update(2, """
                {
                      "requestDate" : "2024-04-17",
                      "problem" : "Депрессия"
                }
                """);
        System.out.println("Session Request: \n" + sessionRequestController.findAll());

        SessionController sessionController = context.getBean(SessionController.class);
        sessionController.deleteById(1L);
        sessionController.update(2, """
                {
                      "startSession" : "2024-04-17T08:35:00",
                      "finalSession" : "2024-05-12T09:30:00"
                }
                """);
        System.out.println("Session: \n" + sessionController.findAll());


        UserController userController = context.getBean(UserController.class);
        userController.deleteById(1L);
        userController.update(2, """
                {
                     "firstName": "John",
                     "secondName": "Savko",
                     "email": "savko@mail.ru"
                }
                """);
        System.out.println("User: \n" +userController.findAll());

    }
}