package xadrez;

public class Torre extends Peca {

    public Torre(char cor) {
        super(cor);
    }

    // Usa unicode para printar seu simbolo.
    @Override
    public void desenho() {
        if (this.getCor() == 'b') {
            System.out.print('\u2656');
        } else if (this.getCor() == 'p') {
            System.out.print('\u265C');
        }
    }

    @Override
    public boolean checaMovimento(Posicao pos_orig, Posicao pos_dest) {
        // Se destino for a posicao de origem retorne falso
        if (pos_dest.getLinha() == pos_orig.getLinha() && pos_dest.getColuna() == pos_orig.getColuna()) {
            return false;
        }

        // Se a linha for igual mas a coluna diferente, eh um movimento valido, pois se move em sua linha.
        if (pos_orig.getLinha() == pos_dest.getLinha() && pos_orig.getColuna() != pos_dest.getColuna()) {
            return true;
        }

        // Se a coluna for igual mas a linha diferente, eh um movimento valido, pois se move em sua coluna.
        if (pos_orig.getLinha() != pos_dest.getLinha() && pos_orig.getColuna() == pos_dest.getColuna()) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }
    
}
