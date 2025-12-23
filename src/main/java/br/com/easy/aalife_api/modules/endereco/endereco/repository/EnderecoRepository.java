package br.com.easy.aalife_api.modules.endereco.endereco.repository;

import br.com.easy.aalife_api.modules.endereco.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>, QuerydslPredicateExecutor<Endereco> {
}
