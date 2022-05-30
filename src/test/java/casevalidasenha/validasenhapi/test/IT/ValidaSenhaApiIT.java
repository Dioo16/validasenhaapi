package casevalidasenha.validasenhapi.test.IT;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;




@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ValidaSenhaApiIT {
	
	
	 @LocalServerPort
	 private int port;
		 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 @Autowired
	    private MockMvc mockMvc;
	 
	 
 	@Test
	@Order(1)
	public void contextLoad() {
		assertNotNull(mockMvc);
		
	}
 	
 	@Test
	@Order(2)
 	public void  verificarRetornoApiSenha() throws Exception {
 		
 		this.mockMvc.perform(get("/valida-senha/senha/teste@123")).andExpect(status().isOk());
 	}
 	
 	

}
