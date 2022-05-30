package casevalidasenha.validasenhapi.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import casevalidasenha.validasenhapi.model.SenhaModel;
import casevalidasenha.validasenhapi.service.ValidaSenhaService;


@SpringBootTest(classes = { ValidaSenhaService.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ActiveProfiles("teste")
public class ValidaSenhaApiTest {
	
	@Autowired
	private ValidaSenhaService validaSenhaService;
	
	
	@Test
	public void verificaRetornoNullAutenticacaoSenhaService() {
		assertNotNull(validaSenhaService);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void senhaVerificadaComSucesso() throws Exception {
		SenhaModel senha = new SenhaModel();
		String senhaTeste = "Asdfghjkl@123";
		senha.setSenha(senhaTeste);
				
		assertNotNull(validaSenhaService.verificarValidacoesSenha(senha));
		assertNotNull(validaSenhaService.validarCaracteresObrigatorios(senhaTeste));
		assertNotNull(validaSenhaService.verificarEspacos(senhaTeste));
		assertNotNull(validaSenhaService.verificarQuantidadeCaracteres(senhaTeste));
		assertNotNull(validaSenhaService.verificarCaracteresRepetidos(senhaTeste));
		assertEquals(senhaTeste, senha.getSenha());
		
		
	}

}

