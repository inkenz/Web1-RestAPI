package br.ufscar.dc.dsw.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataInicioAntesDoFimValidator.class)
public @interface DataInicioAntesDoFim {
    String message() default "O inicio da promoção deve ocorrer antes que o final";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
