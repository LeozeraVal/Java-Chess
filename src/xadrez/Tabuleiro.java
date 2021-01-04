package xadrez;

import java.util.LinkedList;

/**
 * Classe Tabulerio que possui um conjunto de posicoes e eh responsavel por checar seus limites e efetuar movimentos e checagens dentro do mesmo.
 * @author Leonardo Valerio
 */
public class Tabuleiro {
    // Cria um vetor bidimencional para guardar as posicoes.
    private Posicao[][] posicoes;
    
    /**
     * Construtor que inicializa e itera pelo vetor de posicoes atribuindo a coluna, linha e a cor de cada posicao.
     */
    public Tabuleiro() {
        this.posicoes = new Posicao[8][8];
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
    public void populaNovoTabuleiro(LinkedList<Peca> pecas) {
        this.posicoes[0][0].colocaPeca(pecas.get(0));
        this.posicoes[0][1].colocaPeca(pecas.get(1));
        this.posicoes[0][2].colocaPeca(pecas.get(2));
        this.posicoes[0][3].colocaPeca(pecas.get(3));
        this.posicoes[0][4].colocaPeca(pecas.get(4));
        this.posicoes[0][5].colocaPeca(pecas.get(5));
        this.posicoes[0][6].colocaPeca(pecas.get(6));
        this.posicoes[0][7].colocaPeca(pecas.get(7));
        
        int i = 0;
        int j = 8;
        while (i < 8 && j < 16) {
            this.posicoes[1][i].colocaPeca(pecas.get(j));
            i++;
            j++;
        }

        this.posicoes[7][0].colocaPeca(pecas.get(16));
        this.posicoes[7][1].colocaPeca(pecas.get(17));
        this.posicoes[7][2].colocaPeca(pecas.get(18));
        this.posicoes[7][3].colocaPeca(pecas.get(19));
        this.posicoes[7][4].colocaPeca(pecas.get(20));
        this.posicoes[7][5].colocaPeca(pecas.get(21));
        this.posicoes[7][6].colocaPeca(pecas.get(22));
        this.posicoes[7][7].colocaPeca(pecas.get(23));

        i = 0;
        j = 24;
        while (i < 8 && j < 32) {
            this.posicoes[6][i].colocaPeca(pecas.get(j));
            i++;
            j++;
        }
        
    }

    /**
     * Metodo chamado para mover uma peca no tabuleiro, efetua chamada de diversos metodos que checam a validade do mesmo
     * antes de efetuar o movimento.
     * @param linha_orig Linha de origem do movimento.
     * @param coluna_orig Coluna de origem do movimento.
     * @param linha_dest Linha de destino do moivmento.
     * @param coluna_dest Coluna de destino do movimento.
     * @param cor Cor do jogador que esta efetuando o movimento.
     * @return true caso movimento tenha sido efetuado, false caso contrario.
     * @return
     */
    public boolean mover(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest, char cor) {

        // chamamos a funcao checaMovimentoGlobal que engloba todas as checagens de movimento desde os limites do tabuleiro,
        // obstrucoes e movimentacao especifica da peca e guardamos seu retorno em uma variavel.
        int erro = this.checaMovimentoGlobal(linha_orig, coluna_orig, linha_dest, coluna_dest, cor);
        
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

        // Entao checamos se o movimento colocou o rei aliado em xeque:
        if(this.xeque(cor)) {
            // Se sim, revertemos o movimento.
            this.posicoes[linha_orig-1][coluna_orig-97].colocaPeca(this.posicoes[linha_dest-1][coluna_dest-97].removePeca());
            // E se existia uma peca na posicao de destino a colocamos de volta.
            this.posicoes[linha_dest-1][coluna_dest-97].colocaPeca(temp);
            // Comunicamos o erro e retornamos false.
            System.out.println("Este movimento coloca seu Rei em xeque, portanto eh invalido.");
            return false;
        }
        // Se chegou ate aqui o movimento foi efetuado e validado, retornamos true.
        return true;        
    }

    /**
     * Metodo que efetua a grande maioria das checagens de validade de um movimento.
     * @param linha_orig Linha de origem do movimento.
     * @param coluna_orig Coluna de origem do movimento.
     * @param linha_dest Linha de destino do moivmento.
     * @param coluna_dest Coluna de destino do movimento.
     * @param cor Cor do jogador que esta efetuando o movimento.
     * @return Codigo de Retornos:
     * 1 = Movimento valido;
     * -1 = Movimento fora do limite do Tabuleiro;
     * -2 = Movimento sem Peca na origem;
     * -3 = Movimento de uma Peca de cor diferente do jogador;
     * -4 = Movimento invalido pela peca;
     * -5 = Caminho do movimento obstruido;
     * -6 = Posicao de destino possui peca da mesma cor que origem.
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

        // Terceiramente checa a Peca da origem eh da cor do jogador que comandou o movimento.
        if (!(this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getCor() == cor)) {
            
            return -3;
        }

        // Terceiramente checa se o movimento eh valido pela Peca.
        if (!this.posicoes[linha_orig-1][coluna_orig-97].getPeca().checaMovimento(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
            return -4;
        }

        // Depois chama o metodo que checa o caminho deste movimento e se tem alguma Peca o obstruindo.
        // Nao fazemos esta checagem para o Cavalo pq ele pula mt bem.
        if (!this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getClass().getSimpleName().equals("Cavalo")) {
            if (!this.checaCaminhoMovimento(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
                return -5;
            }
        }

        // Existe um caso especial para o Peao, pois ele so pode se mover na diagonal caso exista uma Peca para ele comer
        // e so pode se mover para frente caso nao existe uma peca no seu destino.
        // Por ja ter checado que o movimento eh valido pela peca, sabemos que o Peao ou esta se movendo uma casa nas suas diagonais ou
        // para frente.
        if (this.posicoes[linha_orig-1][coluna_orig-97].getPeca().getClass().getSimpleName().equals("Peao")) {
            
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
     * Metodo auxiliar que checa o destino e origem do movimento em todas as dimensoes para encontrar inconsistencias.
     * @param linha_orig Linha de origem do movimento.
     * @param coluna_orig Coluna de origem do movimento.
     * @param linha_dest Linha de destino do moivmento.
     * @param coluna_dest Coluna de destino do movimento.
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
     * @param linha_orig Linha de origem do movimento.
     * @param coluna_orig Coluna de origem do movimento.
     * @param linha_dest Linha de destino do moivmento.
     * @param coluna_dest Coluna de destino do movimento.
     * @return True se caminho esta livre e false se nao esta.
     */
    private boolean checaCaminhoMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {

        // Primeiramente convertemos as coordenadas relativos do tabuleiro para absoluta na matriz.
        // Assim facilitamos o entendimento do algoritmo.
        linha_orig--;
        linha_dest--;
        coluna_orig = (char) (coluna_orig - 97);
        coluna_dest = (char) (coluna_dest - 97);


        // Movimento horizontal.
        if (linha_orig == linha_dest) {
            // Movimento para a "Direita".
            if (coluna_orig < coluna_dest) {
                // Precisamos checar se existem pecas uma coluna depois da origem ate uma coluna antes do destino.
                for (int coluna = coluna_orig+1; coluna < coluna_dest; coluna++) {
                    if (this.posicoes[linha_orig][coluna].temPeca()) {
                        return false;
                    }
                }
                return true;
            }

            // Movimento para a "Esquerda".
            if (coluna_orig > coluna_dest) {
                // Precisamos checar se existem pecas uma coluna antes da origem ate uma coluna depois do destino.
                for (int coluna = coluna_orig-1; coluna > coluna_dest; coluna--) {
                    if (this.posicoes[linha_orig][coluna].temPeca()) {
                        return false;
                    }
                }
                return true;
            }
        }

        // Movimento vertical.
        if (coluna_orig == coluna_dest) {
            // Movimento para "Cima".
            if (linha_orig < linha_dest) {
                // Precisamos checar se existem pecas uma linha acima da origem ate uma linha abaixo do destino.
                for (int linha = linha_orig+1; linha < linha_dest; linha++) {
                    if (this.posicoes[linha][coluna_orig].temPeca()) {
                        return false;
                    }
                }
                return true;
            }

            // Movimento para a "Baixo".
            if (linha_orig > linha_dest) {
                // Precisamos checar se existem pecas uma linha abaixo da origem ate uma linha acima do destino.
                for (int linha = linha_orig-1; linha > linha_dest; linha--) {
                    if (this.posicoes[linha][coluna_orig].temPeca()) {
                        return false;
                    }
                }
                return true;
            }
        }
        
        // Movimentacao Diagonal.
        if (linha_orig != linha_dest && coluna_orig != coluna_dest) {

            //Movimento Diagonal Superior.
            if (linha_orig < linha_dest) {

                // Movimento Diagonal Superior Direito.
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

                // Movimento Diagonal Superior Esquerdo.
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

            //Movimento Diagonal Inferior.
            if (linha_orig > linha_dest) {

                // Movimento Diagonal Inferior Direito.
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
                
                // Movimento Diagonal Superior Esquerdo.
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
     * Metodo que sera chamado para checar se um time especifico esta em xeque dada a situacao atual do tabuleiro.
     * @param cor_rei Cor do rei do time a ser analisado. Ou seja o rei alvo do xeque.
     * @return true caso rei esteja em xeque e false caso contrario.
     */
    public boolean xeque(char cor_rei) {
        // Primeiro acha o rei da cor_rei.
        Posicao pos_rei = this.achaRei(cor_rei);
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
        // ate o rei inimigo seria valido usando a funcao checaMovimentoGlobal(). Caso sim, entao o rei esta em xeque e retornamos true.
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if (this.posicoes[linha][coluna].temPeca()) {
                    if (this.posicoes[linha][coluna].getPeca().getCor() == cor_atacante) {
                        if (this.checaMovimentoGlobal(linha+1, (char) (coluna + 97), linha_rei, coluna_rei, cor_atacante) == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        // Se chegou ate aqui, nenhuma peca da cor_atacante consegue chegar ao rei da cor_rei, portanto o mesmo nao esta em xeque.
        return false;
    }

    /**
     * Metodo que sera chamado para checar se um time especifico esta em xeque dada a situacao atual do tabuleiro.
     * @param linha_rei Linha onde se localiza o Rei do time atacado.
     * @param coluna_rei Coluna onde se localiza o Rei do time atacado.
     * @param cor_rei Cor do rei atacado.
     * @return true caso o rei esteja em xeque e false caso contrario.
     */
    public boolean xeque(int linha_rei, char coluna_rei, char cor_rei) {
        // Para agilizar, guardamos a cor do time atacante ao rei na variavel cor_atacante.
        char cor_atacante;
        if (cor_rei == 'b') {
            cor_atacante = 'p';
        } else {
            cor_atacante = 'b';
        }

        // Para cada posicao do Tabuleiro, se esta posicao possui uma peca da cor_atacante, checamos se um movimento
        // ate o rei inimigo seria valido usando a funcao checaMovimentoGlobal(). Caso sim, entao o rei esta em xeque e retornamos true.
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if (this.posicoes[linha][coluna].temPeca()) {
                    if (this.posicoes[linha][coluna].getPeca().getCor() == cor_atacante) {
                        if (this.checaMovimentoGlobal(linha+1, (char) (coluna + 97), linha_rei, coluna_rei, cor_atacante) == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        // Se chegou ate aqui, nenhuma peca da cor_atacante consegue chegar ao rei da cor_rei, portanto o mesmo nao esta em xeque.
        return false;
    }

    /**
     * Metodo que ao ser chamado, verifica se o time da cor_atacante efetuou um cheque_mate.
     * @param cor_atacante Cor do time que esta atacando.
     * @return true caso seja um xeque mate e false caso contrario.
     */
    public boolean xequeMate(char cor_atacante) {
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

        // Utilizamos esta flag para podermos armazenar quando achamos uma saida do xeque.
        boolean saida = false;

        // Para cada movimento possivel do Rei, ou seja, desde sua linha -1 e coluna -1 ate sua linha +1 e coluna +1: (Por isso linha e coluna_rei_atual)
        for (int linha_rei_temp = linha_rei_atual-1; linha_rei_temp <= linha_rei_atual+1; linha_rei_temp++) {
            for (int coluna_rei_temp = coluna_rei_atual-1; coluna_rei_temp <= coluna_rei_atual+1; coluna_rei_temp++) {
                // Efetua um movimento temporario do Rei para esta posicao, caso seja valido.
                if (!(this.checaMovimentoGlobal(linha_rei_atual, coluna_rei_atual, linha_rei_temp, (char) coluna_rei_temp, cor_rei_inimigo) == 1)) {
                    // Se o movimento nao eh valido, continuamos para o proximo movimento possivel do Rei.
                    continue;
                }
                // Para efetuar o movimento temporario precisamos guardar a peca de destino, pois seria hipoteticamente
                // comida pelo rei, por isso a variavel Peca temp.
                Peca temp = this.posicoes[linha_rei_temp-1][coluna_rei_temp-97].removePeca();
                // Movemos o rei pra casa de destino hipotetico.
                this.posicoes[linha_rei_temp-1][coluna_rei_temp-97].colocaPeca(this.posicoes[linha_rei_atual-1][coluna_rei_atual-97].removePeca());
                // Rodamos a checagem de xeque naquela posicao, se retornar false, temos uma saida do xeque, portanto atribuimos a
                // booleana saida true.
                if (!this.xeque(linha_rei_temp, (char) coluna_rei_temp, cor_rei_inimigo)) {
                    saida = true;
                }
                // Revertemos o movimento temporario que efetuamos para o rei e a peca que foi hipoteticamente comida.
                this.posicoes[linha_rei_atual-1][coluna_rei_atual-97].colocaPeca(this.posicoes[linha_rei_temp-1][coluna_rei_temp-97].removePeca());
                this.posicoes[linha_rei_temp-1][coluna_rei_temp-97].colocaPeca(temp);
                // Apos reverter o movimento, se a variavel saida for true, retornamos false para o xeque mate, pois ha uma saida do
                // xeque.
                if (saida) {
                    return false;    
                }
            }
        }

        // Agora efetuamos a checagem extensiva de todos os movimentos possiveis das pecas da cor do rei em xeque.
        
        // Para cada posicao do tabuleiro.
        for (int linha_atual = 0; linha_atual < 8; linha_atual++) {
            for (int coluna_atual = 0; coluna_atual < 8; coluna_atual++) {
                // Se a peca nesta posicao existe e possui a mesma cor do rei :
                if (this.posicoes[linha_atual][coluna_atual].temPeca() && this.posicoes[linha_atual][coluna_atual].getPeca().getCor() == cor_rei_inimigo) {
                    // Caso for o proprio rei pulamos. (ja checamos todas as possibilidades de movimento do mesmo)
                    if (this.posicoes[linha_atual][coluna_atual].getPeca().getClass().getSimpleName().equals("Rei")) {
                        continue;
                    }

                    // Checamos todos os movimentos possiveis da peca (Para todas as posicoes do tabuleiro),
                    // fazemos um movimento temporario e checamos se ainda existe o xeque.
                    for (int linha_temp = 0; linha_temp < 8; linha_temp++) {
                        for (int coluna_temp = 0; coluna_temp < 8; coluna_temp++) {
                            // Caso o movimento desta peca ate o destino seja possivel.
                            if (this.checaMovimentoGlobal(linha_atual+1, (char) (coluna_atual+97), linha_temp+1, (char) (coluna_temp+97), cor_rei_inimigo) == 1) {
                                // Efetuamos um movimento temporario ate o destino e checamos se o xeque permanece.
                                Peca temp = posicoes[linha_temp][coluna_temp].removePeca();
                                this.posicoes[linha_temp][coluna_temp].colocaPeca(this.posicoes[linha_atual][coluna_atual].removePeca());

                                // Caso o rei nao esteja mais em xeque, atribuimos a variavel saida true.
                                if (!this.xeque(linha_rei_atual, (char) coluna_rei_atual, cor_rei_inimigo)) {
                                    saida = true;

                                }
                                // Desfazemos o movimento temporario.
                                this.posicoes[linha_atual][coluna_atual].colocaPeca(this.posicoes[linha_temp][coluna_temp].removePeca());
                                this.posicoes[linha_temp][coluna_temp].colocaPeca(temp);

                                // Apos reverter o movimento, se a variavel saida for true, retornamos false para o xeque mate, pois ha uma saida do
                                // xeque.
                                if (saida) {
                                   return false; 
                                }
                            }
                        }
                    }
                }
            }
        }

        // Se chegou aqui, testamos todos os movimentos possiveis e nenhum retirou o rei de xeque, portanto retornamos verdadeiro para xeque mate.
        return true;
    }

    /**
     * Metodo auxiliar que apenas percorre o tabuleiro percorrendo a posicao do Rei da cor alvo e retorna a mesma.
     * @param cor cor do Rei cuja posicao eh desejada.
     * @return Referencia a Posicao do rei ou null caso o rei nao exista (Caso absurdo).
     */
    private Posicao achaRei(char cor) {
        // Para cada Posicao do tabuleiro:
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                // Se existe uma Peca e a Peca possui a Cor alvo:
                if (this.posicoes[linha][coluna].temPeca()) {
                    if (this.posicoes[linha][coluna].getPeca().getCor() == cor) {
                        // Checamos o nome da classe em tempo de execucao da Peca, se for igual a "Rei" achamos o rei que procuramos.
                        if(this.posicoes[linha][coluna].getPeca().getClass().getSimpleName().equals("Rei")) {
                            return this.posicoes[linha][coluna];
                        }
                    }
                }
            }
        }
        // Nunca chegara aqui.
        return null;
    }

    /**
     * Metodo utilizado para obter a Peca da posicao especificada, O Jogo utilizara para saber qual peca foi
     * comida em um movimento e comunicar ao jogador.
     * @param linha Linha da Peca desejada.
     * @param coluna Coluna da Peca desejada.
     * @return null se o movimento for invalido ou se nao existe peca no destino, caso contrario uma referencia ao objeto Peca.
     */
    public Peca achaPeca(int linha, char coluna) {
        // Validamos ambas linha e coluna.
        if (1 > linha || linha > 8) {
            return null;
        }
        if ('a' > coluna || coluna > 'h') {
            return null;
        }

        // Retornamos a Peca. 
        return this.posicoes[linha-1][coluna-97].getPeca();
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

    /**
     * Metodo que transforma o estado atual do tabuleiro em uma String e a retorna.
     * @return O tabuleiro em formato de String, primeiro uma linha com a quantidade de pecas ativas no momento,
     * depois uma linha para cada peca dizendo sua posicao, sua classe e sua cor.
     */
	public String stringTabuleiro() {
        // Declaramos a String final a ser retornada.
        String tabuleiro;
        // Declaramos a variavel que ira conter a quantidade de Pecas ativas;
        int qtd_pecas = 0;
        // Declaramos e inicializamos uma string que ira conter o estado do tabuleiro.
        String pecas = "";

        // Para cada Posicao do tabuleiro.
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                // Se esta Posicao possui uma Peca:
                if (this.posicoes[linha][coluna].temPeca()) {
                    // Aumenta a variavel pecas em 1.
                    qtd_pecas++;
                    // Guarda o nome da Peca em uma String usando o metodo getSimpleName();
                    String nome_peca = this.posicoes[linha][coluna].getPeca().getClass().getSimpleName();
                    // Guarda a cor da Peca em outra String.
                    char cor_peca = this.posicoes[linha][coluna].getPeca().getCor();
                    // Finalmente, combinamos todas as informacoes em uma String coord, que possui uma quebra de linha, o nome da Peca,
                    // sua cor, sua linha e sua coluna.
                    String coord = new String("\n" + (char)(coluna+97) + (linha+1) + " " + cor_peca + " " + nome_peca);
                    // Utilizamos a funcao ineretnte do atribuidor de String que efetua uma concatenacao das duas Strings.
                    // Neste caso, no primeiro momento a String estara vazia e recebera a primeira coordenada,
                    // nos proximos casos as coordenadas serao adicionadas a lista de coordenadas ja presente.
                    pecas = pecas + coord;
                }
            }
        }
        // Atribuimos a string final a juncao da qtd de pecas mais suas coordenadas e a retornamos.
        tabuleiro = new String(qtd_pecas + pecas);
        return tabuleiro;
	}

    /**
     * Metodo chamado pelo Jogo para popular este tabuleiro com uma matriz de Pecas em suas posicoes equivalente do tabuleiro
     * @param pecas Matriz de Pecas. Uma peca na linha 0 coluna 0 no tabuleiro estaria na posicao [0][0] desta matriz.
     */
	public void populaVelhoTabuleiro(Peca[][] pecas) {
        // Para cada coluna de cada linha colocar a Peca correspondente presente na Matriz.
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                this.posicoes[linha][coluna].colocaPeca(pecas[linha][coluna]);
            }
        }
	}
}
