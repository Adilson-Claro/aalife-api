package br.com.easy.aalife_api.modules.treinos.repository;

import br.com.easy.aalife_api.modules.treinos.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Integer> {
}
