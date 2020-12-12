package xadrez;

/**
 * Classe filha de Peca da peca Torre.
 * @author Leonardo Valerio
 */
public class Torre extends Peca {

    /**
     * @param cor Indica qual a cor da Torre a ser criada.
     */
    public Torre(char cor) {
        super(cor);
    }

    @Override
    public void desenho() {
        // Usa unicode para printar seu simbolo.
        if (this.getCor() == 'b') {
            System.out.print('\u2656');
        } else if (this.getCor() == 'p') {
            System.out.print('\u265C');
        }
    }

    @Override
    public boolean checaMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
        // Se destino for a posicao de origem retorne falso
        if (linha_dest == linha_orig && coluna_dest == coluna_orig) {
            return false;
        }

        // Se a linha for igual mas a coluna diferente, eh um movimento valido, pois se move em sua linha.
        if (linha_orig == linha_dest && coluna_orig != coluna_dest) {
            return true;
        }

        // Se a coluna for igual mas a linha diferente, eh um movimento valido, pois se move em sua coluna.
        if (linha_orig != linha_dest && coluna_orig == coluna_dest) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }
    
}
