package br.com.easy.aalife_api.modules.treino.dto;

import br.com.easy.aalife_api.modules.treino.enums.EExercicio;
import br.com.easy.aalife_api.modules.treino.enums.EGrupoMuscular;

public record TreinoRequest(String nome,
                            Integer series,
                            Double peso,
                            EGrupoMuscular grupoMuscular,
                            EExercicio exercicio,
                            Integer tempoPausa) {
}
