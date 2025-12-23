package br.com.easy.aalife_api.modules.usuario.administrador.repository;

import br.com.easy.aalife_api.modules.usuario.administrador.model.UsuarioAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioAdministradorRepository extends JpaRepository<UsuarioAdministrador, Integer> {

    boolean existsByCpf(String cpf);

    boolean existsByTelefone(String telefone);

    boolean existsByTelefoneAndIdNot(String telefone, Integer id);

    boolean existsByEmail(String email);
}
