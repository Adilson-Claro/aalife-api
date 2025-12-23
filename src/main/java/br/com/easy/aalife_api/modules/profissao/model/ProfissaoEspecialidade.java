package br.com.easy.aalife_api.modules.profissao.model;

import br.com.easy.aalife_api.modules.especialidade.model.Especialidade;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profissao_especialidade",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = {"profissao_id", "especialidade_id"})}
)
public class ProfissaoEspecialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Profissao profissao;

    @ManyToOne(optional = false)
    private Especialidade especialidade;

    public static ProfissaoEspecialidade of(Profissao profissao, Especialidade especialidade) {
        return ProfissaoEspecialidade.builder()
                .profissao(profissao)
                .especialidade(especialidade)
                .build();
    }
}
