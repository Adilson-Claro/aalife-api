package br.com.easy.aalife_api.modules.usuario.administrador.service;

import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.config.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.usuario.administrador.dto.UsuarioAdministradorRequest;
import br.com.easy.aalife_api.modules.usuario.administrador.model.UsuarioAdministrador;
import br.com.easy.aalife_api.modules.usuario.administrador.repository.UsuarioAdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_USUARIO_JA_CADASTRADO;
import static br.com.easy.aalife_api.modules.comum.utils.TelefoneUtils.validarTelefone;

@Service
@RequiredArgsConstructor
public class UsuarioAdministradorService {

    private final UsuarioAdministradorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioAdministradorRequest request) {
        validarEmailExistente(request.email());
        validarTelefoneExistente(request.telefone());
        validarTelefone(request.telefone());
        validarCpfExistente(request.cpf());

        var administrador = UsuarioAdministrador.of(request, passwordEncoder);

        repository.save(administrador);
    }

    public void editar(Integer id, UsuarioAdministradorRequest request) {
        var administrador = findById(id);
        validarSituacaoAtiva(administrador.getUsuarioCredenciais().getSituacao());
        validarTelefoneParaAtualizar(request.telefone(), id);

        administrador.editar(request, passwordEncoder);

        repository.save(administrador);
    }

    public void alterarSituacao(Integer id) {
        var usuario = findById(id);
        usuario.alterarSituacao();
        repository.save(usuario);
    }

    private void validarSituacaoAtiva(ESituacao situacao) {
        if (situacao != ESituacao.A) {
            throw new ValidationException("Não é possivel atualizar um usuário inativo.");
        }
    }

    private void validarEmailExistente(String email) {
        if (repository.existsByUsuarioCredenciaisEmail(email)) {
            throw new ValidationException(EX_USUARIO_JA_CADASTRADO);
        }
    }

    public UsuarioAdministrador findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(EX_USUARIO_NAO_ENCONTRADO));
    }

    private void validarCpfExistente(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new ValidationException(EX_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarTelefoneExistente(String telefone) {
        if (repository.existsByTelefone(telefone)) {
            throw new ValidationException(EX_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarTelefoneParaAtualizar(String telefone, Integer id) {
        if (repository.existsByTelefoneAndIdNot(telefone, id)) {
            throw new ValidationException(EX_USUARIO_JA_CADASTRADO);
        }
        validarTelefone(telefone);
    }
}
