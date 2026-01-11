package br.com.easy.aalife_api.modules.usuario.base.dto;

import br.com.easy.aalife_api.modules.comum.annotations.ValidarSenha;
import br.com.easy.aalife_api.modules.comum.enums.ESexo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UsuarioBaseRequest(String nome,
                                 String email,
                                 LocalDate dataNascimento,
                                 BigDecimal peso,
                                 BigDecimal altura,
                                 Integer idade,
                                 @ValidarSenha String senha,
                                 ESexo sexo) {
}
