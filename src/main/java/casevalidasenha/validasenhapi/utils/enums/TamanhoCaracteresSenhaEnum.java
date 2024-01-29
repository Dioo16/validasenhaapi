package casevalidasenha.validasenhapi.utils.enums;

public enum TamanhoCaracteresSenhaEnum {
        MINIMO(9),
        MAXIMO(30);

        private final int tamanho;

    TamanhoCaracteresSenhaEnum(int tamanho) {
            this.tamanho = tamanho;
        }
        public int getTamanho() {
            return tamanho;
        }
}
