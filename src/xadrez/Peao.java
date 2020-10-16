package xadrez;

public class Peao {

    private char cor;

    public Peao(char cor) {
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
            System.out.print('\u2659');
        } else {
            System.out.print('\u265F');
        }
    }
  
    public boolean checaMovimento(Posicao pos_orig, Posicao pos_dest) {

        if (this.getCor() == 'b') {
            // Se esta na mesma coluna e a diferenca entre as linhas for menor ou igual a 2 e maior que 0,
            // O movimento eh valido como movimento para frente(brancas) ou como movimento de inicio.
            if (pos_dest.getColuna() == pos_orig.getColuna()) {
                if (pos_dest.getLinha() - pos_orig.getLinha() <= 2 && pos_dest.getLinha() - pos_orig.getLinha() > 0) {
                    return true;
                }
                return false;
            }
            // Se a diferenca entre as colunas e a diferenca entre as linhas for 1,
            // o peao esta efetuando movimento de comer para frente(brancas) portanto eh valido.
            if (pos_dest.getLinha() - pos_orig.getLinha() == 1) {
                if (Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) == 1) {
                    return true;
                }
                return false;
            }
            return false;
        } else {
            // Se esta na mesma coluna e a diferenca entre as linhas for maior ou igual a -2 e menor que 0,
            // O movimento eh valido como movimento para tras (pretas) ou como movimento de inicio.
            if (pos_dest.getColuna() == pos_orig.getColuna()) {
                if (pos_dest.getLinha() - pos_orig.getLinha() >= -2 && pos_dest.getLinha() - pos_orig.getLinha() < 0) {
                    return true;
                }
                return false;
            }
            // Se a diferenca entre as coluna for 1 e das linhas -1
            // o peao esta efetuando movimento de comer para tras(pretas) portanto eh valido.
            if (pos_dest.getLinha() - pos_orig.getLinha() == -1) {
                if (Math.abs(pos_dest.getColuna() - pos_orig.getColuna()) == 1) {
                    return true;
                }
                return false;
            }
            return false;
        }

    }

}
