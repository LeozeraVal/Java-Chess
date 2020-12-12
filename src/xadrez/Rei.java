package xadrez;

public class Rei extends Peca {

    public Rei(char cor) {
        super(cor);
    }

    // Usa unicode para printar seu simbolo.
    @Override
    public void desenho() {
        if (this.getCor() == 'b') {
            System.out.print('\u2654');
        } else {
            System.out.print('\u265A');
        }
    }

    @Override
    public boolean checaMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
        // Se destino for a posicao de origem retorne falso
        if (linha_dest == linha_orig && coluna_dest == coluna_orig) {
            return false;
        }
        // Se a diferenca entre as linhas E a diferenca entre as colunas for menor ou igual a 1, o movimento eh valido, cobre todos os casos
        if (Math.abs(linha_dest - linha_orig) <= 1 && Math.abs(coluna_dest - coluna_orig) <= 1) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }

}
