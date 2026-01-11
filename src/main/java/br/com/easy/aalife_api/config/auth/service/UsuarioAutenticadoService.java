package br.com.easy.aalife_api.config.auth.service;

import br.com.easy.aalife_api.config.auth.UsuarioAutenticado;
import br.com.easy.aalife_api.config.exceptions.NotFoundException;
import br.com.easy.aalife_api.modules.comum.enums.ETipoUsuario;
import br.com.easy.aalife_api.modules.usuario.administrador.repository.UsuarioAdministradorRepository;
import br.com.easy.aalife_api.modules.usuario.base.repository.UsuarioBaseRepository;
import br.com.easy.aalife_api.modules.usuario.profissional.repository.UsuarioProfissionalRepository;
import br.com.easy.aalife_api.modules.usuario.usuariocredenciais.UsuarioCredenciais;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioAutenticadoService implements UserDetailsService {

    private final UsuarioAdministradorRepository administradorRepository;
    private final UsuarioProfissionalRepository profissionalRepository;
    private final UsuarioBaseRepository usuarioBaseRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return administradorRepository.findByUsuarioCredenciaisEmail(email)
                .map(administrador -> of(administrador.getUsuarioCredenciais(), ETipoUsuario.ADMINISTRADOR, administrador.getId()))
                .or(() -> profissionalRepository.findByUsuarioCredenciaisEmail(email)
                        .map(profissional -> of(profissional.getUsuarioCredenciais(), ETipoUsuario.PROFISSIONAL, profissional.getId())))
                .or(() -> usuarioBaseRepository.findByUsuarioCredenciaisEmail(email)
                        .map(base -> of(base.getUsuarioCredenciais(), ETipoUsuario.BASE, base.getId())))
                .orElseThrow(() ->
                        new NotFoundException("Usuário não encontrado"));
    }

    private UsuarioAutenticado of(UsuarioCredenciais credenciais, ETipoUsuario tipo, Integer id) {
        return new UsuarioAutenticado(
                id,
                credenciais.getEmail(),
                credenciais.getSenha(),
                credenciais.getRole(),
                tipo,
                credenciais.getSituacao()
        );
    }
}
