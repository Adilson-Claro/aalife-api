package br.com.easy.aalife_api.modules.usuario.administrador.controller.contract;

import br.com.easy.aalife_api.modules.usuario.administrador.dto.UsuarioAdministradorRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Administrador")
public interface IUsuarioAdministradorController {

    @PostMapping
    @Operation(description = "Endpoint responsavel por cadastrar um administrador.")
    void salvar(@RequestBody @Valid UsuarioAdministradorRequest request);

    @PutMapping("{id}/editar")
    @Operation(description = "Endpoint responsavel por editar um administrador.")
    void editar(@PathVariable Integer id, @RequestBody UsuarioAdministradorRequest request);

    @PutMapping("{id}")
    @Operation(description = "Endpoint responsavel por alterar a situacao de um administrador.")
    void alterarSituacao(@PathVariable Integer id);
}
