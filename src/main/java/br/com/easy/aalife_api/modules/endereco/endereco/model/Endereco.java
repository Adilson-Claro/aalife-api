package br.com.easy.aalife_api.modules.endereco.endereco.model;

import br.com.easy.aalife_api.modules.endereco.cidade.model.Cidade;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoAtualizacaoRequest;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoRequest;
import br.com.easy.aalife_api.modules.usuario.profissional.model.UsuarioProfissional;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profissional_id")
    private UsuarioProfissional profissional;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "codigoIbge", nullable = false)
    private String cep;

    @Column(name = "complemento", nullable = false)
    private String complemento;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    public static Endereco of(EnderecoRequest request, UsuarioProfissional profissional, Cidade cidade) {
        return Endereco.builder()
                .profissional(profissional)
                .cidade(cidade)
                .logradouro(request.logradouro())
                .bairro(request.bairro())
                .cep(request.cep())
                .complemento(request.complemento())
                .numero(request.numero())
                .build();
    }

    public void editar(EnderecoAtualizacaoRequest request) {
        this.cidade = new Cidade(request.cidadeId());
        this.logradouro = request.logradouro();
        this.bairro = request.bairro();
        this.cep = request.cep();
        this.complemento = request.complemento();
        this.numero = request.numero();
    }
}
