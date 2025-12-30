package br.com.easy.aalife_api.modules.usuario.profissional.repository;

import br.com.easy.aalife_api.modules.usuario.profissional.model.UsuarioProfissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UsuarioProfissionalRepository extends JpaRepository<UsuarioProfissional, Integer>,
        QuerydslPredicateExecutor<UsuarioProfissional> {

    boolean existsByCnpj(String cnpj);

    boolean existsByTelefone(String telefone);

    boolean existsByTelefoneAndIdNot(String telefone, Integer id);

    boolean existsByNumeroConselho(String numeroConselho);

    boolean existsByUsuarioCredenciaisEmailAndIdNot(String email, Integer id);

    boolean existsByUsuarioCredenciaisEmail(String email);

    Optional<UsuarioProfissional> findByUsuarioCredenciaisEmail(String email);
}
