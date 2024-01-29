package casevalidasenha.validasenhapi.controller;

import casevalidasenha.validasenhapi.entity.response.ValidationResponse;
import casevalidasenha.validasenhapi.service.ValidaSenhaService;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ValidaSenhaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ValidaSenhaService validaSenhaService;

    @InjectMocks
    private ValidaSenhaController validaSenhaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(validaSenhaController).build();
    }

    @Test
    void deveRetornarValidacaoSenha() throws Exception {
        final String senha = "Teste@123";
        final List<String> expectedValidations = Arrays.asList("validacao1", "validacao2");

        when(validaSenhaService.validarSenha(anyString())).thenReturn(expectedValidations);

        mockMvc.perform(post("/valida-senha/senha/{senhaUsuario}", senha)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> Assertions.assertFalse(ObjectUtils.isEmpty(result.getResponse())));

    }
}
