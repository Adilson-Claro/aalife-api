package br.com.easy.aalife_api.modules.endereco.cidade.controller;

import br.com.easy.aalife_api.modules.endereco.cidade.controller.contract.ICidadeController;
import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeFiltros;
import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeRequest;
import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeResponse;
import br.com.easy.aalife_api.modules.endereco.cidade.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cidades")
public class CidadeController implements ICidadeController {

    private final CidadeService service;

    public void salvar(CidadeRequest request) {
        service.salvar(request);
    }

    public void editar(Integer id, CidadeRequest request) {
        service.editar(id, request);
    }

    public Page<CidadeResponse> buscarCidades(CidadeFiltros filtros, Pageable pageable) {
        return service.buscarCidade(filtros, pageable);
    }

    public CidadeResponse buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }

    public Page<CidadeResponse> buscarTodas(Pageable pageable) {
        return service.buscarTodas(pageable);
    }
}
