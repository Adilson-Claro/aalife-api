package br.com.easy.aalife_api.modules.endereco.estado.service;

import br.com.easy.aalife_api.modules.endereco.estado.dto.EstadoFiltros;
import br.com.easy.aalife_api.modules.endereco.estado.dto.EstadoResponse;
import br.com.easy.aalife_api.modules.endereco.estado.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository repository;

    public Page<EstadoResponse> buscarEstados(EstadoFiltros filtros, Pageable pageable) {
        return repository.findAll(filtros.toPredicate(), pageable).map(EstadoResponse::of);
    }

    public Page<EstadoResponse> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable).map(EstadoResponse::of);
    }
}
