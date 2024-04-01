package senla.repository.impl;

import senla.annotations.Autowired;
import senla.annotations.Component;
import senla.entity.ParametersHolder;
import senla.repository.DatabaseInterface;

@Component
public class DatabaseInterfaceImpl implements DatabaseInterface {
    @Autowired
    private ParametersHolder parametersHolder;

    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}
