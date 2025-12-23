package br.com.easy.aalife_api.modules.especialidade.controller.contract;

import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeAtualizacaoRequest;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeFiltros;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeRequest;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IEspecialidadeController {

    @PostMapping
    @Operation(description = "Endpoint responsavel pelo cadastro de especialidades.")
    void salvar(@RequestBody @Valid EspecialidadeRequest request);

    @PutMapping("{id}/editar")
    @Operation(description = "Endpoint responsavel por editar uma especialidade.")
    void editar(@PathVariable Integer id, @RequestBody @NotBlank String nome);

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar especialidades por fitros.")
    Page<EspecialidadeResponse> buscarEspecialidades(EspecialidadeFiltros filtros, Pageable pageable);

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar especialidade por id.")
    EspecialidadeResponse buscarEspecialidade(@PathVariable Integer id);

    @PutMapping("{id}/alterar-situacao")
    @Operation(description = "Endpoint responsavel por alterar a situacao de uma especialidade.")
    void alterarSituacao(@PathVariable Integer id);
}
