package br.com.easy.aalife_api.modules.usuario.base.repository;

import br.com.easy.aalife_api.modules.usuario.base.model.UsuarioBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UsuarioBaseRepository extends JpaRepository<UsuarioBase, Integer>,
        QuerydslPredicateExecutor<UsuarioBase> {

    boolean existsByUsuarioCredenciaisEmailAndIdNot(String email, Integer id);

    boolean existsByUsuarioCredenciaisEmail(String email);
}
