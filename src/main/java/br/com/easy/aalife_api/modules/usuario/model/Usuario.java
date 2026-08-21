package br.com.easy.aalife_api.modules.usuario.model;

import br.com.easy.aalife_api.modules.usuario.dto.UsuarioRequest;
import br.com.easy.aalife_api.modules.usuario.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static br.com.easy.aalife_api.modules.usuario.enums.ERole.COMUM;
import static br.com.easy.aalife_api.modules.usuario.enums.ESituacao.ATIVO;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 160)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ERole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ESituacao situacao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ORGAO_REGULAMENTADOR")
    private ETipoOrgaoRegulamentador tipoOrgaoRegulamentador;

    @Column(unique = true)
    private String cpf;

    @CNPJ
    @Column(unique = true)
    private String cnpj;

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "AREA_SAUDE")
    private EAreaSaude areaSaude;

    @Column(name = "NUMERO_ORGAO_REGULAMENTADOR")
    private Integer numeroOrgaoRegulamentador;

    @Column(name = "peso", columnDefinition = "numeric")
    private BigDecimal peso;

    @Column(name = "altura", columnDefinition = "numeric")
    private BigDecimal altura;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO")
    private ETipoUsuario tipoUsuario;

    @Enumerated(EnumType.STRING)
    private ESexo sexo;

    public static Usuario of(UsuarioRequest request, String senha, ETipoUsuario tipoUsuario) {
        return Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(senha)
                .dataCadastro(LocalDateTime.now())
                .role(COMUM)
                .situacao(ATIVO)
                .tipoOrgaoRegulamentador(request.tipoOrgaoRegulamentador())
                .cpf(request.cpf())
                .cnpj(request.cnpj())
                .razaoSocial(request.razaoSocial())
                .dataNascimento(request.dataNascimento())
                .areaSaude(request.areaSaude())
                .numeroOrgaoRegulamentador(request.numeroOrgaoRegulamentador())
                .peso(request.peso())
                .altura(request.altura())
                .tipoUsuario(tipoUsuario)
                .sexo(request.sexo())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ESituacao.ATIVO.equals(situacao);
    }
}
