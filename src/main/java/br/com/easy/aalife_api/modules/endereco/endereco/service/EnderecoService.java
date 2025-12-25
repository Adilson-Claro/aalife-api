package br.com.easy.aalife_api.modules.endereco.endereco.service;

import br.com.easy.aalife_api.config.exceptions.NotFoundException;
import br.com.easy.aalife_api.modules.endereco.cidade.service.CidadeService;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoAtualizacaoRequest;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoFiltros;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoRequest;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoResponse;
import br.com.easy.aalife_api.modules.endereco.endereco.model.Endereco;
import br.com.easy.aalife_api.modules.endereco.endereco.repository.EnderecoRepository;
import br.com.easy.aalife_api.modules.usuario.profissional.service.UsuarioProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife_api.modules.comum.utils.CepValidator.normalizarCep;
import static br.com.easy.aalife_api.modules.comum.utils.CepValidator.validarCep;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioProfissionalService profissionalService;
    private final CidadeService cidadeService;

    public void salvar(EnderecoRequest request) {
        var profissional = profissionalService.findById(request.profissionalId());
        var cidade = cidadeService.findById(request.cidadeId());
        var cep = normalizarCep(request.cep());
        validarCep(cep);
        var endereco = Endereco.of(request, profissional, cidade);

        repository.save(endereco);
    }

    public void editar(Integer id, EnderecoAtualizacaoRequest request) {
        var endereco = findById(id);
        endereco.editar(request);
        repository.save(endereco);
    }

    public Page<EnderecoResponse> buscarEndereco(EnderecoFiltros filtros, Pageable pageable) {
        return repository.findAll(filtros.toPredicate(), pageable).map(EnderecoResponse::of);
    }

    private Endereco findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereco nao encontrado."));
    }
}
