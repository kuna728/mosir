package pl.edu.wat.student.i9g1s1.mosir.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint()
public @interface PeselConstraint {
    String message() default "Field is not a valid pesel number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
