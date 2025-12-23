package br.com.easy.aalife_api.comum.utils;

import br.com.easy.aalife_api.config.exceptions.ValidationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailUtils {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public boolean isEmailValido(String email) {
        if (email == null || email.isBlank()) return false;
        return email.matches(EMAIL_REGEX);
    }

    public static void validarEmail(String email) {
        if (!EmailUtils.isEmailValido(email)) {
            throw new ValidationException("E-mail inválido");
        }
    }
}
