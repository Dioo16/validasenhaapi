package casevalidasenha.validasenhapi.strategy;

import casevalidasenha.validasenhapi.strategy.ValidacoesSenhaStrategy;
import casevalidasenha.validasenhapi.utils.enums.ValidacoesSenhasEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidacoesSenhaStrategyTest {

    private ValidacoesSenhaStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new ValidacoesSenhaStrategy();
    }

    @Test
    void deveValidarSenha() {
        final String senha = "Teste@123";
        final List<String> result = strategy.validar(senha);
        assertTrue(result.isEmpty());
    }

    @Test
    void deveValidarSenhaTamanho() {
        final String senha = "Teste1@";
        final List<String> result = strategy.validar(senha);
        assertEquals(ValidacoesSenhasEnum.NOVE_OU_MAIS_CARACTERES.getMensagem(), result.get(0));
    }

    @Test
    void deveValidarSenhaDigitos() {
        final String senha = "Teste";
        final List<String> result = strategy.validar(senha);
        assertEquals(ValidacoesSenhasEnum.AO_MENOS_UM_DIGITO.getMensagem(), result.get(0));
    }

    @Test
    void deveValidarSenhaLetraMinuscula() {
        final String senha = "TESTE@123";
        final List<String> result = strategy.validar(senha);
        assertEquals(ValidacoesSenhasEnum.AO_MENOS_UMA_LETRA_MINUSCULA.getMensagem(), result.get(0));
    }

    @Test
    void deveValidarSenhaLetraMaiuscula() {
        final String senha = "teste@123";
        final List<String> result = strategy.validar(senha);
        assertEquals(ValidacoesSenhasEnum.AO_MENOS_UMA_LETRA_MAIUSCULA.getMensagem(), result.get(0));
    }

    @Test
    void deveValidarSenhaCaractereEspecial() {
        final String senha = "Teste123";
        final List<String> result = strategy.validar(senha);
        assertEquals(ValidacoesSenhasEnum.AO_MENOS_UM_CARACTERE_ESPECIAL.getMensagem(), result.get(0));
    }

    @Test
    void deveValidarSenhaCaracteresRepetidos() {
        final String senha = "Teste@123";
        final List<String> result = strategy.validar(senha);
        assertTrue(result.isEmpty());
    }

    @Test
    void deveValidarSenhaComEspacoEmBranco() {
        final String senha = "Teste @123";
        final List<String> result = strategy.validar(senha);
        assertEquals(ValidacoesSenhasEnum.POSSUI_ESPACOS_EM_BRANCO.getMensagem(), result.get(0));
    }
}
