package br.com.easy.aalife_api.modules.endereco.cidade.service;

import br.com.easy.aalife_api.config.exceptions.ValidationException;
import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeFiltros;
import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeRequest;
import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeResponse;
import br.com.easy.aalife_api.modules.endereco.cidade.model.Cidade;
import br.com.easy.aalife_api.modules.endereco.cidade.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_CIDADE_JA_CADASTRADA;
import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.EX_CIDADE_NAO_ENCONTRADA;
import static br.com.easy.aalife_api.modules.comum.utils.IbgeValidator.validarCodigoIbge;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository repository;

    public void salvar(CidadeRequest request) {
        validarEstadoId(request.estadoId());
        validarNomeCidade(request.nome());
        validarCidadeExistente(request.nome());
        validarCodigoIbge(request.codigoIbge());

        var cidade = Cidade.of(request);

        repository.save(cidade);
    }

    public void editar(Integer id, CidadeRequest request) {
        var cidade = findById(id);

        validarCodigoIbge(request.codigoIbge());
        validarCidadeExistenteParaEditar(id, request.nome());

        cidade.editar(request);

        repository.save(cidade);
    }

    public Page<CidadeResponse> buscarCidade(CidadeFiltros filtros, Pageable pageable) {
        return repository.findAll(filtros.toPredicate(), pageable).map(CidadeResponse::of);
    }

    public CidadeResponse buscarPorId(Integer id) {
        var ciade = findById(id);

        return CidadeResponse.of(ciade);
    }

    public Page<CidadeResponse> buscarTodas(Pageable pageable) {
        return repository.findAll(pageable).map(CidadeResponse::of);
    }

    private void validarNomeCidade(String nome) {
        if (nome.isBlank()) {
            throw new ValidationException("O campo nome e obrigatorio.");
        }
    }

    private void validarEstadoId(Integer estadoId) {
        if (estadoId == null) {
            throw new ValidationException("O campo estado id e obrigatorio");
        }
    }

    private void validarCidadeExistenteParaEditar(Integer id, String nome) {
        if (repository.existsByNomeAndIdNot(nome, id)) {
            throw new ValidationException(EX_CIDADE_JA_CADASTRADA);
        }
    }

    private void validarCidadeExistente(String nome) {
        if (repository.existsByNome(nome)) {
            throw new ValidationException(EX_CIDADE_JA_CADASTRADA);
        }
    }

    public Cidade findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(EX_CIDADE_NAO_ENCONTRADA));
    }
}
