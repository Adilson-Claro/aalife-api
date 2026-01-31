package br.com.easy.aalife_api.modules.treinos.service;

import br.com.easy.aalife_api.config.exceptions.NotFoundException;
import br.com.easy.aalife_api.modules.treinos.dto.TreinoRequest;
import br.com.easy.aalife_api.modules.treinos.model.Treino;
import br.com.easy.aalife_api.modules.treinos.repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository repository;

    public void salvar(TreinoRequest request, String imagemExercicioUrl, String videoExercicioUrl) {
        var treino = Treino.of(request, imagemExercicioUrl, videoExercicioUrl);

        repository.save(treino);
    }

    public void editar(Integer id, TreinoRequest request, String imagemExercicioUrl, String videoExercicioUrl) {
        var treino = findById(id);

        treino.editar(request, imagemExercicioUrl, videoExercicioUrl);

        repository.save(treino);
    }

    public Treino findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Treino nao encontrado"));
    }
}
