package br.com.easy.aalife_api.modules.endereco.cidade.model;

import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeRequest;
import br.com.easy.aalife_api.modules.endereco.endereco.model.Endereco;
import br.com.easy.aalife_api.modules.endereco.estado.model.Estado;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(columnDefinition = "estado_id")
    private Estado estadoId;

    @OneToMany(mappedBy = "cidade")
    private List<Endereco> enderecos;

    @Column(name = "codigo_ibge", unique = true)
    private String codigoIbge;

    public Cidade(Integer integer) {
    }

    public static Cidade of(CidadeRequest request) {
        return Cidade.builder()
                .nome(request.nome())
                .estadoId(new Estado(request.estadoId()))
                .codigoIbge(request.codigoIbge())
                .build();
    }

    public void editar(CidadeRequest request) {
        this.nome = request.nome();
        this.codigoIbge = request.codigoIbge();
    }
}
