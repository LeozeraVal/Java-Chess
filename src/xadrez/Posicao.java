package xadrez;

public class Posicao {
    // possui uma cor, linha, coluna e no momento apenas uma booleana que simboliza se tem peca ou nao,
    // futuramente tera tambem uma peca.
    private char cor;
    private int linha;
    private char coluna;
    private boolean tem_peca;


    public Posicao() {
        this.cor = 'p';
        this.linha = 1;
        this.coluna = 'a';
        this.tem_peca = false;
    }


    public char getCor() {
        return this.cor;
    }

    // apenas checa a consistencia dos dados
    public void setCor(char cor) {
        if (cor != 'b' && cor != 'p') {
            System.out.println("> ERRO! " + cor + " nao eh uma cor valida!");
        } else {
            this.cor = cor;
        }
    }

    public int getLinha() {
        return this.linha;
    }

    // apenas checa a consistencia dos dados
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

    // apenas checa a consistencia dos dados
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
}
