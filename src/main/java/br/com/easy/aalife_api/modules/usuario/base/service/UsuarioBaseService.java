package br.com.easy.aalife_api.modules.usuario.base.service;

import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.config.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseFiltros;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseRequest;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseResponse;
import br.com.easy.aalife_api.modules.usuario.base.model.UsuarioBase;
import br.com.easy.aalife_api.modules.usuario.base.repository.UsuarioBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_USUARIO_JA_CADASTRADO;
import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife_api.modules.comum.utils.EmailUtils.validarEmail;

@Service
@RequiredArgsConstructor
public class UsuarioBaseService {

    private final UsuarioBaseRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioBaseRequest request) {
        validarEmail(request.email());
        validarEmailExistente(request.email());
        var usuario = UsuarioBase.of(request, passwordEncoder);

        repository.save(usuario);
    }

    public void editar(Integer id, UsuarioBaseRequest request) {
        var usuario = findById(id);
        validarUsuarioAtivo(usuario.getUsuarioCredenciais().getSituacao());
        validarEmailParaAtualizar(request.email(), id);

        usuario.editar(request, passwordEncoder);

        repository.save(usuario);
    }

    public void alterarSituacao(Integer id) {
        var usuario = findById(id);
        usuario.alterarSituacao();
        repository.save(usuario);
    }

    public Page<UsuarioBaseResponse> buscarUsuarios(UsuarioBaseFiltros filtros, Pageable pageable) {
        return repository.findAll(filtros.toPredicate(), pageable).map(UsuarioBaseResponse::of);
    }

    public UsuarioBaseResponse buscarPorId(Integer id) {
        var usuario = findById(id);

        return UsuarioBaseResponse.of(usuario);
    }

    public UsuarioBase findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(EX_USUARIO_NAO_ENCONTRADO));
    }

    private void validarEmailParaAtualizar(String email, Integer id) {
        if (repository.existsByUsuarioCredenciaisEmailAndIdNot(email, id)) {
            throw new ValidationException(EX_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarUsuarioAtivo(ESituacao situacao) {
        if (situacao != ESituacao.A) {
            throw new ValidationException("Usuário inativo.");
        }
    }

    private void validarEmailExistente(String email) {
        if (repository.existsByUsuarioCredenciaisEmail(email)) {
            throw new ValidationException(EX_USUARIO_JA_CADASTRADO);
        }
    }
}
