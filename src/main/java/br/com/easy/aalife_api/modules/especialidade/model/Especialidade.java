package br.com.easy.aalife_api.modules.especialidade.model;

import br.com.easy.aalife_api.comum.enums.ESituacao;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeAtualizacaoRequest;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeRequest;
import br.com.easy.aalife_api.modules.profissao.model.Profissao;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "especialidade")
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profissao_id")
    private Profissao profissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private ESituacao situacao;

    public static Especialidade of(EspecialidadeRequest request, Profissao profissao) {
        return Especialidade.builder()
                .nome(request.nome())
                .profissao(profissao)
                .situacao(ESituacao.A)
                .build();
    }

    public void editar(String nome) {
        this.nome = nome;
    }

    public void alterarSituacao() {
        this.situacao = this.situacao == ESituacao.A ? ESituacao.I : ESituacao.A;
    }
}
