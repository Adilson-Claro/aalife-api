package br.com.easy.aalife_api.modules.profissao.controller;

import br.com.easy.aalife_api.modules.profissao.controller.contract.IProfissaoController;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoAtualizacaoRequest;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoFiltros;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoRequest;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoResponse;
import br.com.easy.aalife_api.modules.profissao.service.ProfissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profissoes")
public class ProfissaoController implements IProfissaoController {

    private final ProfissaoService service;

    public void salvar(ProfissaoRequest request) {
        service.salvar(request);
    }

    public void editar(Integer id, ProfissaoAtualizacaoRequest request) {
        service.editar(id, request);
    }

    public Page<ProfissaoResponse> buscarProfissoes(ProfissaoFiltros filtros, Pageable pageable) {
        return service.buscarProfissoes(filtros, pageable);
    }

    public ProfissaoResponse buscarProfissao(Integer id) {
        return service.buscarProfissao(id);
    }

    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }
}

