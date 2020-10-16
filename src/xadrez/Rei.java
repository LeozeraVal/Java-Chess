package xadrez;

public class Rei {

    private char cor;

    public Rei(char cor) {
        if (cor == 'p' || cor == 'b') {
            this.cor = cor;
        } else {
            System.out.println("Uma peca tem que ser preta ou branca.");
        }
    }

    public char getCor() {
        return this.cor;
    }

    // Usa unicode para printar seu simbolo.
    public void desenho() {
        if (this.getCor() == 'b') {
            System.out.print('\u2654');
        } else {
            System.out.print('\u265A');
        }
    }

    public boolean checaMovimento(Posicao pos_orig, Posicao pos_dest) {
        // Se destino for a posicao de origem retorne falso
        if (pos_dest.getLinha() == pos_orig.getLinha() && pos_dest.getColuna() == pos_orig.getColuna()) {
            return false;
        }
        // Se a diferenca entre as linhas E a diferenca entre as colunas for menor ou igual a 1, o movimento eh valido, cobre todos os casos
        if (Math.abs(pos_dest.getLinha() - pos_orig.getLinha()) <= 1 && Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) <= 1) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }

}
