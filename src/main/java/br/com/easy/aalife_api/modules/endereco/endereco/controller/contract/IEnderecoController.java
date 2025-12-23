package br.com.easy.aalife_api.modules.endereco.endereco.controller.contract;

import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoAtualizacaoRequest;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoFiltros;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoRequest;
import br.com.easy.aalife_api.modules.endereco.endereco.dto.EnderecoResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IEnderecoController {

    @PostMapping
    @Operation(description = "Endpoint responsavel por salvar um endereco.")
    void salvar(@RequestBody @Valid EnderecoRequest request);

    @PutMapping("{id}/editar")
    @Operation(description = "Endpoint responsavel por editar um endereco.")
    void editar(@PathVariable Integer id, @RequestBody @Valid EnderecoAtualizacaoRequest request);

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar enderecos.")
    Page<EnderecoResponse> buscarEndereco(EnderecoFiltros filtros, Pageable pageable);
}
