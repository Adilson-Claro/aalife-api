package br.com.easy.aalife_api.modules.treino.model;

import br.com.easy.aalife_api.modules.treino.dto.TreinoRequest;
import br.com.easy.aalife_api.modules.treino.enums.EExercicio;
import br.com.easy.aalife_api.modules.treino.enums.EGrupoMuscular;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TREINO")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TREINO")
    @SequenceGenerator(name = "SEQ_TREINO", sequenceName = "SEQ_TREINO", allocationSize = 1)
    private Long id;

    private String nome;

    @Column(name = "PESO_KG")
    private Double peso;

    private Integer series;

    @Enumerated(EnumType.STRING)
    @Column(name = "GRUPO_MUSCULAR")
    private EGrupoMuscular grupoMuscular;

    @Enumerated(EnumType.STRING)
    @Column(name = "EXERCICIO")
    private EExercicio exercicio;

    @Column(name = "URL_IMG_EXERCICIO")
    private String urlImagemExercicio;

    @Column(name = "URL_VIDEO_EXERCICIO")
    private String urlVideoExercicio;

    @Column(name = "TEMPO_PAUSA_SEGUNDOS")
    private Integer tempoPausaSegundos;

    public static Treino of(TreinoRequest request, String urlImagemExercicio, String urlVideoExercicio) {
        return Treino.builder()
                .nome(request.nome())
                .series(request.series())
                .peso(request.peso())
                .grupoMuscular(request.grupoMuscular())
                .exercicio(request.exercicio())
                .urlImagemExercicio(urlImagemExercicio)
                .urlVideoExercicio(urlVideoExercicio)
                .tempoPausaSegundos(request.tempoPausa())
                .build();
    }

    public void editar(TreinoRequest request, String urlImagemExercicio, String urlVideoExercicio) {
        this.exercicio = request.exercicio();
        this.grupoMuscular = request.grupoMuscular();
        this.nome = request.nome();
        this.peso = request.peso();
        this.series = request.series();
        this.urlImagemExercicio = urlImagemExercicio;
        this.urlVideoExercicio = urlVideoExercicio;
    }
}
