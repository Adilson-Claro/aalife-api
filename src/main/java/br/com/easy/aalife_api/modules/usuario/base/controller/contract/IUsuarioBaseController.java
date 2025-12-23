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

    @PostMapping("cadastrar")
    @Operation(summary = "Endpoint responsavel pelo cadastro de usuario base.")
    void salvar(@RequestBody @Valid UsuarioBaseRequest request);

    @PutMapping("{id}/editar")
    @Operation(summary = "Endpoint responsavel pela edicao de usuarios base")
    void editar(@PathVariable Integer id, @RequestBody UsuarioBaseRequest request);

    @PutMapping("{id}/alterar-situacao")
    @Operation(summary = "Endpoint responsavel por alterar a situacao de um usuario base")
    void alterarSituacao(@PathVariable Integer id);

    @GetMapping("{id}")
    @Operation(summary = "Endpoint responsavel por buscar usuario base por id")
    UsuarioBaseResponse buscarPorId(@PathVariable Integer id);

    @GetMapping
    @Operation(summary = "Endpoint responsavel por buscar usuarios todos usuarios base com filtros")
    Page<UsuarioBaseResponse> buscarUsuarios(UsuarioBaseFiltros filtros, Pageable pageable);
}
