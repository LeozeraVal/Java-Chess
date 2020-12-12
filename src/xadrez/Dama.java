package xadrez;

/**
 * Classe filha de Peca da peca Dama.
 * @author Leonardo Valerio
 */
public class Dama extends Peca {

    /**
     * @param cor Indica qual a cor da Dama a ser criada.
     */
    public Dama(char cor) {
        super(cor);
    }

    @Override
    public void desenho() {
        // Usa unicode para printar seu simbolo.
        if (this.getCor() == 'b') {
            System.out.print('\u2655');
        } else {
            System.out.print('\u265B');
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

        // Se a diferenca entre a linha do destino e da origem for igual a diferenca entre a coluna
        // do destino e a coluna da origem, o movimento eh valido, pois se move em uma diagonal.
        // (Usamos abs para contar em todas as 4 diagonais).
        if (Math.abs(linha_dest - linha_orig) == Math.abs(coluna_dest - coluna_orig)) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }
    
}
