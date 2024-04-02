package senla.service.impl;

import senla.annotations.Autowired;
import senla.annotations.Component;
import senla.repository.DatabaseInterface;
import senla.service.ServiceInterface;

@Component
public class ServiceInterfaceImpl implements ServiceInterface {
    private DatabaseInterface databaseInterface;

    @Autowired
    public void setDatabaseInterface(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public String execute() {
        return databaseInterface.execute();
    }
}
