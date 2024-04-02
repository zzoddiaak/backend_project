package senla.entity;

import senla.annotations.Component;
import senla.annotations.Value;
import lombok.Getter;

@Component
public class ParametersHolder {
    @Getter
    @Value("parameters.holder.sometext")
    private String someText;
}
