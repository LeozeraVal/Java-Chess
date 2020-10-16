package xadrez;

public class Tabuleiro {
    // Cria um vetor bidimencional para guardar as posicoes.
    private Posicao[][] posicoes = new Posicao[8][8];
    
    // Construtor itera pelo vetor de posicoes atribuindo a coluna, linha e a cor de cada posicao.
    public Tabuleiro() {
        for (int linha = 0; linha < this.posicoes.length; linha++) {
            for (int coluna = 0; coluna < this.posicoes[linha].length; coluna++) {
                if ((linha + coluna) % 2 == 0) {
                    this.posicoes[linha][coluna] = new Posicao('p', linha+1, (char)(coluna+97), false);
                } else {
                    this.posicoes[linha][coluna] = new Posicao('b', linha+1, (char)(coluna+97), false);
                }
            }
        }
    }

    // Checa o destino e origem em todas as dimensoes para encontrar inconsistencias,
    // se nao encontrou nenhuma, o movimento sera efetuado.
    public boolean mover(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
        if (coluna_orig > 'h' || coluna_orig < 'a') {
            System.out.println("Movimento Invalido, origem fora do tabuleiro.");
            return false;
        }
        if (linha_orig > 8 || linha_orig < 1) {
            System.out.println("Movimento Invalido, origem fora do tabuleiro.");
            return false;
        }
        if (coluna_dest > 'h' || coluna_dest < 'a') {
            System.out.println("Movimento Invalido, destino fora do tabuleiro.");
            return false;
        }
        if (linha_dest > 8 || linha_dest < 1) {
            System.out.println("Movimento Invalido, destino fora do tabuleiro.");
            return false;
        }
        if (linha_dest == linha_orig && coluna_dest == coluna_orig) {
            System.out.println("Movimento Invalido, destino igual a origem.");
            return false;
        }
        System.out.println("Movimento efetuado");
        return true;

        // Aqui seria a verificacao da peca na posicao de origem se pode ir pra posicao destino
        // contudo, no momento nao programamos a classe Peca que incorpora as pecas.
        //posicoes[linha_orig-1][coluna_orig-97].Peca.checkmove(linha_dest, coluna_dest);

        // Apos verificar com se eh um movimento valido para aquela peca, checariamos se o caminho esta livre.
        
    }

    // uma funcao para representar na tela o tabuleiro, ele parte da ultima linha do vetor bidimencional
    // e desce ate a primeira, assim na tela do usuario ele visualiza o tabuleiro do jeito normal em perspectiva
    // das pecas brancas. Futuramente tera uma checagem se a posicao possui peca, e se possuir desenhar a mesma.
    public void printTabuleiro() {
        for (int linha = this.posicoes.length-1; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < this.posicoes[linha].length; coluna++) {
                // Aqui uso Unicode pra printar quadrados brancos ou pretos
                if (this.posicoes[linha][coluna].getCor() == 'b') {
                    System.out.print('\u25A1' + " ");
                } else {
                    System.out.print('\u25A0' + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");
    }
}
