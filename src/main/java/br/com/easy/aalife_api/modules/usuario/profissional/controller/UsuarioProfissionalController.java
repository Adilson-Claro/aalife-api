package br.com.easy.aalife_api.modules.usuario.profissional.controller;

import br.com.easy.aalife_api.modules.usuario.profissional.controller.contract.IUsuarioProfissionalController;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalAtualizacaoRequest;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalFiltros;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalResponse;
import br.com.easy.aalife_api.modules.usuario.profissional.service.UsuarioProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profissionais")
public class UsuarioProfissionalController implements IUsuarioProfissionalController {

    private final UsuarioProfissionalService service;

    @Override
    public void salvar(UsuarioProfissionalRequest request) {
        service.salvar(request);
    }

    @Override
    public void editar(Integer id, UsuarioProfissionalAtualizacaoRequest request) {
        service.editar(id, request);
    }

    @Override
    public void editarSenha(Integer id, UsuarioProfissionalAtualizacaoRequest request) {
        service.editarSenha(id, request);
    }

    @Override
    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }

    @Override
    public UsuarioProfissionalResponse buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }

    @Override
    public Page<UsuarioProfissionalResponse> buscarUsuarios(UsuarioProfissionalFiltros filtros, Pageable pageable) {
        return service.buscarProfissionais(filtros, pageable);
    }
}
