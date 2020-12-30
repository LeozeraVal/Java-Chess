package xadrez;

/**
 * Classe Tabulerio que possui um conjunto de posicoes e eh responsavel por checar seus limites e efetuar movimentos e checagens dentro do mesmo.
 * @author Leonardo Valerio
 */
public class Tabuleiro {
    // Cria um vetor bidimencional para guardar as posicoes.
    private Posicao[][] posicoes;
    
    // Construtor que inicializa e itera pelo vetor de posicoes atribuindo a coluna, linha e a cor de cada posicao.
    public Tabuleiro() {
        posicoes = new Posicao[8][8];
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

    /**
     * Metodo responsavel por popular um tabuleiro com um vetor de pecas, neste caso o metodo
     * assume que o tabuleiro eh novo e portanto que o vetor pecas tera uma ordem especifica correspondente
     * a ordem de inicio do jogo.
     * @param pecas Vetor que possui as pecas previamente inicializadas.
     */
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

    /**
     * Metodo chamado para mover uma peca no tabuleiro, efetua chamada de diversos metodos que checam a validade do mesmo
     * antes de efetuar o movimento.
     * @param linha_orig Linha de origem do movimento
     * @param coluna_orig Coluna de origem do movimento
     * @param linha_dest Linha de destino do moivmento
     * @param coluna_dest Coluna de destino do movimento
     * @param cor Cor do jogador que esta efetuando o movimento
     * @return 1 = Caso movimento valido.
     * @return
     */
    public boolean mover(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest, char cor) {

        // chamamos a funcao checaMovimentoGlobal que engloba todas as checagens de movimento desde os limites do tabuleiro,
        // obstrucoes e movimentacao especifica da peca e guardamos seu retorno em uma variavel.
        int erro = checaMovimentoGlobal(linha_orig, coluna_orig, linha_dest, coluna_dest, cor);
        
        // Logo depois tratamos este retorno printando na tela o erro, ou se nao houve erro continuamos o codigo.
        switch (erro) {
            case 1:
                break;

            case -1:
                System.out.println("Movimento Invalido, Destino igual Origem ou fora do tabuleiro.");
                return false;
                
            case -2:
                System.out.println("Nao existe uma Peca na origem deste movimento.");
                return false;

            case -3:
                System.out.println("Voce nao pode mover uma peca que nao eh sua.");
                return false;
            
            case -4:
                System.out.println("Esta Peca nao faz esse tipo de movimento.");
                return false;

            case -5:
                System.out.println("O caminho deste movimento esta obstruido.");
                return false;
            
            case -6:
                System.out.println("A peca de Destino tem a mesma cor que a Peca de Origem");
                return false;

            default:
                break;
        }

        // Caso a posicao de destino tenha uma Peca, ela sera temporariamente comida.
        Peca temp = null;
        temp = this.posicoes[linha_dest-1][coluna_dest-97].removePeca();

        // Enfim, colocamos a Peca que esta presenta na origem no destino e simultaneamente retiramos da origem.
        this.posicoes[linha_dest-1][coluna_dest-97].colocaPeca(this.posicoes[linha_orig-1][coluna_orig-97].removePeca());

        // Entao checamos se o movimento colocou o rei aliado em cheque
        if(cheque(cor)) {
            // Se sim, revertemos o movimento
            this.posicoes[linha_orig-1][coluna_orig-97].colocaPeca(this.posicoes[linha_dest-1][coluna_dest-97].removePeca());
            // E se existia uma peca na posicao de destino a colocamos de volta
            this.posicoes[linha_dest-1][coluna_dest-97].colocaPeca(temp);
            // Comunicamos o erro e retornamos false.
            System.out.println("Este movimento coloca seu Rei em cheque, portanto eh invalido");
            return false;
        }
        // Se chegou ate aqui o movimento foi efetuado e validado, retornamos true.
        return true;        
    }

    /**
     * Metodo que efetua a grande maioria das checagens de validade de um movimento.
     * @param linha_orig Linha de origem do movimento
     * @param coluna_orig Coluna de origem do movimento
     * @param linha_dest Linha de destino do moivmento
     * @param coluna_dest Coluna de destino do movimento
     * @param cor Cor do jogador que esta efetuando o movimento
     * @return Codigo de Retornos:
     * 1 = Movimento valido;
     * -1 = Movimento fora do limite do Tabuleiro;
     * -2 = Movimento sem Peca na origem;
     * -3 = Movimento de uma Peca de cor diferente do jogador;
     * -4 = Movimento invalido pela peca;
     * -5 = Caminho do movimento obstruido;
     * -6 = Posicao de destino possui peca da mesma cor que origem;
     */
    private int checaMovimentoGlobal(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest, char cor) {
        // Primeiro chama o metodo que checa os limites deste movimento no tabuleiro. Caso
        // O metodo retorne false, retornamos erro tambem pois nao eh um movimento valido.
        if(!this.checaLimiteMovimento(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
            return -1;
        }

        // Segundamente checa se existe Peca na origem.
        if (!this.posicoes[linha_orig-1][coluna_orig-97].temPeca()) {
            return -2;
        }

        // Terceiramente checa a Peca da origem eh da cor do jogador que comandou o movimento
        if (!(this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getCor() == cor)) {
            
            return -3;
        }

        // Terceiramente checa se o movimento eh valido pela Peca.
        if (!this.posicoes[linha_orig-1][coluna_orig-97].getPeca().checaMovimento(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
            return -4;
        }

        // Depois chama o metodo que checa o caminho deste movimento e se tem alguma Peca o obstruindo.
        // Nao fazemos esta checagem para o Cavalo pq ele pula mt bem.
        if (!this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getClass().getName().equals("xadrez.Cavalo")) {
            if (!this.checaCaminhoMovimento(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
                return -5;
            }
        }

        // Existe um caso especial para o Peao, pois ele so pode se mover na diagonal caso exista uma Peca para ele comer
        // e so pode se mover para frente caso nao existe uma peca no seu destino.
        // Por ja ter checado que o movimento eh valido pela peca, sabemos que o Peao ou esta se movendo uma casa nas suas diagonais ou
        // para frente
        if (this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getClass().getName().equals("xadrez.Peao")) {
            
            // Caso o movimento seja frontal e no seu destino tenha uma Peca, retorna erro, pois o Peao nao come para frente.
            if (coluna_dest == coluna_orig && this.posicoes[linha_dest-1][coluna_dest-97].temPeca()) {
                return -4;
            }

            // Caso o movimento seja diagonal e no seu destino nao tenha uma Peca, retorna erro, pois o Peao so anda
            // na diagonal comendo uma Peca.
            if (coluna_dest != coluna_orig && (!this.posicoes[linha_dest-1][coluna_dest-97].temPeca())) {
                return -4;
            }
        }

        // Caso a Peca presente na posicao de destino tem a mesma cor que a Peca na posicao de origem retornamos erro.
        if (this.posicoes[linha_dest-1][coluna_dest-97].temPeca()) {
            if (this.posicoes[linha_dest-1][coluna_dest-97].getPeca().getCor() == this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getCor()) {
                return -6;
            }
        }

        // Caso nao tenha retornado ate agora, o movimento eh considerado valido.
        return 1;
    }

    /**
     * Metodo auxiliar que checa o destino e origem do movimento em todas as dimensoes para encontrar inconsistencias
     * @param linha_orig Linha de origem do movimento
     * @param coluna_orig Coluna de origem do movimento
     * @param linha_dest Linha de destino do moivmento
     * @param coluna_dest Coluna de destino do movimento
     * @return True se o movimento esta no tabuleiro e false se nao esta.
     */
    private boolean checaLimiteMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
        if (coluna_orig > 'h' || coluna_orig < 'a') {
            return false;
        }
        if (linha_orig > 8 || linha_orig < 1) {
            return false;
        }
        if (coluna_dest > 'h' || coluna_dest < 'a') {
            return false;
        }
        if (linha_dest > 8 || linha_dest < 1) {
            return false;
        }
        if (linha_dest == linha_orig && coluna_dest == coluna_orig) {
            return false;
        }
        return true;        
    }

    /**
     * Metodo auxiliar que checa se o caminho do movimento esta obstruido por outra Peca.
     * @param linha_orig Linha de origem do movimento
     * @param coluna_orig Coluna de origem do movimento
     * @param linha_dest Linha de destino do moivmento
     * @param coluna_dest Coluna de destino do movimento
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
                    // Simultaneamente opera a variavel linha e coluna para checar apenas a direcao diagonal.
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
                    // Simultaneamente opera a variavel linha e coluna para checar apenas a direcao diagonal.
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
                    // Simultaneamente opera a variavel linha e coluna para checar apenas a direcao diagonal.
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
                    // Simultaneamente opera a variavel linha e coluna para checar apenas a direcao diagonal.
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

    /**
     * Metodo que sera chamado para checar se um time especifico esta em cheque dada a situacao atual do tabuleiro.
     * @param cor_rei Cor do rei do time a ser analisado. Ou seja o rei alvo do cheque.
     * @return true caso rei esteja em cheque e false caso contrario.
     */
    public boolean cheque(char cor_rei) {
        // Primeiro acha o rei da cor_rei
        Posicao pos_rei = achaRei(cor_rei);
        // Descontruimos a posicao do rei nas variaveis linha_rei e coluna _rei.
        int linha_rei = pos_rei.getLinha();
        char coluna_rei = pos_rei.getColuna();

        // Para agilizar, guardamos a cor do time atacante ao rei na variavel cor_atacante.
        char cor_atacante;
        if (cor_rei == 'b') {
            cor_atacante = 'p';
        } else {
            cor_atacante = 'b';
        }

        // Para cada posicao do Tabuleiro, se esta posicao possui uma peca da cor_atacante, checamos se um movimento
        // ate o rei inimigo seria valido usando a funcao checaMovimentoGlobal(). Caso sim, entao o rei esta em cheque e retornamos true.
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if (this.posicoes[linha][coluna].temPeca()) {
                    if (this.posicoes[linha][coluna].getPeca().getCor() == cor_atacante) {
                        if (checaMovimentoGlobal(linha+1, (char) (coluna + 97), linha_rei, coluna_rei, cor_atacante) == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        // Se chegou ate aqui, nenhuma peca da cor_atacante consegue chegar ao rei da cor_rei, portanto o mesmo nao esta em cheque.
        return false;
    }

    /**
     * Metodo que sera chamado para checar se um time especifico esta em cheque dada a situacao atual do tabuleiro.
     * @param linha_rei Linha onde se localiza o Rei do time atacado.
     * @param coluna_rei Coluna onde se localiza o Rei do time atacado.
     * @param cor_rei Cor do rei atacado.
     * @return true caso o rei esteja em cheque e false caso contrario.
     */
    public boolean cheque(int linha_rei, char coluna_rei, char cor_rei) {
        // Para agilizar, guardamos a cor do time atacante ao rei na variavel cor_atacante.
        char cor_atacante;
        if (cor_rei == 'b') {
            cor_atacante = 'p';
        } else {
            cor_atacante = 'b';
        }

        // Para cada posicao do Tabuleiro, se esta posicao possui uma peca da cor_atacante, checamos se um movimento
        // ate o rei inimigo seria valido usando a funcao checaMovimentoGlobal(). Caso sim, entao o rei esta em cheque e retornamos true.
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if (this.posicoes[linha][coluna].temPeca()) {
                    if (this.posicoes[linha][coluna].getPeca().getCor() == cor_atacante) {
                        if (checaMovimentoGlobal(linha+1, (char) (coluna + 97), linha_rei, coluna_rei, cor_atacante) == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        // Se chegou ate aqui, nenhuma peca da cor_atacante consegue chegar ao rei da cor_rei, portanto o mesmo nao esta em cheque.
        return false;
    }

    /**
     * Metodo que ao ser chamado, verifica se o time da cor_atacante efetuou um cheque_mate.
     * @param cor_atacante Cor do time que esta atacando.
     * @return true caso seja um cheque mate e false caso contrario.
     */
    public boolean chequeMate(char cor_atacante) {
        // para agilizar, armazenamos a cor do rei inimigo em uma variavel.
        char cor_rei_inimigo;
        if (cor_atacante == 'b') {
            cor_rei_inimigo = 'p';
        } else {
            cor_rei_inimigo = 'b';
        }

        // Utiliza o metodo achaRei() para encontrar o rei inimigo no tabuleiro e logo depois decompoe suas coordenadas nas variaveis:
        // linha_rei_atual e coluna_rei_atual.
        Posicao pos_rei = this.achaRei(cor_rei_inimigo);
        int linha_rei_atual = pos_rei.getLinha();
        char coluna_rei_atual = pos_rei.getColuna();

        // Utilizamos esta flag para podermos armazenar quando achamos uma saida do cheque.
        boolean saida = false;

        // Para cada movimento possivel do Rei, ou seja, desde sua linha -1 e coluna -1 ate sua linha +1 e coluna +1: (Por isso linha e coluna_rei_atual)
        for (int linha_rei_temp = linha_rei_atual-1; linha_rei_temp <= linha_rei_atual+1; linha_rei_temp++) {
            for (int coluna_rei_temp = coluna_rei_atual-1; coluna_rei_temp <= coluna_rei_atual+1; coluna_rei_temp++) {
                // Efetua um movimento temporario do Rei para esta posicao, caso seja valido.
                if (!(checaMovimentoGlobal(linha_rei_atual, coluna_rei_atual, linha_rei_temp, (char) coluna_rei_temp, cor_rei_inimigo) == 1)) {
                    // Se o movimento nao eh valido, continuamos para o proximo movimento possivel do Rei.
                    continue;
                }
                // Para efetuar o movimento temporario precisamos guardar a peca de destino, pois seria hipoteticamente
                // comida pelo rei, por isso a variavel Peca temp.
                Peca temp = posicoes[linha_rei_temp-1][coluna_rei_temp-97].removePeca();
                // Movemos o rei pra casa de destino hipotetico
                posicoes[linha_rei_temp-1][coluna_rei_temp-97].colocaPeca(posicoes[linha_rei_atual-1][coluna_rei_atual-97].removePeca());
                // Rodamos a checagem de cheque naquela posicao, se retornar false, temos uma saida do cheque, portanto atribuimos a
                // booleana saida true.
                if (!cheque(linha_rei_temp, (char) coluna_rei_temp, cor_rei_inimigo)) {
                    saida = true;
                }
                // Revertemos o movimento temporario que efetuamos para o rei e a peca que foi hipoteticamente comida.
                posicoes[linha_rei_atual-1][coluna_rei_atual-97].colocaPeca(posicoes[linha_rei_temp-1][coluna_rei_temp-97].removePeca());
                posicoes[linha_rei_temp-1][coluna_rei_temp-97].colocaPeca(temp);
                // Apos reverter o movimento, se a variavel saida for true, retornamos false para o cheque mate, pois ha uma saida do
                // cheque.
                if (saida) {
                    return false;    
                }
            }
        }

        // Agora efetuamos a checagem extensiva de todos os movimentos possiveis das pecas da cor do rei em cheque.
        
        // Para cada posicao do tabuleiro.
        for (int linha_atual = 0; linha_atual < 8; linha_atual++) {
            for (int coluna_atual = 0; coluna_atual < 8; coluna_atual++) {
                // Se a peca nesta posicao existe e possui a mesma cor do rei 
                if (posicoes[linha_atual][coluna_atual].temPeca() && posicoes[linha_atual][coluna_atual].getPeca().getCor() == cor_rei_inimigo) {
                    // Caso for o proprio rei pulamos, (ja checamos todas as possibilidades de movimento do mesmo)
                    if (posicoes[linha_atual][coluna_atual].getPeca().getClass().getName().equals("xadrez.Rei")) {
                        continue;
                    }

                    // Checamos todos os movimentos possiveis da peca (Para todas as posicoes do tabuleiro),
                    // fazemos um movimento temporario e checamos se ainda existe o cheque.
                    for (int linha_temp = 0; linha_temp < 8; linha_temp++) {
                        for (int coluna_temp = 0; coluna_temp < 8; coluna_temp++) {
                            // Caso o movimento desta peca ate o destino seja possivel.
                            if (checaMovimentoGlobal(linha_atual+1, (char) (coluna_atual+97), linha_temp+1, (char) (coluna_temp+97), cor_rei_inimigo) == 1) {
                                // Efetuamos um movimento temporario ate o destino e checamos se o cheque permanece.
                                Peca temp = posicoes[linha_temp][coluna_temp].removePeca();
                                posicoes[linha_temp][coluna_temp].colocaPeca(posicoes[linha_atual][coluna_atual].removePeca());

                                // Caso o rei nao esteja mais em cheque, atribuimos a variavel saida true.
                                if (!cheque(linha_rei_atual, (char) coluna_rei_atual, cor_rei_inimigo)) {
                                    saida = true;

                                }
                                // Desfazemos o movimento temporario.
                                posicoes[linha_atual][coluna_atual].colocaPeca(posicoes[linha_temp][coluna_temp].removePeca());
                                posicoes[linha_temp][coluna_temp].colocaPeca(temp);

                                // Apos reverter o movimento, se a variavel saida for true, retornamos false para o cheque mate, pois ha uma saida do
                                // cheque.
                                if (saida) {
                                   return false; 
                                }
                            }
                        }
                    }
                }
            }
        }

        // Se chegou aqui, testamos todos os movimentos possiveis e nenhum retirou o rei de cheque, portanto retornamos verdadeiro para cheque mate.
        return true;
    }

    /**
     * Metodo auxiliar que apenas percorre o tabuleiro percorrendo a posicao do Rei da cor alvo e retorna a mesma.
     * @param cor cor do Rei cuja posicao eh desejada
     * @return Referencia a Posicao do rei ou null caso o rei nao exista (Caso absurdo).
     */
    private Posicao achaRei(char cor) {
        // Para cada Posicao do tabuleiro:
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                // Se existe uma Peca e a Peca possui a Cor alvo:
                if (this.posicoes[linha][coluna].temPeca()) {
                    if (this.posicoes[linha][coluna].getPeca().getCor() == cor) {
                        // Checamos o nome da classe em tempo de execucao da Peca, se for igual a "xadrez.Rei" achamos o rei que procuramos.
                        if(this.posicoes[linha][coluna].getPeca().getClass().getName().equals("xadrez.Rei")) {
                            return this.posicoes[linha][coluna];
                        }
                    }
                }
            }
        }
        // Nunca chegara aqui
        return null;
    }

    /**
     * Um metodo para representar na tela o tabuleiro, ele parte da ultima linha do vetor bidimencional
     * e desce ate a primeira, assim na tela do usuario ele visualiza o tabuleiro do jeito normal em perspectiva
     * das pecas brancas.
     */
    public void printTabuleiro() {
        // Imprime os indices das colunas.
        System.out.println("     a b c d e f g h");
        System.out.println("     | | | | | | | |");
        // Para cada linha, partindo de cima:
        for (int linha = this.posicoes.length-1; linha >= 0; linha--) {
            // Escreva o numero da linha.
            System.out.print(linha+1 + " -- ");
            // Para cada coluna partindo da esquerda :
            for (int coluna = 0; coluna < this.posicoes[linha].length; coluna++) {
                // Se nao possui Peca:
                if (!this.posicoes[linha][coluna].temPeca()) {
                    // Imprime o caracter em Unicode correspondente a cor da Posicao.
                    if (this.posicoes[linha][coluna].getCor() == 'b') {
                        System.out.print('\u25A1' + " ");
                    } else {
                        System.out.print('\u25A0' + " ");
                    }
                } else {
                    // Se possui Peca apenas chama o metodo desenho() da mesma.
                    this.posicoes[linha][coluna].getPeca().desenho();
                    System.out.print(" ");
                }
            }
            // Imprime novamente o numero da linha.
            System.out.println(" -- " + (linha+1));
        }
        // Imprime novamente os indices das colunas.
        System.out.println("     | | | | | | | |");
        System.out.println("     a b c d e f g h");
    }
}
