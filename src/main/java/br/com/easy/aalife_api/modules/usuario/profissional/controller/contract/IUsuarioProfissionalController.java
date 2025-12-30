package br.com.easy.aalife_api.modules.usuario.profissional.controller.contract;

import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalAtualizacaoRequest;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalFiltros;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IUsuarioProfissionalController {

    @PostMapping("salvar")
    @Operation(summary = "Endpoint responsável pelo cadastro de um usuário profissional.")
    void salvar(@RequestBody @Valid UsuarioProfissionalRequest request);

    @PutMapping("{id}/editar")
    @Operation(summary = "Endpoint responsável pela edição de um usuário profissional.")
    void editar(@PathVariable Integer id, @RequestBody UsuarioProfissionalAtualizacaoRequest request);

    @PutMapping("{id}/alterar-situacao")
    @Operation(summary = "Endpoint responsável por alterar a situação de um usuário profissional.")
    void alterarSituacao(@PathVariable Integer id);

    @GetMapping("{id}")
    @Operation(summary = "Endpoint responsável por buscar usuário profissional por id.")
    UsuarioProfissionalResponse buscarPorId(@PathVariable Integer id);

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar usuários profissionais por filtros.")
    Page<UsuarioProfissionalResponse> buscarUsuarios(UsuarioProfissionalFiltros filtros, Pageable pageable);
}
