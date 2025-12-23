package br.com.easy.aalife_api.modules.endereco.estado.controller.contract;

import br.com.easy.aalife_api.modules.endereco.estado.dto.EstadoFiltros;
import br.com.easy.aalife_api.modules.endereco.estado.dto.EstadoResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

public interface IEstadoController {

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar estados por filtros.")
    Page<EstadoResponse> buscarEstados(EstadoFiltros filtros, Pageable pageable);
}
