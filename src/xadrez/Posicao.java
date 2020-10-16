package xadrez;

public class Posicao {
    // possui uma cor, linha, coluna e no momento apenas uma booleana que simboliza se tem peca ou nao,
    // futuramente tera tambem uma peca.
    private char cor;
    private int linha;
    private char coluna;
    private boolean tem_peca;


    public Posicao(char cor, int linha, char coluna, boolean tem_peca) {
        //Checa consistencia da cor
        if (cor != 'b' && cor != 'p') {
            System.out.println("> ERRO! " + cor + " nao eh uma cor valida!");
            return;
        } else {
            this.cor = cor;
        }
        //Checa consistencia da linha
        if (linha > 8 || linha < 1) {
            System.out.println("> ERRO! " + linha + " nao eh uma linha valida!");
            return;
        } else {
            this.linha = linha;
        }
        //Checa consistencia da coluna
        if (coluna > 'h' || coluna < 'a') {
            System.out.println("> ERRO! " + coluna + " nao eh uma coluna valida!");
            return;
        } else {
            this.coluna = coluna;
        }
        this.tem_peca = tem_peca;
    }


    public char getCor() {
        return this.cor;
    }

    public int getLinha() {
        return this.linha;
    }

    // Apenas existe para testes, sera removido no programa final
    public void setLinha(int linha) {
        if (linha > 8 || linha < 1) {
            System.out.println("> ERRO! " + linha + " nao eh uma linha valida!");
        } else {
            this.linha = linha;
        }
    }

    public int getColuna() {
        return this.coluna;
    }

    // Apenas existe para testes, sera removido no programa final
    public void setColuna(char coluna) {
        if (coluna > 'h' || coluna < 'a') {
            System.out.println("> ERRO! " + coluna + " nao eh uma coluna valida!");
        } else {
            this.coluna = coluna;
        }
    }

    public boolean temPeca() {
        return this.tem_peca;
    }

    //Usado quando uma peca for retirada desta posicao
    public void setPeca(boolean tem) {
        this.tem_peca = tem;
        return;
    }
}
