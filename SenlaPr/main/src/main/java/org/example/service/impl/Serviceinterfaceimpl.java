package org.example.service.impl;

import org.example.annotations.Autowired;
import org.example.annotations.Component;
import org.example.repository.Dbinterface;
import org.example.service.Serviceinterface;

@Component
public class Serviceinterfaceimpl implements Serviceinterface {
    private Dbinterface databaseInterface;

    @Autowired
    public void setDatabaseInterface(Dbinterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public String execute() {
        return databaseInterface.execute();
    }
}