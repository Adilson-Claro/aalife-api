package br.com.easy.aalife_api.modules.usuario.profissional.service;

import br.com.easy.aalife_api.comum.enums.ESituacao;
import br.com.easy.aalife_api.config.exceptions.NotFoundException;
import br.com.easy.aalife_api.config.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalFiltros;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalResponse;
import br.com.easy.aalife_api.modules.usuario.profissional.model.UsuarioProfissional;
import br.com.easy.aalife_api.modules.usuario.profissional.repository.UsuarioProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife_api.comum.validator.AreaSaudeValidator.validarOrgaoRegulamentador;
import static br.com.easy.aalife_api.comum.utils.ConstantsUtils.EX_USUARIO_JA_CADASTRADO;
import static br.com.easy.aalife_api.comum.utils.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife_api.comum.utils.CpfCnpjUtils.validarCnpj;
import static br.com.easy.aalife_api.comum.utils.TelefoneUtils.validarTelefone;

@Service
@RequiredArgsConstructor
public class UsuarioProfissionalService {

    private final UsuarioProfissionalRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioProfissionalRequest request) {
        validarEmailExistente(request.email());
        validarTelefone(request.telefone());
        validarCnpj(request.cnpj());
        validarCpfExistente(request.cnpj());
        validarTelefoneExistente(request.telefone());
        validarNumeroConselhoExistente(request.numeroConselho());
        validarOrgaoRegulamentador(request.areaSaude(), request.orgaoRegulamentador());

        var profissional = UsuarioProfissional.of(request, passwordEncoder);

        repository.save(profissional);
    }

    public void editar(Integer id, UsuarioProfissionalRequest request) {
        var profissional = findById(id);

        validarUsuarioAtivo(profissional.getUsuarioCredenciais().getSituacao());

        validarTelefoneParaAtualizar(request.telefone(), id);
        validarEmailParaAtualizar(request.email(), profissional.getId());

        profissional.editar(request, passwordEncoder);

        repository.save(profissional);
    }

    public void alterarSituacao(Integer id) {
        var usuario = findById(id);
        usuario.alterarSituacao();
        repository.save(usuario);
    }

    public Page<UsuarioProfissionalResponse> buscarProfissionais(UsuarioProfissionalFiltros filtros, Pageable pageable) {
        return repository.findAll(filtros.toPredicate(), pageable).map(UsuarioProfissionalResponse::of);
    }

    public UsuarioProfissionalResponse buscarPorId(Integer id) {
        var usuario = findById(id);

        return UsuarioProfissionalResponse.of(usuario);
    }

    public void validarEmailParaAtualizar(String email, Integer id) {
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

    private void validarTelefoneExistente(String telefone) {
        if (repository.existsByTelefone(telefone)) {
            throw new ValidationException(EX_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarCpfExistente(String cpf) {
        if (cpf != null && repository.existsByCnpj(cpf)) {
            throw new ValidationException(EX_USUARIO_JA_CADASTRADO);
        }
    }

    public UsuarioProfissional findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(EX_USUARIO_NAO_ENCONTRADO));
    }

    private void validarNumeroConselhoExistente(String numeroConselho) {
        if (repository.existsByNumeroConselho(numeroConselho)) {
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
