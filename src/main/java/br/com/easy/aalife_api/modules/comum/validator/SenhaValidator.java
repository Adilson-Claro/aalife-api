package br.com.easy.aalife_api.modules.comum.validator;

import br.com.easy.aalife_api.modules.comum.annotations.ValidarSenha;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class SenhaValidator implements ConstraintValidator<ValidarSenha, String> {

    private static final Pattern PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$"
    );

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext context) {
        return senha != null && PATTERN.matcher(senha).matches();
    }
}
