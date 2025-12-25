package br.com.easy.aalife_api.modules.endereco.endereco.controller;

import br.com.easy.aalife_api.modules.endereco.endereco.controller.contract.IEnderecoController;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoAtualizacaoRequest;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoFiltros;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoRequest;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoResponse;
import br.com.easy.aalife_api.modules.endereco.endereco.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/endereco")
public class EnderecoController implements IEnderecoController {

    private final EnderecoService service;

    public void salvar(EnderecoRequest request) {
        service.salvar(request);
    }

    public void editar(Integer id, EnderecoAtualizacaoRequest request) {
        service.editar(id, request);
    }

    public Page<EnderecoResponse> buscarEndereco(EnderecoFiltros filtros, Pageable pageable) {
        return service.buscarEndereco(filtros, pageable);
    }
}
