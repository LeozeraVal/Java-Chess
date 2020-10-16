package xadrez;

public class Cavalo {

    private char cor;

    public Cavalo(char cor) {
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
            System.out.print('\u2658');
        } else {
            System.out.print('\u265E');
        }
    }

    public boolean checaMovimento(Posicao pos_orig, Posicao pos_dest) {
        // Se a diferenca entre a linha for 3, a diferenca entre coluna tem que ser 1
        if (Math.abs(pos_dest.getLinha() - pos_orig.getLinha()) == 2 && Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) == 1) {
            return true;
        }

        // Se a diferenca entre a linha for 1, a diferenca entre coluna tem que ser 3
        if (Math.abs(pos_dest.getLinha() - pos_orig.getLinha()) == 1 && Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) == 2) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }

}
