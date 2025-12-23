package br.com.easy.aalife_api.modules.endereco.cidade.repository;

import br.com.easy.aalife_api.modules.endereco.cidade.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>, QuerydslPredicateExecutor<Cidade> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Integer id);
}
