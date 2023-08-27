package br.ufscar.dc.dsw.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePromocaoValidator.class)
public @interface UniquePromocao {
    String message() default "Este hotel ou este site já possui uma promoção cadastrada nesse intervalo de dias";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
