package br.com.easy.aalife_api.modules.profissao.controller.contract;

import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoAtualizacaoRequest;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoFiltros;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoRequest;
import br.com.easy.aalife_api.modules.profissao.dto.ProfissaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IProfissaoController {

    @PostMapping
    @Operation(description = "Endpoint responsavel pelo cadastro de profissoes.")
    void salvar(@RequestBody @Valid ProfissaoRequest request);

    @PutMapping("{id}/editar")
    @Operation(description = "Endpoint responsavel por editar uma profissao.")
    void editar(@PathVariable Integer id, @RequestBody @Valid ProfissaoAtualizacaoRequest request);

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar profissoes por fitros.")
    Page<ProfissaoResponse> buscarProfissoes(ProfissaoFiltros filtros, Pageable pageable);

    @GetMapping("{id}")
    @Operation(description = "Endpoint responsavel por buscar profissao por id.")
    ProfissaoResponse buscarProfissao(@PathVariable Integer id);

    @PutMapping("{id}/alterar-situacao")
    @Operation(description = "Endpoint responsavel por alterar a situacao de uma profissao.")
    void alterarSituacao(@PathVariable Integer id);
}
