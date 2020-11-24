package xadrez;

public class Cavalo extends Peca {

    public Cavalo(char cor) {
        super(cor);
    }

    // Usa unicode para printar seu simbolo.
    @Override
    public void desenho() {
        if (this.getCor() == 'b') {
            System.out.print('\u2658');
        } else {
            System.out.print('\u265E');
        }
    }
    @Override
    public boolean checaMovimento(Posicao pos_orig, Posicao pos_dest) {
        // Se destino for a posicao de origem retorne falso
        if (pos_dest.getLinha() == pos_orig.getLinha() && pos_dest.getColuna() == pos_orig.getColuna()) {
            return false;
        }
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
