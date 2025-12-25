package br.com.easy.aalife_api.modules.endereco.estado.controller;

import br.com.easy.aalife_api.modules.endereco.estado.controller.contract.IEstadoController;
import br.com.easy.aalife_api.modules.endereco.estado.dto.EstadoFiltros;
import br.com.easy.aalife_api.modules.endereco.estado.dto.EstadoResponse;
import br.com.easy.aalife_api.modules.endereco.estado.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/estados")
public class EstadoController implements IEstadoController {

    private final EstadoService service;

    public Page<EstadoResponse> buscarEstados(EstadoFiltros filtros, Pageable pageable) {
        return service.buscarEstados(filtros, pageable);
    }

    public Page<EstadoResponse> buscarTodos(Pageable pageable) {
        return service.buscarTodos(pageable);
    }
}
