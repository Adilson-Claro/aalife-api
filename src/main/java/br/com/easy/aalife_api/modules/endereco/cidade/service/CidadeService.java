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

import static br.com.easy.aalife_api.comum.utils.IbgeValidator.validarCodigoIbge;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository repository;

    public void salvar(CidadeRequest request) {
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

    private void validarCidadeExistenteParaEditar(Integer id, String nome) {
        if (repository.existsByNomeAndIdNot(nome, id)) {
            throw new ValidationException("Esta cidade ja esta cadastrada.");
        }
    }

    private void validarCidadeExistente(String nome) {
        if (repository.existsByNome(nome)) {
            throw new ValidationException("Esta cidade ja esta cadastrada.");
        }
    }

    public Cidade findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Cidade nao encontrada"));
    }
}
