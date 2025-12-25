package br.com.easy.aalife_api.modules.usuario.base.controller.contract;

import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseFiltros;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseRequest;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IUsuarioBaseController {

    @PostMapping
    @Operation(summary = "Endpoint responsável pelo cadastro de usuário base.")
    void salvar(@RequestBody @Valid UsuarioBaseRequest request);

    @PutMapping("{id}/editar")
    @Operation(summary = "Endpoint responsável pela edicao de usuários base.")
    void editar(@PathVariable Integer id, @RequestBody UsuarioBaseRequest request);

    @PutMapping("{id}/alterar-situacao")
    @Operation(summary = "Endpoint responsável por alterar a situação de um usuário base.")
    void alterarSituacao(@PathVariable Integer id);

    @GetMapping("{id}")
    @Operation(summary = "Endpoint responsável por buscar um usuário base por id.")
    UsuarioBaseResponse buscarPorId(@PathVariable Integer id);

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar usuários base por filtros.")
    Page<UsuarioBaseResponse> buscarUsuarios(UsuarioBaseFiltros filtros, Pageable pageable);
}
