package br.com.easy.aalife_api.modules.usuario.profissional.controller.contract;

import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalFiltros;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IUsuarioProfissionalController {

    @PostMapping("cadastrar")
    @Operation(summary = "Endpoint responsavel pelo cadastro de usuario base.")
    void salvar(@RequestBody @Valid UsuarioProfissionalRequest request);

    @PutMapping("{id}/editar")
    @Operation(summary = "Endpoint responsavel pela edicao de usuarios base")
    void editar(@PathVariable Integer id, @RequestBody UsuarioProfissionalRequest request);

    @PutMapping("{id}/alterar-situacao")
    @Operation(summary = "Endpoint responsavel por alterar a situacao de um usuario base")
    void alterarSituacao(@PathVariable Integer id);

    @GetMapping("{id}")
    @Operation(summary = "Endpoint responsavel por buscar usuario base por id")
    UsuarioProfissionalResponse buscarPorId(@PathVariable Integer id);

    @GetMapping
    @Operation(summary = "Endpoint responsavel por buscar usuarios todos usuarios base com filtros")
    Page<UsuarioProfissionalResponse> buscarUsuarios(UsuarioProfissionalFiltros filtros, Pageable pageable);
}
