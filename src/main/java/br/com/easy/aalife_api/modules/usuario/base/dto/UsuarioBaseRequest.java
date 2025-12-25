package br.com.easy.aalife_api.modules.usuario.base.dto;

import br.com.easy.aalife_api.modules.comum.enums.ESexo;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UsuarioBaseRequest(String nome,
                                 @NotBlank String email,
                                 LocalDate dataNascimento,
                                 BigDecimal peso,
                                 BigDecimal altura,
                                 Integer idade,
                                 @NotBlank String senha,
                                 ESexo sexo) {
}
