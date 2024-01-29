package casevalidasenha.validasenhapi.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ValidationResponse {
    private List<String> validacoes;
}


