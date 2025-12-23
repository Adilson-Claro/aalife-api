package br.com.easy.aalife_api.modules.profissao.repository;

import br.com.easy.aalife_api.modules.profissao.model.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProfissaoRepository extends JpaRepository<Profissao, Integer>, QuerydslPredicateExecutor<Profissao> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Integer id);
}
