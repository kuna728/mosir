package pl.edu.wat.student.i9g1s1.mosir.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.unak7.peselvalidator.PeselValidator;
import pl.unak7.peselvalidator.PeselValidatorImpl;

@Component
public class OuterBeanFactory {

    @Bean
    public PeselValidator peselValidator() {
        return new PeselValidatorImpl();
    }

}
