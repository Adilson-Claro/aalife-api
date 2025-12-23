package br.com.easy.aalife_api.modules.usuario.pessoa.fisica;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
public abstract class PessoaFisica {

    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private String cpf;
    private BigDecimal peso;
    private BigDecimal altura;
    private Integer idade;
}
