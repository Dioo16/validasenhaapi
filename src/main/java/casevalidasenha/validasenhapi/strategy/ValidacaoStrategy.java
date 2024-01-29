package casevalidasenha.validasenhapi.strategy;
import java.util.List;

public interface ValidacaoStrategy {
    List<String> validar(final String senha);

}
