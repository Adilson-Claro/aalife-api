package br.com.easy.aalife_api.modules.especialidade.repository;

import br.com.easy.aalife_api.modules.especialidade.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer>,
        QuerydslPredicateExecutor<Especialidade> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Integer id);
}
