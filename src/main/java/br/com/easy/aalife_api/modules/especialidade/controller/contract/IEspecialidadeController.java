package br.com.easy.aalife_api.modules.especialidade.controller.contract;

import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeFiltros;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeRequest;
import br.com.easy.aalife_api.modules.especialidade.dto.EspecialidadeResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IEspecialidadeController {

    @PostMapping
    @Operation(description = "Endpoint responsável pelo cadastro de especialidades.")
    void salvar(@RequestBody EspecialidadeRequest request);

    @PutMapping("{id}/editar")
    @Operation(description = "Endpoint responsável por editar uma especialidade.")
    void editar(@PathVariable Integer id, @RequestBody @NotBlank String nome);

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar especialidades por fitros.")
    Page<EspecialidadeResponse> buscarEspecialidades(EspecialidadeFiltros filtros, Pageable pageable);

    @GetMapping("{id}")
    @Operation(description = "Endpoint responsável por buscar especialidade por id.")
    EspecialidadeResponse buscarEspecialidade(@PathVariable Integer id);

    @PutMapping("{id}/alterar-situacao")
    @Operation(description = "Endpoint responsável por alterar a situação de uma especialidade.")
    void alterarSituacao(@PathVariable Integer id);
}
