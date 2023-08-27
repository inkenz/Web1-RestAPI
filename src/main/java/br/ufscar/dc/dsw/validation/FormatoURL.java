package br.ufscar.dc.dsw.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FormatoURLValidator.class)
public @interface FormatoURL {
    String message() default "URL inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
