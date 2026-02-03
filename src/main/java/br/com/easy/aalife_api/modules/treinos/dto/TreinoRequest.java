package br.com.easy.aalife_api.modules.treinos.dto;

import br.com.easy.aalife_api.modules.treinos.enums.EExercicio;
import br.com.easy.aalife_api.modules.treinos.enums.EGrupoMuscular;

public record TreinoRequest(String nome,
                            Integer series,
                            Double peso,
                            EGrupoMuscular grupoMuscular,
                            EExercicio exercicio) {
}
