package br.com.easy.aalife_api.modules.endereco.estado.repository;

import br.com.easy.aalife_api.modules.endereco.estado.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EstadoRepository extends JpaRepository<Estado, Integer>, QuerydslPredicateExecutor<Estado> {
}
