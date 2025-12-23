package br.com.easy.aalife_api.modules.usuario.base.controller;

import br.com.easy.aalife_api.modules.usuario.base.controller.contract.IUsuarioBaseController;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseFiltros;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseRequest;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseResponse;
import br.com.easy.aalife_api.modules.usuario.base.service.UsuarioBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/usuarios")
public class UsuarioBaseController implements IUsuarioBaseController {

    private final UsuarioBaseService service;

    @Override
    public void salvar(UsuarioBaseRequest request) {
        service.salvar(request);
    }

    @Override
    public void editar(Integer id, UsuarioBaseRequest request) {
        service.editar(id, request);
    }

    @Override
    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }

    @Override
    public UsuarioBaseResponse buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }

    @Override
    public Page<UsuarioBaseResponse> buscarUsuarios(UsuarioBaseFiltros filtros, Pageable pageable) {
        return service.buscarUsuarios(filtros, pageable);
    }
}
