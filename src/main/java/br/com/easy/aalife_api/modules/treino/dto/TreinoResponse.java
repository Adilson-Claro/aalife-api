package br.com.easy.aalife_api.modules.treino.dto;

import br.com.easy.aalife_api.modules.treino.enums.EExercicio;
import br.com.easy.aalife_api.modules.treino.enums.EGrupoMuscular;
import br.com.easy.aalife_api.modules.treino.model.Treino;

public record TreinoResponse(String nome,
                             Integer series,
                             Double peso,
                             EGrupoMuscular grupoMuscular,
                             EExercicio exercicio,
                             String urlVideoExercicio,
                             String urlImagemExercicio) {

    public static TreinoResponse of(Treino treino) {
        return new TreinoResponse(
                treino.getNome(),
                treino.getSeries(),
                treino.getPeso(),
                treino.getGrupoMuscular(),
                treino.getExercicio(),
                treino.getUrlVideoExercicio(),
                treino.getUrlImagemExercicio()
        );
    }
}
