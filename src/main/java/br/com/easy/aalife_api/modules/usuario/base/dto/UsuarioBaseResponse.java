package br.com.easy.aalife_api.modules.usuario.base.dto;

import br.com.easy.aalife_api.modules.usuario.base.model.UsuarioBase;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UsuarioBaseResponse(String nome,
                                  LocalDate dataNascimento,
                                  BigDecimal peso,
                                  BigDecimal altura,
                                  Integer idade) {

    public static UsuarioBaseResponse of(UsuarioBase usuarioBase) {
        return new UsuarioBaseResponse(
                usuarioBase.getNome(),
                usuarioBase.getDataNascimento(),
                usuarioBase.getPeso(),
                usuarioBase.getAltura(),
                usuarioBase.getIdade()
        );

    }
}
