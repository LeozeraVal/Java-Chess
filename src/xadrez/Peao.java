package xadrez;

/**
 * Classe filha de Peca da peca Peao.
 * @author Leonardo Valerio
 */
public class Peao extends Peca {

    /**
     * @param cor Indica qual a cor do Peao a ser criado.
     */
    public Peao(char cor) {
        super(cor);
    }

    @Override
    public void desenho() {
        // Usa unicode para printar seu simbolo.
        if (this.getCor() == 'b') {
            System.out.print('\u2659');
        } else {
            System.out.print('\u265F');
        }
    }
  
    @Override
    public boolean checaMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
        // Se destino for a posicao de origem retorne falso
        if (linha_dest == linha_orig && coluna_dest == coluna_orig) {
            return false;
        }

        if (this.getCor() == 'b') {
            // Se esta na linha 2, tem como destino uma posicao 2 linhas para frente e na mesma coluna, eh
            // considerado movimento de 2 casas do peao branco na posicao de inicio portanto true.
            if (linha_orig == 2) {
                if (coluna_dest == coluna_orig) {
                    if (linha_dest - linha_orig == 2) {
                        return true;
                    }
                }
            }
            // Se a diferenca entre as linhas for 1 e a diferenca entre as colunas for 0 ou 1,
            // o peao esta efetuando movimento de comer para frente(brancas) ou andar uma para frente.
            if (linha_dest - linha_orig == 1) {
                if (Math.abs(coluna_dest - coluna_orig) == 1 ||
                     Math.abs(coluna_dest - coluna_orig) == 0) {
                    return true;
                }
                return false;
            }
            return false;
        } else {
            // Se esta na linha 7, tem como destino uma posicao 2 linhas para tras e na mesma coluna, eh
            // considerado movimento de 2 casas do peao preto na posicao de inicio portanto true.
            if (linha_orig == 7) {
                if (coluna_dest == coluna_orig) {
                    if (linha_dest - linha_orig == -2) {
                        return true;
                    }
                }
            }
            // Se a diferenca entre as linhas for -1 e das colunas 1 ou 0,
            // o peao esta efetuando movimento de comer para tras(pretas) ou andar uma para tras portanto eh valido.
            if (linha_dest - linha_orig == -1) {
                if (Math.abs(coluna_dest - coluna_orig) == 1 ||
                     Math.abs(coluna_dest - coluna_orig) == 0) {
                    return true;
                }
                return false;
            }
            return false;
        }

    }

}
