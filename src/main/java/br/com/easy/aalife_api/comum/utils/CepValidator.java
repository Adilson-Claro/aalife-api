package br.com.easy.aalife_api.comum.utils;

import br.com.easy.aalife_api.config.exceptions.ValidationException;

import java.util.regex.Pattern;

public class CepValidator {

    private static final Pattern CEP_PATTERN =
            Pattern.compile("^\\d{5}-?\\d{3}$");

    public static void validarCep(String cep) {
        if (cep == null || !CEP_PATTERN.matcher(cep).matches()) {
            throw new ValidationException("CEP inválido.");
        }
    }

    public static String normalizarCep(String cep) {
        return cep.replaceAll("\\D", "");
    }
}
