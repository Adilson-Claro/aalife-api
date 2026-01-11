package br.com.easy.aalife_api.modules.usuario.administrador.service;

import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.config.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.usuario.administrador.dto.UsuarioAdministradorAtualizacaoRequest;
import br.com.easy.aalife_api.modules.usuario.administrador.dto.UsuarioAdministradorRequest;
import br.com.easy.aalife_api.modules.usuario.administrador.model.UsuarioAdministrador;
import br.com.easy.aalife_api.modules.usuario.administrador.repository.UsuarioAdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.easy.aalife_api.config.auth.UsuarioAutenticado.getUsuarioAutenticado;
import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_USUARIO_JA_CADASTRADO;
import static br.com.easy.aalife_api.modules.comum.utils.CpfCnpjUtils.validarCpf;
import static br.com.easy.aalife_api.modules.comum.utils.TelefoneUtils.validarTelefone;

@Service
@RequiredArgsConstructor
public class UsuarioAdministradorService {

    private final UsuarioAdministradorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioAdministradorRequest request) {
        validarEmailExistente(request.email());
        validarCpf(request.cpf());
        validarTelefoneExistente(request.telefone());
        validarTelefone(request.telefone());
        validarCpfExistente(request.cpf());

        var usuario = UsuarioAdministrador.of(request, passwordEncoder);

        repository.save(usuario);
    }

    public void editar(Integer id, UsuarioAdministradorAtualizacaoRequest request) {
        var usuario = findById(id);
        validarUsuarioAtual(usuario.getId());
        validarSituacaoAtiva(usuario.getUsuarioCredenciais().getSituacao());
        validarTelefoneParaAtualizar(request.telefone(), id);

        usuario.editar(request);

        repository.save(usuario);
    }

    public void alterarSituacao(Integer id) {
        var usuario = findById(id);
        usuario.alterarSituacao();
        repository.save(usuario);
    }

    public void editarSenha(Integer id, UsuarioAdministradorAtualizacaoRequest request) {
        var usuario = findById(id);
        validarUsuarioAtual(usuario.getId());

        usuario.editarSenha(request.senha(), passwordEncoder);

        repository.save(usuario);
    }

    private void validarSituacaoAtiva(ESituacao situacao) {
        if (situacao != ESituacao.A) {
            throw new ValidationException("Não é possivel atualizar um usuário inativo.");
        }
    }

    private void validarUsuarioAtual(Integer usuarioId) {
        var usuario = getUsuarioAutenticado();
        if (!Objects.equals(usuario.getId(), usuarioId)) {
            throw new ValidationException("Usuario sem permissao sobre a entidade requisitada.");
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
