package xadrez;

/**
 * Classe Tabulerio que possui um conjunto de posicoes e eh responsavel por checar seus limites.
 * @author Leonardo Valerio
 */
public class Tabuleiro {
    // Cria um vetor bidimencional para guardar as posicoes.
    private Posicao[][] posicoes = new Posicao[8][8];
    
    // Construtor itera pelo vetor de posicoes atribuindo a coluna, linha e a cor de cada posicao.
    public Tabuleiro() {
        for (int linha = 0; linha < this.posicoes.length; linha++) {
            for (int coluna = 0; coluna < this.posicoes[linha].length; coluna++) {
                if ((linha + coluna) % 2 == 0) {
                    this.posicoes[linha][coluna] = new Posicao('p', linha+1, (char)(coluna+97));
                } else {
                    this.posicoes[linha][coluna] = new Posicao('b', linha+1, (char)(coluna+97));
                }
            }
        }
    }

    // Metodo responsavel por popular um tabuleiro com um vetor de pecas, neste caso o metodo
    // assume que o tabuleiro eh novo e portanto que o vetor pecas tera uma ordem especifica correspondente
    // a ordem de inicio do jogo.
    public void populaNovoTabuleiro(Peca[] pecas) {
        posicoes[0][0].colocaPeca(pecas[0]);
        posicoes[0][1].colocaPeca(pecas[1]);
        posicoes[0][2].colocaPeca(pecas[2]);
        posicoes[0][3].colocaPeca(pecas[3]);
        posicoes[0][4].colocaPeca(pecas[4]);
        posicoes[0][5].colocaPeca(pecas[5]);
        posicoes[0][6].colocaPeca(pecas[6]);
        posicoes[0][7].colocaPeca(pecas[7]);
        
        int i = 0;
        int j = 8;
        while (i < 8 && j < 16) {
            posicoes[1][i].colocaPeca(pecas[j]);
            i++;
            j++;
        }

        posicoes[7][0].colocaPeca(pecas[16]);
        posicoes[7][1].colocaPeca(pecas[17]);
        posicoes[7][2].colocaPeca(pecas[18]);
        posicoes[7][3].colocaPeca(pecas[19]);
        posicoes[7][4].colocaPeca(pecas[20]);
        posicoes[7][5].colocaPeca(pecas[21]);
        posicoes[7][6].colocaPeca(pecas[22]);
        posicoes[7][7].colocaPeca(pecas[23]);

        i = 0;
        j = 24;
        while (i < 8 && j < 32) {
            posicoes[6][i].colocaPeca(pecas[j]);
            i++;
            j++;
        }
        
    }

    // Checa o destino e origem em todas as dimensoes para encontrar inconsistencias,
    // se nao encontrou nenhuma, o movimento sera efetuado.
    public boolean mover(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest, char cor, Peca temp) {

        // Primeiro chama o metodo que checa os limites deste movimento no tabuleiro. Caso
        // O metodo retorne false, retornamos false tambem pois nao eh um movimento valido.
        if(!this.checaLimiteMovimento(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
            return false;
        }

        // Segundamente checa se existe Peca na origem.
        if (!this.posicoes[linha_orig-1][coluna_orig-97].temPeca()) {
            System.out.println("Nao existe uma Peca na origem deste movimento");
            return false;
        }

        // Terceiramente checa a Peca da origem eh da cor do jogador que comandou o movimento
        if (!(this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getCor() == cor)) {
            System.out.println("Voce nao pode mover uma peca que nao eh sua.");
            return false;
        }

        // Terceiramente checa se o movimento eh valido pela Peca.
        if (!this.posicoes[linha_orig-1][coluna_orig-97].getPeca().checaMovimento(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
            System.out.println("Esta Peca nao faz esse tipo de movimento.");
            return false;
        }

        // Depois chama o metodo que checa o caminho deste movimento e se tem alguma Peca o obstruindo.
        // Nao fazemos esta checagem para o Cavalo pq ele pula mt bem.
        if (!this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getClass().getName().equals("Cavalo")) {
            if (!this.checaCaminhoMovimento(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
                System.out.println("O caminho deste movimento esta obstruido.");
                return false;
            }
        }

        // Existe um caso especial para o Peao, pois ele so pode se mover na diagonal caso exista uma Peca para ele comer
        // e so pode se mover para frente caso nao existe uma peca no seu destino.
        // Por ja ter checado que o movimento eh valido pela peca, sabemos que o Peao ou esta se movendo uma casa nas suas diagonais ou
        // para frente
        if (this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getClass().getName().equals("Peao")) {
            
            // Caso o movimento seja frontal e no seu destino tenha uma Peca, retorna false, pois o Peao nao come para frente.
            if (coluna_dest == coluna_orig && this.posicoes[linha_dest-1][coluna_dest-97].temPeca()) {
                return false;
            }

            // Caso o movimento seja diagonal e no seu destino nao tenha uma Peca, retorna false, pois o Peao so anda
            // na diagonal comendo uma Peca.
            if (coluna_dest != coluna_orig && (!this.posicoes[linha_dest-1][coluna_dest-97].temPeca())) {
                return false;
            }
        }

        // Enfim, como o movimento esta nos limites do tabuleiro, eh valido pela Peca e seu caminho esta livre,
        // basta apenas checar se a Peca no destino do movimento eh da mesma cor que a Peca de origem.

        // Caso a posicao de destino tenha uma Peca, ela sera comida ou se for uma Peca
        // da mesma cor que a origem retornamos false.
        if (this.posicoes[linha_dest-1][coluna_dest-97].temPeca()) {
            if (this.posicoes[linha_dest-1][coluna_dest-97].getPeca().getCor() == this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getCor()) {
                System.out.println("A peca de Destino tem a mesma cor que a Peca de Origem");
                return false;
            }
            temp = this.posicoes[linha_dest-1][coluna_dest-97].removePeca();
        }

        // Enfim, colocamos a Peca que esta presenta na origem no destino e simultaneamente retiramos da origem.
        this.posicoes[linha_dest-1][coluna_dest-97].colocaPeca(this.posicoes[linha_orig-1][coluna_orig-97].removePeca());

        return true;        
    }

    /**
     * Metodo auxiliar que checa o destino e origem do movimento em todas as dimensoes para encontrar inconsistencias
     * @param linha_orig
     * @param coluna_orig
     * @param linha_dest
     * @param coluna_dest
     * @return True se o movimento esta no tabuleiro e false se nao esta.
     */
    private boolean checaLimiteMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
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
        return true;        
    }

    /**
     * Metodo auxiliar que checa se o caminho do movimento esta obstruido por outra Peca.
     * @param linha_orig
     * @param coluna_orig
     * @param linha_dest
     * @param coluna_dest
     * @return True se caminho esta livre e false se nao esta.
     */
    private boolean checaCaminhoMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {

        // Primeiramente convertemos as coordenadas relativos do tabuleiro para absoluta na matriz.
        // Assim facilitamos o entendimento do algoritmo.
        linha_orig--;
        linha_dest--;
        coluna_orig = (char) (coluna_orig - 97);
        coluna_dest = (char) (coluna_dest - 97);


        // Movimento horizontal
        if (linha_orig == linha_dest) {
            // Movimento para a "Direita"
            if (coluna_orig < coluna_dest) {
                // Precisamos checar se existem pecas uma coluna depois da origem ate uma coluna antes do destino
                for (int coluna = coluna_orig+1; coluna < coluna_dest; coluna++) {
                    if (this.posicoes[linha_orig][coluna].temPeca()) {
                        return false;
                    }
                }
                return true;
            }

            // Movimento para a "Esquerda"
            if (coluna_orig > coluna_dest) {
                // Precisamos checar se existem pecas uma coluna antes da origem ate uma coluna depois do destino
                for (int coluna = coluna_orig-1; coluna > coluna_dest; coluna--) {
                    if (this.posicoes[linha_orig][coluna].temPeca()) {
                        return false;
                    }
                }
                return true;
            }
        }

        // Movimento vertical
        if (coluna_orig == coluna_dest) {
            // Movimento para "Cima"
            if (linha_orig < linha_dest) {
                // Precisamos checar se existem pecas uma linha acima da origem ate uma linha abaixo do destino
                for (int linha = linha_orig+1; linha < linha_dest; linha++) {
                    if (this.posicoes[linha][coluna_orig].temPeca()) {
                        return false;
                    }
                }
                return true;
            }

            // Movimento para a "Baixo"
            if (linha_orig > linha_dest) {
                // Precisamos checar se existem pecas uma linha abaixo da origem ate uma linha acima do destino
                for (int linha = linha_orig-1; linha > linha_dest; linha--) {
                    if (this.posicoes[linha][coluna_orig].temPeca()) {
                        return false;
                    }
                }
                return true;
            }
        }
        
        // Movimentacao Diagonal
        if (linha_orig != linha_dest && coluna_orig != coluna_dest) {

            //Movimento Diagonal Superior
            if (linha_orig < linha_dest) {

                // Movimento Diagonal Superior Direito
                if (coluna_orig < coluna_dest) {
                    int linha = linha_orig+1;
                    int coluna = coluna_orig+1;

                    while (linha < linha_dest && coluna < coluna_dest) {
                        
                        if (this.posicoes[linha][coluna].temPeca()) {
                            return false;
                        }

                        linha++;
                        coluna++;
                    }
                    return true;
                }

                // Movimento Diagonal Superior Esquerdo
                if (coluna_orig > coluna_dest) {
                    int linha = linha_orig+1;
                    int coluna = coluna_orig-1;
    
                    while (linha < linha_dest && coluna > coluna_dest) {
                        
                        if (this.posicoes[linha][coluna].temPeca()) {
                            return false;
                        }
    
                        linha++;
                        coluna--;
                    }
                    return true;
                }
            }

            //Movimento Diagonal Inferior
            if (linha_orig > linha_dest) {

                // Movimento Diagonal Inferior Direito
                if (coluna_orig < coluna_dest) {
                    int linha = linha_orig-1;
                    int coluna = coluna_orig+1;

                    while (linha > linha_dest && coluna < coluna_dest) {
                        
                        if (this.posicoes[linha][coluna].temPeca()) {
                            return false;
                        }

                        linha--;
                        coluna++;
                    }
                    return true;
                }
                
                // Movimento Diagonal Superior Esquerdo
                if (coluna_orig > coluna_dest) {
                    int linha = linha_orig-1;
                    int coluna = coluna_orig-1;
    
                    while (linha > linha_dest && coluna > coluna_dest) {
                        
                        if (this.posicoes[linha][coluna].temPeca()) {
                            return false;
                        }
    
                        linha--;
                        coluna--;
                    }
                    return true;
                }
            }
        }

        // Caso nao se configure em nenhum desses tipos de movimento retorna true, pois teoricamente
        // o caminho entre um movimento que tem como destino a sua propria origem nao tem caminho obstruido.
        return true;
    }

    // uma funcao para representar na tela o tabuleiro, ele parte da ultima linha do vetor bidimencional
    // e desce ate a primeira, assim na tela do usuario ele visualiza o tabuleiro do jeito normal em perspectiva
    // das pecas brancas. Futuramente tera uma checagem se a posicao possui peca, e se possuir desenhar a mesma.
    public void printTabuleiro() {
        for (int linha = this.posicoes.length-1; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < this.posicoes[linha].length; coluna++) {
                if (!this.posicoes[linha][coluna].temPeca()) {
                    if (this.posicoes[linha][coluna].getCor() == 'b') {
                        System.out.print('\u25A1' + " ");
                    } else {
                        System.out.print('\u25A0' + " ");
                    }
                } else {
                    this.posicoes[linha][coluna].getPeca().desenho();
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");
    }
}
