package br.com.easy.aalife_api.modules.especialidade.controller;

import br.com.easy.aalife_api.modules.especialidade.controller.contract.IEspecialidadeController;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeAtualizacaoRequest;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeFiltros;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeRequest;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeResponse;
import br.com.easy.aalife_api.modules.especialidade.service.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/especialidades")
public class EspecialidadeController implements IEspecialidadeController {

    private final EspecialidadeService service;

    public void salvar(EspecialidadeRequest request) {
        service.salvar(request);
    }

    public void editar(Integer id, String nome) {
        service.editar(id, nome);
    }

    public Page<EspecialidadeResponse> buscarEspecialidades(EspecialidadeFiltros filtros, Pageable pageable) {
        return service.buscarEspecialidades(filtros, pageable);
    }

    public EspecialidadeResponse buscarEspecialidade(Integer id) {
        return service.buscarEspecialidade(id);
    }

    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }
}
