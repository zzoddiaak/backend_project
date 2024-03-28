package org.example.repository.impl;

import org.example.annotations.Autowired;
import org.example.annotations.Component;
import org.example.entity.ParametersHolders;
import org.example.repository.Dbinterface;

@Component
public class Dbinterfaceimpl implements Dbinterface {
    @Autowired
    private ParametersHolders.ParametersHolder parametersHolder;

    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}