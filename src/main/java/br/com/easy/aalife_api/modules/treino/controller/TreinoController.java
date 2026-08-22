package br.com.easy.aalife_api.modules.treino.controller;

import br.com.easy.aalife_api.modules.treino.dto.TreinoRequest;
import br.com.easy.aalife_api.modules.treino.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/treino")
public class TreinoController {

    private final TreinoService service;

    @PostMapping
    public void salvar(@RequestPart TreinoRequest request,
                       @RequestPart MultipartFile imagem,
                       @RequestPart MultipartFile video) {
        service.salvar(request, imagem, video);
    }
}
