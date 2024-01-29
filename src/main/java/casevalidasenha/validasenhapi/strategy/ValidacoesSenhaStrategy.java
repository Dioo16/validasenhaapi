package casevalidasenha.validasenhapi.strategy;

import casevalidasenha.validasenhapi.utils.enums.TamanhoCaracteresSenhaEnum;
import casevalidasenha.validasenhapi.utils.enums.ValidacoesSenhasEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class ValidacoesSenhaStrategy implements ValidacaoStrategy {
    @Override
    public List<String > validar(final String senha) {
        return varificaSenhaValida(senha);
    }

    private List<String> varificaSenhaValida(final String senha) {
        final Map<String, Predicate<String>> regras = new HashMap<>();

        regras.put(ValidacoesSenhasEnum.NOVE_OU_MAIS_CARACTERES.getMensagem(), senhaCompleta -> senhaCompleta.length() >= TamanhoCaracteresSenhaEnum.MINIMO.getTamanho());
        regras.put(ValidacoesSenhasEnum.AO_MENOS_UM_DIGITO.getMensagem(), senhaCompleta -> senhaCompleta.matches(".*\\d.*"));
        regras.put(ValidacoesSenhasEnum.AO_MENOS_UMA_LETRA_MINUSCULA.getMensagem(),  senhaCompleta -> senhaCompleta.matches(".*[a-z].*"));
        regras.put(ValidacoesSenhasEnum.AO_MENOS_UMA_LETRA_MAIUSCULA.getMensagem(),  senhaCompleta -> senhaCompleta.matches(".*[A-Z].*"));
        regras.put(ValidacoesSenhasEnum.AO_MENOS_UM_CARACTERE_ESPECIAL.getMensagem(),  senhaCompleta -> senhaCompleta.matches(".*[!@#$%^&*()-+].*"));
        regras.put(ValidacoesSenhasEnum.POSSUI_CARACTERES_REPETIDOS.getMensagem(), this::isPossuiCaracteresRepetidos);
        regras.put(ValidacoesSenhasEnum.POSSUI_ESPACOS_EM_BRANCO.getMensagem(),  this::isNaoTemEspacosEmBranco);

        return regras.entrySet().stream()
                .filter(entry -> !entry.getValue().test(senha))
                .map(Map.Entry::getKey).toList();
    }

    private boolean isNaoTemEspacosEmBranco( final String senha) {
        return !senha.contains(" ");
    }
    private boolean isPossuiCaracteresRepetidos(String senha) {
        return (senha.length() != senha.chars().distinct().count());
    }
}
