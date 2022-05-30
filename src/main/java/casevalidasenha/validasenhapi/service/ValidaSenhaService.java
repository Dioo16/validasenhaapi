package casevalidasenha.validasenhapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import casevalidasenha.validasenhapi.model.SenhaModel;


@Service
public class ValidaSenhaService {
	private  Pattern pattern;
	private JSONObject jsonVerificacoesSenha = new JSONObject();
	private JSONObject jsonSenhaValida = new JSONObject();
		
	public boolean validarCaracteresObrigatorios(String senha) throws JSONException {
		List<String> caracteresFaltantes = new ArrayList<String>();
		Map<String, String> Mapregex = new HashMap<>();
		Mapregex.put("Caracteres maisculo", "[A-Z]");
		Mapregex.put("Caracteres minusculo", "[a-z]");
		Mapregex.put("Caracter especial", "[!@#$%^&*()-+]");
		Mapregex.put("Digito", "[0-9]");
		
		for (var regex : Mapregex.entrySet()) {
			this.pattern = Pattern.compile(regex.getValue());
			Matcher match = pattern.matcher(senha);
			
			if(!match.find()) {
				
				caracteresFaltantes.add(regex.getKey());
			}
			
		}
		
		
		if(!caracteresFaltantes.isEmpty()) {
			this.jsonVerificacoesSenha.put("caracteresFaltantes", caracteresFaltantes);
			return false;
		}
		else {
			this.jsonVerificacoesSenha.put("caracteresFaltantes", "nenhum");
			return true;
		}
		
	}
	
	public boolean verificarEspacos(String senha) throws JSONException {
		String regexEspaco = "\s";
		this.pattern = Pattern.compile(regexEspaco);
		Matcher match = pattern.matcher(senha);
		
		if(match.find()){
			jsonVerificacoesSenha.put("espacoEmBranco", true);
			return false;
		}else {
			jsonVerificacoesSenha.put("espacoEmBranco", false);
			return true;
		}	
	}
	
	
	
	public boolean verificarCaracteresRepetidos(String senha) throws JSONException{
		StringBuilder senhaCaracteresDistintos = new StringBuilder();
		senha.chars().distinct().forEach(c -> senhaCaracteresDistintos.append((char) c));
		Integer totalCaracteresDistintosSenha = senhaCaracteresDistintos.length();
		Integer totalCaracteresSenha = senha.length();
		
		if(totalCaracteresDistintosSenha  < totalCaracteresSenha ) {
			this.jsonVerificacoesSenha.put("caracteresRepetidos", true);
			return false;
		}
		else {
			this.jsonVerificacoesSenha.put("caracteresRepetidos", false);
			return true;
		}
	     
		
	}
	
	public boolean verificarQuantidadeCaracteres(String senha) throws JSONException {
		Integer caracteresTotais = senha.length();
		Integer quantidadeMinimaCaracteres = 9;
		Integer quantidadeMaximaCaracteres = 30;
		
		if(caracteresTotais < quantidadeMinimaCaracteres) {
			this.jsonVerificacoesSenha.put("quantidadeDeCaracteresQueFaltam", quantidadeMinimaCaracteres - caracteresTotais);
			return false;
		}
		else if(caracteresTotais > quantidadeMaximaCaracteres){
			this.jsonVerificacoesSenha.put("quantidadeDeCaracteresQueDevemSerRetirados", caracteresTotais - quantidadeMaximaCaracteres);
			return false;
		}
		else {
				this.jsonVerificacoesSenha.put("quantidadeDeCaracteres", "ok");
				return true;
		}
	}
	
	public void limpaJsons() {
		this.jsonSenhaValida = new JSONObject();
		this.jsonVerificacoesSenha = new JSONObject(); 
	}
	
	public String verificarValidacoesSenha(SenhaModel senha) throws JSONException{
		limpaJsons();
		boolean validaCaracteresObrigatorios = validarCaracteresObrigatorios(senha.getSenha());
		boolean validaSemCaracteresRepetidos = verificarCaracteresRepetidos(senha.getSenha());
		boolean validaQuantidadeMinimaMaximaCaracteres = verificarQuantidadeCaracteres(senha.getSenha());
		boolean validaEspacos = verificarEspacos(senha.getSenha());
		
		
		if(validaCaracteresObrigatorios &&  validaSemCaracteresRepetidos && validaQuantidadeMinimaMaximaCaracteres && validaEspacos) {
			this.jsonSenhaValida.put("senhaVálida", true);
			this.jsonSenhaValida.put("Verificacoes", this.jsonVerificacoesSenha);
		}
		else {
			this.jsonSenhaValida.put("senhaVálida", false);
			this.jsonSenhaValida.put("Verificacoes", this.jsonVerificacoesSenha);
		}
		
		String validacaoSenhaVerificacoes = this.jsonSenhaValida.toString();
		
		return validacaoSenhaVerificacoes;
	
	}
	
	
}
