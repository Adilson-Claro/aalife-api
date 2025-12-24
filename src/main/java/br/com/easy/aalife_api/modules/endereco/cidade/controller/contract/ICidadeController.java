package br.com.easy.aalife_api.modules.endereco.cidade.controller.contract;

import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeFiltros;
import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeRequest;
import br.com.easy.aalife_api.modules.endereco.cidade.dto.CidadeResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface ICidadeController {

    @PostMapping
    @Operation(description = "Endpoint responsavel por cadastrar cidades.")
    void salvar(@RequestBody CidadeRequest request);

    @PutMapping("{id}/editar")
    @Operation(description = "Endpoint responsavel por editar uma cidade.")
    void editar(@PathVariable Integer id, @RequestBody CidadeRequest request);

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar cidades por filtros.")
    Page<CidadeResponse> buscarCidades(CidadeFiltros filtros, Pageable pageable);

    @GetMapping("{id}")
    @Operation(description = "Endpoint responsavel por buscar cidade por id.")
    CidadeResponse buscarPorId(@PathVariable Integer id);

    @GetMapping("listar")
    @Operation(description = "Endpoint responsavel por listar todas cidades.")
    Page<CidadeResponse> buscarTodas(Pageable pageable);
}
