package casevalidasenha.validasenhapi.utils.enums;

public enum ValidacoesSenhasEnum {

    NOVE_OU_MAIS_CARACTERES("Nove ou mais caracteres"),
    AO_MENOS_UM_DIGITO("Ao menos 1 dígito"),
    AO_MENOS_UMA_LETRA_MINUSCULA("Ao menos 1 letra minúscula"),
    AO_MENOS_UMA_LETRA_MAIUSCULA("Ao menos 1 letra maiúscula"),
    AO_MENOS_UM_CARACTERE_ESPECIAL("Ao menos 1 caractere Especial(!@#$%^&*()-+)"),
    POSSUI_ESPACOS_EM_BRANCO("Possui espaços em branco"),
    POSSUI_CARACTERES_REPETIDOS("Possui caracteres repetidos");


    private final String mensagem;

    ValidacoesSenhasEnum(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
