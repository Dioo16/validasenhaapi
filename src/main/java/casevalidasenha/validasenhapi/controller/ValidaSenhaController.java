package casevalidasenha.validasenhapi.controller;
import casevalidasenha.validasenhapi.entity.response.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import casevalidasenha.validasenhapi.service.ValidaSenhaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
public class ValidaSenhaController {
    @Autowired
    private ValidaSenhaService validaSenhaService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado a validação da senha e suas verificações"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value = "/valida-senha/senha/{senhaUsuario}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ValidationResponse> validaSenha(@PathVariable final String senhaUsuario) {
        final List<String> senhaValidadaVerificacoes = this.validaSenhaService.validarSenha(senhaUsuario);
        final ValidationResponse response = new ValidationResponse(senhaValidadaVerificacoes);
        return ResponseEntity.ok(response);
    }
}
	