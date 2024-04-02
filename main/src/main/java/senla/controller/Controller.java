package senla.controller;

import senla.annotations.Autowired;
import senla.annotations.Component;
import senla.service.ServiceInterface;

@Component
public class Controller {
    private ServiceInterface serviceInterface;

    @Autowired
    public Controller(ServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public void execute(){
        System.out.println(serviceInterface.execute());
    }
}
