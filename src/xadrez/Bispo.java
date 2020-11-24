package xadrez;

public class Bispo extends Peca {

    public Bispo(char cor) {
        super(cor);
    }

    // Usa unicode para printar seu simbolo.
    @Override
    public void desenho() {
        if (this.getCor() == 'b') {
            System.out.print('\u2657');
        } else {
            System.out.print('\u265D');
        }
    }

    @Override
    public boolean checaMovimento(Posicao pos_orig, Posicao pos_dest) {
        // Se estiver na mesma posicao entraria como valido, portanto isolamos este caso.
        if (pos_dest.getLinha() == pos_orig.getLinha() && pos_dest.getColuna() == pos_orig.getColuna()) {
            return false;
        }
        // Se a diferenca entre a linha do destino e da origem for igual a diferenca entre a coluna
        // do destino e a coluna da origem, o movimento eh valido. (Usamos abs para contar em todas as 4 diagonais).
        if (Math.abs(pos_dest.getLinha() - pos_orig.getLinha()) == Math.abs(pos_dest.getColuna() - pos_orig.getColuna())) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }

    
}
