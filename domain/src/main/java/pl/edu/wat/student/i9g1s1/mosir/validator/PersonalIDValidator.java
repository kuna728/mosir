package pl.edu.wat.student.i9g1s1.mosir.validator;

import org.hibernate.validator.internal.constraintvalidators.hv.pl.PESELValidator;
import pl.unak7.peselvalidator.PeselValidator;
import pl.unak7.peselvalidator.PeselValidatorImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonalIDValidator implements ConstraintValidator<PersonalIDConstraint, String> {

    private final PeselValidator peselValidator = new PeselValidatorImpl();

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)
            return true;

        return peselValidator.validate(s);
    }
}
