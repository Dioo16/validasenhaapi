package casevalidasenha.validasenhapi.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import casevalidasenha.validasenhapi.model.SenhaModel;
import casevalidasenha.validasenhapi.service.ValidaSenhaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ValidaSenhaController {
		@Autowired
		private ValidaSenhaService validaSenhaService;
		
		
		@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Retornado a validação da senha e suas verificações"),
			    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
			})
		@RequestMapping(value = "/valida-senha/senha/{senhaUsuario}", method = RequestMethod.GET, produces="application/json")
		@ResponseBody
		public ResponseEntity<String> validaSenha(@PathVariable String senhaUsuario) throws Exception {
		SenhaModel senha = new SenhaModel();
		senha.setSenha(senhaUsuario);
		
		try {
			
			String senhaValidadaVerificacoes = this.validaSenhaService.verificarValidacoesSenha(senha);
			return ResponseEntity.ok(senhaValidadaVerificacoes);
			
		} catch (JSONException e) {
			String errorParametrosBody = "{\"erroNoParametro\":\"parametroNaoReconhecido\"}";
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorParametrosBody);
			
		}
		
		}
			
	}
	

