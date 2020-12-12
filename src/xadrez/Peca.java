package xadrez;

/**
 * Classe abstrata que servira de interface para todas as pecas.
 * @author Leonardo Valerio Morales
 */
public abstract class Peca {

    /**
     * Atributo que armazena a cor da Peca.
     */
    private char cor;

    /**
     * Construtor da Peca, sera utilizado por todas as pecas.
     * @param cor Indica qual a cor da Peca a ser criada.
     */
    public Peca(char cor) {
        if (cor == 'p' || cor == 'b') {
            this.cor = cor;
        } else {
            System.out.println("Uma peca tem que ser preta ou branca.");
        }
    }
    
    public char getCor() {
        return this.cor;
    }

    /**
     * Metodo responsavel por desenhar a peca na tela.
     */
    public abstract void desenho();

    /**
     * Este metodo valida ou desvalida um movimento com a peca especifica.
     * @param linha_orig Linha da posicao de origem.
     * @param coluna_orig Coluna da posicao de origem.
     * @param linha_dest Linha da posicao de destino.
     * @param coluna_dest Coluna da posicao de destino.
     * @return true caso o movimento seja valido no ambito da peca, false caso contrario.
     */
    public abstract boolean checaMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest);
}