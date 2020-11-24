package xadrez;

public class Peao extends Peca {

    public Peao(char cor) {
        super(cor);
    }

    // Usa unicode para printar seu simbolo.
    @Override
    public void desenho() {
        if (this.getCor() == 'b') {
            System.out.print('\u2659');
        } else {
            System.out.print('\u265F');
        }
    }
  
    @Override
    public boolean checaMovimento(Posicao pos_orig, Posicao pos_dest) {
        // Se destino for a posicao de origem retorne falso
        if (pos_dest.getLinha() == pos_orig.getLinha() && pos_dest.getColuna() == pos_orig.getColuna()) {
            return false;
        }

        if (this.getCor() == 'b') {
            // Se esta na linha 2, tem como destino uma posicao 2 linhas para frente e na mesma coluna, eh
            // considerado movimento de 2 casas do peao branco na posicao de inicio portanto true.
            if (pos_orig.getLinha() == 2) {
                if (pos_dest.getColuna() == pos_orig.getColuna()) {
                    if (pos_dest.getLinha() - pos_orig.getLinha() == 2) {
                        return true;
                    }
                }
            }
            // Se a diferenca entre as linhas for 1 e a diferenca entre as colunas for 0 ou 1,
            // o peao esta efetuando movimento de comer para frente(brancas) ou andar uma para frente.
            if (pos_dest.getLinha() - pos_orig.getLinha() == 1) {
                if (Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) == 1 ||
                     Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) == 0) {
                    return true;
                }
                return false;
            }
            return false;
        } else {
            // Se esta na linha 7, tem como destino uma posicao 2 linhas para tras e na mesma coluna, eh
            // considerado movimento de 2 casas do peao preto na posicao de inicio portanto true.
            if (pos_orig.getLinha() == 7) {
                if (pos_dest.getColuna() == pos_orig.getColuna()) {
                    if (pos_dest.getLinha() - pos_orig.getLinha() == -2) {
                        return true;
                    }
                }
            }
            // Se a diferenca entre as linhas for -1 e das colunas 1 ou 0,
            // o peao esta efetuando movimento de comer para tras(pretas) ou andar uma para tras portanto eh valido.
            if (pos_dest.getLinha() - pos_orig.getLinha() == -1) {
                if (Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) == 1 ||
                     Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) == 0) {
                    return true;
                }
                return false;
            }
            return false;
        }

    }

}
