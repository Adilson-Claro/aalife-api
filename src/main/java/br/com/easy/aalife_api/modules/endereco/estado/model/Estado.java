package br.com.easy.aalife_api.modules.endereco.estado.model;

import br.com.easy.aalife_api.modules.endereco.cidade.model.Cidade;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "uf", nullable = false, unique = true)
    private String uf;

    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;

    public Estado(Integer id) {
        this.id = id;
    }
}
