package pl.edu.wat.student.i9g1s1.mosir.validator;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.core.annotation.Order;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AgeValidator implements ConstraintValidator<AgeConstraint, String> {

    private int min;
    private int max;
    @Override
    public void initialize(AgeConstraint constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)
            return true;

        LocalDate date;
        try {
             date = LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            return false;
        }
        return ( LocalDate.now().minusYears(min).isAfter(date) || LocalDate.now().minusYears(min).isEqual(date))
               && ( LocalDate.now().minusYears(max).isBefore(date) || LocalDate.now().minusYears(max).isEqual(date));
    }
}
