package br.com.easy.aalife_api.modules.treinos.model;

import br.com.easy.aalife_api.modules.treinos.dto.TreinoRequest;
import br.com.easy.aalife_api.modules.treinos.enums.EExercicio;
import br.com.easy.aalife_api.modules.treinos.enums.EGrupoMuscular;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "treino")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "peso_kg")
    private Double peso;

    private Integer series;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_muscular")
    private EGrupoMuscular grupoMuscular;

    @Enumerated(EnumType.STRING)
    @Column(name = "exercicio")
    private EExercicio exercicio;

    @Column(name = "url_img_exercicio")
    private String urlImagemExercicio;

    @Column(name = "url_video_exercicio")
    private String urlVideoExercicio;

    public static Treino of(TreinoRequest request, String urlImagemExercicio, String urlVideoExercicio) {
        return Treino.builder()
                .nome(request.nome())
                .series(request.series())
                .peso(request.peso())
                .grupoMuscular(request.grupoMuscular())
                .exercicio(request.exercicio())
                .urlImagemExercicio(urlImagemExercicio)
                .urlVideoExercicio(urlVideoExercicio)
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
