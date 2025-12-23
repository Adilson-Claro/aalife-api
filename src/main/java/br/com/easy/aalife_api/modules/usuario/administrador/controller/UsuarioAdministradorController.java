package br.com.easy.aalife_api.modules.usuario.administrador.controller;

import br.com.easy.aalife_api.modules.usuario.administrador.controller.contract.IUsuarioAdministradorController;
import br.com.easy.aalife_api.modules.usuario.administrador.dto.UsuarioAdministradorRequest;
import br.com.easy.aalife_api.modules.usuario.administrador.service.UsuarioAdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/administradores")
public class UsuarioAdministradorController implements IUsuarioAdministradorController {

    private final UsuarioAdministradorService service;

    public void salvar(UsuarioAdministradorRequest request) {
        service.salvar(request);
    }

    public void editar(Integer id, UsuarioAdministradorRequest request) {
        service.editar(id, request);
    }

    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }
}
