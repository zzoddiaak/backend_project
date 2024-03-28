package org.example.controller;

import org.example.annotations.Autowired;
import org.example.annotations.Component;
import org.example.service.Serviceinterface;

@Component
public class Controller {
    private Serviceinterface serviceInterface;



    @Autowired
    public Controller(Serviceinterface serviceInterface) {

        this.serviceInterface = serviceInterface;
    }

    public void execute(){
        System.out.println(serviceInterface.execute());
    }}
