package casevalidasenha.validasenhapi.service;

import java.util.List;

import casevalidasenha.validasenhapi.strategy.ValidacaoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ValidaSenhaService {
    private final ValidacaoStrategy validacaoStrategy;

    @Autowired
    public ValidaSenhaService(final ValidacaoStrategy validacaoStrategy) {
        this.validacaoStrategy = validacaoStrategy;
    }

    public List<String> validarSenha(final String senha) {
        return validacaoStrategy.validar(senha);
    }
}