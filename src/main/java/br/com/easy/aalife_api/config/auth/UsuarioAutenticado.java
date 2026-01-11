package br.com.easy.aalife_api.config.auth;

import br.com.easy.aalife_api.config.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.comum.enums.ERole;
import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.modules.comum.enums.ETipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAutenticado implements UserDetails {

    private Integer id;
    private String email;
    private String senha;
    private ERole role;
    private ETipoUsuario tipoUsuario;
    private ESituacao situacao;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return situacao == ESituacao.A;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return situacao == ESituacao.A;
    }

    public void validarAdministrador() {
        if (this.role != ERole.ADMINISTRADOR) {
            throw new ValidationException("Acesso negado.");
        }
    }

    public static UsuarioAutenticado getUsuarioAutenticado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UsuarioAutenticado usuario)) {
            throw new ValidationException("Usuário não autenticado.");
        }

        return usuario;
    }
}
