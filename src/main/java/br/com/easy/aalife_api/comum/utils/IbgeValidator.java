package br.com.easy.aalife_api.comum.utils;

import br.com.easy.aalife_api.config.exceptions.ValidationException;
import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class IbgeValidator {

    private static final Pattern IBGE_PATTERN = Pattern.compile("^[1-9][0-9]{6}$");

    public static void validarCodigoIbge(String codigoIbge) {
        if ((codigoIbge == null || codigoIbge.isBlank()) || !IBGE_PATTERN.matcher(codigoIbge).matches()) {
            throw new ValidationException("Código IBGE inválido.");
        }
    }
}
