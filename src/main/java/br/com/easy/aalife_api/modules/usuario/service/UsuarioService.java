package br.com.easy.aalife_api.modules.usuario.service;

import br.com.easy.aalife_api.modules.comum.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.usuario.dto.UsuarioRequest;
import br.com.easy.aalife_api.modules.usuario.enums.EAreaSaude;
import br.com.easy.aalife_api.modules.usuario.enums.ETipoUsuario;
import br.com.easy.aalife_api.modules.usuario.model.Usuario;
import br.com.easy.aalife_api.modules.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public void salvar(UsuarioRequest request, ETipoUsuario tipoUsuario) {
        verificarTipoUsuario(request, tipoUsuario);
        var usuario = Usuario.of(request, passwordEncoder.encode(request.senha()), tipoUsuario);
        usuarioRepository.save(usuario);
    }

    private void verificarTipoUsuario(UsuarioRequest request, ETipoUsuario tipoUsuario) {
        switch (tipoUsuario) {
            case ADMINISTRADOR:
                validarDadosAdministrador(request);
                break;
            case PROFISSIONAL:
                validarDadosProfissional(request);
                break;
            case COMUM:
                validarDadosComum(request);
                break;
        }
    }

    private void validarDadosComum(UsuarioRequest request) {
        validarDataNascimento(request.dataNascimento());
        validarEmailExistente(request.email());
    }

    private void validarDadosProfissional(UsuarioRequest request) {
        validarEmailExistente(request.email());
        validarCnpj(request.cnpj());
        validarRazaoSocial(request.razaoSocial());
        validarAreaSaude(request.areaSaude());
        validarNumeroOrgaoRegulamentador(request.numeroOrgaoRegulamentador());
    }

    private void validarDadosAdministrador(UsuarioRequest request) {
        validarEmailExistente(request.email());
        validarCpf(request.cpf());
        validarDataNascimento(request.dataNascimento());
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new ValidationException("O campo CPF é obrigatório");
        }
    }

    private void validarDataNascimento(LocalDate dataNascimento) {
        validarDataNascimentoObrigatorio(dataNascimento);
        validarDataNascimentoPosteriorADataAtual(dataNascimento);
    }

    private void validarCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            throw new ValidationException("O Campo CNPJ é obrigatório.");
        }
    }

    private void validarRazaoSocial(String razaoSocial) {
        if (razaoSocial == null || razaoSocial.isEmpty()) {
            throw new ValidationException("O Campo CNPJ é obrigatório.");
        }
    }

    private void validarAreaSaude(EAreaSaude areaSaude) {
        if (areaSaude == null) {
            throw new ValidationException("O Campo Area de Saúde é obrigatório.");
        }
    }

    private void validarNumeroOrgaoRegulamentador(Integer numero) {
        if (numero == null) {
            throw new ValidationException("O Campo Número do Orgão Regulamentador é obrigatório.");
        }
    }

    private void validarDataNascimentoObrigatorio(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new ValidationException("O Campo data de nascimento é obrigatório.");
        }
    }

    private void validarDataNascimentoPosteriorADataAtual(LocalDate dataNascimento) {
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new ValidationException("O Campo data de nascimento não pode ser posterior a data atual.");
        }
    }

    private void validarEmailExistente(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ValidationException("E-mail já cadastrado, verifique e informe novamente.");
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Email ou senha incorretos."));
    }
}
