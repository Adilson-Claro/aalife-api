package br.com.easy.aalife_api.modules.profissao.service;

import br.com.easy.aalife_api.comum.enums.ESituacao;
import br.com.easy.aalife_api.config.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoAtualizacaoRequest;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoFiltros;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoRequest;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoResponse;
import br.com.easy.aalife_api.modules.profissao.model.Profissao;
import br.com.easy.aalife_api.modules.profissao.repository.ProfissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife_api.comum.validator.AreaSaudeValidator.validarOrgaoRegulamentador;
import static br.com.easy.aalife_api.comum.utils.ConstantsUtils.EX_PROFISSAO_JA_CADASTRADA;
import static br.com.easy.aalife_api.comum.utils.ConstantsUtils.EX_PROFISSAO_NAO_ENCONTRADA;

@Service
@RequiredArgsConstructor
public class ProfissaoService {

    private final ProfissaoRepository repository;

    public void salvar(ProfissaoRequest request) {
        validarProfissaoExistente(request.nome());
        validarOrgaoRegulamentador(request.areaSaude(), request.orgaoRegulamentador());

        var profissao = Profissao.of(request);
        repository.save(profissao);
    }

    public void editar(Integer id, ProfissaoAtualizacaoRequest request) {
        var profissao = findById(id);
        validarProfissaoExistenteParaEditar(request.nome(), id);

        profissao.editar(request);
        repository.save(profissao);
    }

    public void alterarSituacao(Integer id) {
        var profissao = findById(id);
        validarSituacaoAtiva(profissao.getSituacao());

        profissao.alterarSituacao();

        repository.save(profissao);
    }

    public Page<ProfissaoResponse> buscarProfissoes(ProfissaoFiltros filtros, Pageable pageable) {
        return repository.findAll(filtros.toPredicate(), pageable).map(ProfissaoResponse::of);
    }

    public ProfissaoResponse buscarProfissao(Integer id) {
        var profissao = findById(id);

        return ProfissaoResponse.of(profissao);
    }

    private void validarSituacaoAtiva(ESituacao situacao) {
        if (situacao != ESituacao.A) {
            throw new ValidationException("Esta profissao se encontra inativa");
        }
    }

    public Profissao findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(EX_PROFISSAO_NAO_ENCONTRADA));
    }

    private void validarProfissaoExistenteParaEditar(String nome, Integer id) {
        if (repository.existsByNomeAndIdNot(nome, id)) {
            throw new ValidationException(EX_PROFISSAO_JA_CADASTRADA);
        }
    }

    private void validarProfissaoExistente(String nome) {
        if (repository.existsByNome(nome)) {
            throw new ValidationException(EX_PROFISSAO_JA_CADASTRADA);
        }
    }
}
