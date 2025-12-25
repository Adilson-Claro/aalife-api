package br.com.easy.aalife_api.modules.profissao.model;

import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.modules.comum.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.modules.especialidade.model.Especialidade;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoAtualizacaoRequest;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profissao")
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "orgao_regulamentador", nullable = false)
    private ETipoOrgaoRegulamentador orgaoRegulamentador;

    @OneToMany(mappedBy = "profissao")
    private List<Especialidade> especialidades;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private ESituacao situacao;

    public static Profissao of(ProfissaoRequest request) {
        return Profissao.builder()
                .orgaoRegulamentador(request.orgaoRegulamentador())
                .nome(request.nome())
                .situacao(ESituacao.A)
                .build();
    }

    public void editar(ProfissaoAtualizacaoRequest request) {
        this.nome = request.nome();
        this.orgaoRegulamentador = request.orgaoRegulamentador();
    }

    public void alterarSituacao() {
        this.situacao = this.situacao == ESituacao.A ? ESituacao.I : ESituacao.A;
    }
}
