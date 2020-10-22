package xadrez;

public class Dama {

    private char cor;

    public Dama(char cor) {
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
            System.out.print('\u2655');
        } else {
            System.out.print('\u265B');
        }
    }
    

    public boolean checaMovimento(Posicao pos_orig, Posicao pos_dest) {
        // Se destino for a posicao de origem retorne falso
        if (pos_dest.getLinha() == pos_orig.getLinha() && pos_dest.getColuna() == pos_orig.getColuna()) {
            return false;
        }
        
        // Uma dama tem sua checagem de movimento como uma juncao das checagens da torre e do bispo, portanto reutilizamos o codigo.
        
        if (pos_orig.getLinha() == pos_dest.getLinha() && pos_orig.getColuna() != pos_dest.getColuna()) {
            return true;
        }

        if (pos_orig.getLinha() != pos_dest.getLinha() && pos_orig.getColuna() == pos_dest.getColuna()) {
            return true;
        }

        if (Math.abs(pos_dest.getLinha() - pos_orig.getLinha()) == Math.abs(pos_dest.getColuna() - pos_orig.getColuna())) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }
    
}
