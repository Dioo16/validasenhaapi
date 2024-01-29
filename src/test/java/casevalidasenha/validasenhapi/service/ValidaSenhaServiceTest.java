package casevalidasenha.validasenhapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import casevalidasenha.validasenhapi.strategy.ValidacaoStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import casevalidasenha.validasenhapi.service.ValidaSenhaService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = {})
@ActiveProfiles("teste")
public class ValidaSenhaServiceTest {

	private ValidaSenhaService validaSenhaService;
	private ValidacaoStrategy validacaoStrategy;

	@BeforeEach
	void setUp() {
		validacaoStrategy = mock(ValidacaoStrategy.class);
		validaSenhaService = new ValidaSenhaService(validacaoStrategy);
	}

	@Test
	void testValidarSenhaComSucesso() {
		final String senha = "Teste@123";
		final List<String> expectedValidations = Collections.emptyList();
		when(validacaoStrategy.validar(senha)).thenReturn(expectedValidations);

		final List<String> result = validaSenhaService.validarSenha(senha);

		assertTrue(result.isEmpty());
		verify(validacaoStrategy, times(1)).validar(senha);
	}

	@Test
	void testValidarSenhaComValidacoes() {
		final String senha = "Teste";
		final List<String> expectedValidations = Arrays.asList("validacao1", "validacao2");
		when(validacaoStrategy.validar(senha)).thenReturn(expectedValidations);

		final List<String> result = validaSenhaService.validarSenha(senha);

		assertEquals(expectedValidations, result);
		verify(validacaoStrategy, times(1)).validar(senha);
	}

	@Test
	void testValidarSenhaComExcecao() {
		final String senha = "";
		when(validacaoStrategy.validar(senha)).thenThrow(RuntimeException.class);
		assertThrows(RuntimeException.class, () -> {
			validaSenhaService.validarSenha(senha);
		});
		verify(validacaoStrategy, times(1)).validar(senha);
	}

}