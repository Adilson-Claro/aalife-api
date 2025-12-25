package br.com.easy.aalife_api.modules.especialidade.service;

import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.config.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeFiltros;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeRequest;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeResponse;
import br.com.easy.aalife_api.modules.especialidade.model.Especialidade;
import br.com.easy.aalife_api.modules.profissao.model.ProfissaoEspecialidade;
import br.com.easy.aalife_api.modules.especialidade.repository.EspecialidadeRepository;
import br.com.easy.aalife_api.modules.profissao.service.ProfissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_ESPECIALIDADE_JA_CADASTRADA;
import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_ESPECIALIDADE_NAO_ENCONTRADA;

@Service
@RequiredArgsConstructor
public class EspecialidadeService {

    private final EspecialidadeRepository repository;
    private final ProfissaoService service;

    public void salvar(EspecialidadeRequest request) {
        validarEspecialidadeExistente(request.nome());
        validarNome(request.nome());
        var profissao = service.findById(request.profissaoId());

        var especialidade = Especialidade.of(request, profissao);

        ProfissaoEspecialidade.of(profissao, especialidade);

        repository.save(especialidade);
    }

    public void editar(Integer id, String nome) {
        var especialidade = findById(id);
        validarEspecialidadeExistenteParaEditar(nome, id);

        especialidade.editar(nome);
        repository.save(especialidade);
    }

    public void alterarSituacao(Integer id) {
        var especialidade = findById(id);
        validarSituacaoAtiva(especialidade.getSituacao());

        especialidade.alterarSituacao();

        repository.save(especialidade);
    }

    public Page<EspecialidadeResponse> buscarEspecialidades(EspecialidadeFiltros filtros, Pageable pageable) {
        return repository.findAll(filtros.toPredicate(), pageable).map(EspecialidadeResponse::of);
    }

    public EspecialidadeResponse buscarEspecialidade(Integer id) {
        var especialidade = findById(id);

        return EspecialidadeResponse.of(especialidade);
    }

    private void validarNome(String nome) {
        if (nome.isBlank()) {
            throw new ValidationException("O campo nome e obrigatorio.");
        }
    }

    private void validarSituacaoAtiva(ESituacao situacao) {
        if (situacao != ESituacao.A) {
            throw new ValidationException("Esta especialidade se encontra inativa");
        }
    }

    private Especialidade findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(EX_ESPECIALIDADE_NAO_ENCONTRADA));
    }

    private void validarEspecialidadeExistenteParaEditar(String nome, Integer id) {
        if (repository.existsByNomeAndIdNot(nome, id)) {
            throw new ValidationException(EX_ESPECIALIDADE_JA_CADASTRADA);
        }
    }

    private void validarEspecialidadeExistente(String nome) {
        if (repository.existsByNome(nome)) {
            throw new ValidationException(EX_ESPECIALIDADE_JA_CADASTRADA);
        }
    }
}
