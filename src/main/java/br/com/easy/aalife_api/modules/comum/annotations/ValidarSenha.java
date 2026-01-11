package br.com.easy.aalife_api.modules.comum.annotations;

import br.com.easy.aalife_api.modules.comum.validator.SenhaValidator;
import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER})
@Constraint(validatedBy = SenhaValidator.class)
public @interface ValidarSenha {

    String message() default
            "A senha deve conter no mínimo 8 caracteres, letra maiúscula, minúscula, número e caractere especial.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
