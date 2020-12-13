package xadrez;

/**
 * Classe que compoe o tabuleiro, eh responsavel por saber sua cor, linha, coluna, se tem uma peca e qual peca eh.
 * @author Leonardo Valerio
 */
public class Posicao {
    
    /**
     * Cor da Posicao.
     */
    private char cor;

    /**
     * Linha da Posicao.
     */
    private int linha;

    /**
     * Coluna da Posicao.
     */
    private char coluna;

    /**
     * Booleana responsavel por dizer se a posicao tem uma Peca.
     */
    private boolean tem_peca;

    /**
     * Atributo que aponta para um objeto Peca. Simboliza qual Peca ocupa esta Posicao.
     */
    private Peca peca;


    /**
     * Construtor com Peca de uma Posicao.
     * @param cor (char) Cor da Posicao.
     * @param linha (int) Linha da Posicao.
     * @param coluna (char) Coluna da Posicao.
     * @param peca (Peca) Representa qual Peca ocupa esta Posicao.
     */
    public Posicao(char cor, int linha, char coluna, Peca peca) {
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

        // Ja que chamamos o construtor com uma peca, populamos os atributos tem_peca e peca.
        this.tem_peca = true;
        this.peca = peca;
    }

    /**
     * Construtor sem Peca de uma Posicao.
     * @param cor (char) Cor da Posicao.
     * @param linha (int) Linha da Posicao.
     * @param coluna (char) Coluna da Posicao.
     */
    public Posicao(char cor, int linha, char coluna) {
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

        // Ja que chamamos o construtor com uma peca, populamos os atributos tem_peca e peca.
        this.tem_peca = false;
    }


    public char getCor() {
        return this.cor;
    }

    public int getLinha() {
        return this.linha;
    }

    public char getColuna() {
        return this.coluna;
    }

    public boolean temPeca() {
        return this.tem_peca;
    }

    public Peca getPeca() {
        return this.peca;
    }
    
    /**
     * Usado quando uma peca for colocada nesta posicao
     * @param peca Peca a ser colocada nesta posicao.
     */
    public void colocaPeca(Peca peca) {
        this.tem_peca = true;
        this.peca = peca;
        return;
    }

    /**
     * Usado quando uma peca for retirada desta Posicao, retorna a peca que tirou.
     * @return Objeto Peca que foi retirado da Posicao, ou nulo caso a Posicao nao tenha Peca.
     */
    public Peca removePeca() {
        if (this.tem_peca) {
            // Setamos tem_peca como false e o atributo peca passa a apontar para nulo.
            // Alem disso criamos uma variavel temp para retornar a Peca que estava nesta Posicao.
            this.tem_peca = false;
            Peca temp = this.peca;
            this.peca = null;
            return temp;
        } else {
            System.out.println("Esta posicao nao possui Peca para ser removida.");
            return null;
        }
    }
}
