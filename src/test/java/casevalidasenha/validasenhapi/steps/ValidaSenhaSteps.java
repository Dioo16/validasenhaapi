package casevalidasenha.validasenhapi.steps;

import casevalidasenha.validasenhapi.service.ValidaSenhaService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;

import io.cucumber.java.pt.Quando;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidaSenhaSteps {
    private String senha;
    @Autowired
    private ValidaSenhaService validaSenhaService;
    private List<String> validacoesSenha;

    @Dado("que a senha é {string}")
    public void aSenhaE(String senha) {
        this.senha = senha;
    }

    @Quando("o usuário valida a senha")
    public void usuarioValidaSenha() {
        this.validacoesSenha = validaSenhaService.validarSenha(senha);

    }

    @Entao("a resposta deve conter uma lista vazia de validações")
    public void respostaNaoDeveConterValidacoes() {
        assertTrue(ObjectUtils.isEmpty(validacoesSenha));
    }

    @Entao("a resposta deve conter uma lista de validações não vazia")
    public void respostaDeveConterListaDeValidacoes() {
        assertFalse(ObjectUtils.isEmpty(validacoesSenha));
    }
}
