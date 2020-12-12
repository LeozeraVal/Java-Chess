package xadrez;

/**
 * Classe filha de Peca da peca Cavalo.
 * @author Leonardo Valerio
 */
public class Cavalo extends Peca {
    
    /**
     * @param cor Indica qual a cor do Cavalo a ser criado.
     */
    public Cavalo(char cor) {
        super(cor);
    }

    @Override
    public void desenho() {
        // Usa unicode para printar seu simbolo.
        if (this.getCor() == 'b') {
            System.out.print('\u2658');
        } else {
            System.out.print('\u265E');
        }
    }
    
    @Override
    public boolean checaMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
        // Se destino for a posicao de origem retorne falso
        if (linha_dest == linha_orig && coluna_dest == coluna_orig) {
            return false;
        }
        // Se a diferenca entre a linha for 3, a diferenca entre coluna tem que ser 1
        if (Math.abs(linha_dest - linha_orig) == 2 && Math.abs(coluna_dest - coluna_orig) == 1) {
            return true;
        }

        // Se a diferenca entre a linha for 1, a diferenca entre coluna tem que ser 3
        if (Math.abs(linha_dest - linha_orig) == 1 && Math.abs(coluna_dest - coluna_orig) == 2) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }

}
