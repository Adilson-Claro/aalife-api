package br.com.easy.aalife_api.modules.treino.service;

import br.com.easy.aalife_api.config.minio.service.MinioService;
import br.com.easy.aalife_api.modules.treino.dto.TreinoRequest;
import br.com.easy.aalife_api.modules.treino.model.Treino;
import br.com.easy.aalife_api.modules.treino.repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final MinioService minioService;
    private final TreinoRepository repository;

    @SneakyThrows
    public void salvar(TreinoRequest request, MultipartFile imagem, MultipartFile video) {
        var image = minioService.upload(imagem);

        var treino = Treino.of(request, image, video.getOriginalFilename());
        repository.save(treino);
    }
}
