package br.com.easy.aalife_api.modules.usuario.controller;

import br.com.easy.aalife_api.modules.usuario.dto.UsuarioRequest;
import br.com.easy.aalife_api.modules.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.easy.aalife_api.modules.usuario.enums.ETipoUsuario.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public void salvarComum(@Valid @RequestBody UsuarioRequest request) {
        usuarioService.salvar(request, COMUM);
    }
}
