package org.example.entity;

import lombok.Getter;
import org.example.annotations.Component;
import org.example.annotations.Value;

@Component
public class ParametersHolders {
    @Component
    public class ParametersHolder {
        @Getter
        @Value("parameters.holder")
        private String someText;

        public void printSomeText() {
            System.out.println("Value of someText: " + someText);
        }
    }
}
