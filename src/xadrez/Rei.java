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
