package xadrez;

import java.util.Scanner;

/**
 * Classe Responsavel pela gerencia do jogo, tabuleiro e jogadores.
 * 
 * @author Leonardo Valerio.
 */
public class Jogo {

    // Possui um tabuleiro, um vetor de pecas , possui uma variavel turn que representa de quem eh a vez no momento,
    // e dois jogadores.
    private Tabuleiro tabuleiro;
    private Jogador turn;
    private Jogador j1;
    private Jogador j2;
    private Peca[] pecas;

    /**
     * O construtor da classe jogo eh responsavel por alocar todas as pecas, o tabuleiro, os jogadores e atribuir aos jogadores suas pecas.
     */
    public Jogo() {
        // Instancia um tabuleiro.
        this.tabuleiro = new Tabuleiro();
        // Instancia um vetor de Peca.
        this.pecas = new Peca[32];

        // Chama o metodo populaNovoJogo que cria todas as Pecas em uma ordem padrao no vetor pecas.
        this.populaNovoJogo(pecas);

        // Abre um Scanner
        Scanner ent = new Scanner(System.in);
        System.out.println("Bem Vindo ao Xadrez no terminal!");
        System.out.println("Digite o nome do Jogador 1, comandante das brancas.");
        // Instancia o jogador 1 com o nome inserido e a cor branca.
        this.j1 = new Jogador(ent.nextLine(), 'b');
        // Adiciona as pecas padroes da cor branca ao jogador 1.
        for (int i = 0; i < 16; i++) {
            this.j1.adicionaPeca(pecas[i]);
        }

        System.out.println("Digite o nome do Jogador 2, comandante das pretas.");
        // Instancia o jogador 2 com o nome inserido e a cor preta.
        this.j2 = new Jogador(ent.nextLine() , 'p');
        // Adiciona as pecas padroes da cor preta ao jogador 2.
        for (int i = 16; i < 32; i++) {
            this.j2.adicionaPeca(pecas[i]);
        }
        
        // Chama o metodo populaNovoTabuleiro() para inserir no tabuleiro do jeito padrao de inicio de jogo as pecas.
        this.tabuleiro.populaNovoTabuleiro(pecas);

        // Inicia o turno como turno do comandante das brancas j1.
        this.turn = j1;
    }

    /**
     * Passa para o tabuleiro os argumentos do movimento, se o tabuleiro efetuar o movimento, passa a vez.
     * Se nao for, apenas comunica que ainda eh a vez do jogador.
     * @param linha_orig Linha de origem do movimento.
     * @param coluna_orig Coluna de origem do movimento.
     * @param linha_dest Linha de destino do moivmento.
     * @param coluna_dest Coluna de destino do movimento.
     * @param cor Cor do jogador que esta efetuando o movimento.
     * @return true caso movimento foi efetuado e false caso contrario.
     */
    private boolean mover(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest, char cor) {
        // Cria uma Peca temporaria que armazena a referencia a peca no destino do movimento.
        Peca temp = this.tabuleiro.achaPeca(linha_dest, coluna_dest);

        // Se o movimento nao foi efetuado comunicamos ao jogador, voltamos temp a null e retornamos false.
        if (!this.tabuleiro.mover(linha_orig, coluna_orig, linha_dest, coluna_dest, cor)) {
            System.out.println("Ainda eh sua vez");
            temp = null;
            return false;
        }

        // Caso o movimento foi efetuado, precisamos remover a peca da lista de pecas do jogador alvo:
        // Se o jogador 1 nao possui a cor de origem do movimento a peca a ser removida eh dele.
        if (this.j1.getCor() != cor) {
            this.j1.removePeca(temp);
        } else {
            // Caso contrario, j2 possui a cor alvo e portanto removemos dele.
            this.j2.removePeca(temp);
        }
        // Caso nao exista Peca no destino, temp possuira null e o metodo removePeca apenas nao remove Peca do
        // jogador.
        
        // Retornamos true pro movimento.
        return true;
    }

    /**
     * Apresenta o tabuleiro e diz de quem eh a vez no momento.
     */
    private void status() {
        this.tabuleiro.printTabuleiro();
        if (this.turn == this.j1) {
            System.out.println("Esta na vez de " + this.j1.getNome() + " comandante das brancas.");
        } else {
            System.out.println("Esta na vez de " + this.j2.getNome() + " comandante das pretas.");
        }
    }

    
    /**
     * // Funcao auxiliar para passar a vez ao proximo jogador.
     */
    private void passarVez() {
        if (this.turn == this.j1) {
            this.turn = this.j2;
        } else {
            this.turn = this.j1;
        }
    }

    /**
     * Metodo que ira popular o vetor de pecas com as Pecas de modo padrao de inicio de jogo,
     * @param pecas Vetor que sera populado.
     */
    private void populaNovoJogo(Peca[] pecas) {
        // Primeiro criamos as Pecas que estao na primeira linha, as especificas brancas.
        pecas[0] = new Torre('b');
        pecas[1] = new Cavalo('b');
        pecas[2] = new Bispo('b');
        pecas[3] = new Dama('b');
        pecas[4] = new Rei('b');
        pecas[5] = new Bispo('b');
        pecas[6] = new Cavalo('b');
        pecas[7] = new Torre('b');

        // Entao fazemos um loop para criar os Peoes brancos da segunda linha.
        for (int i = 8; i < 16; i++) {
            pecas[i] = new Peao('b');
        }

        // Portanto, ate aqui temos que o vetor pecas de 0 ate 15 possui todas as pecas brancas.

        // Depois criamos as Pecas especificas pretas, da linha 8.
        pecas[16] = new Torre('p');
        pecas[17] = new Cavalo('p');
        pecas[18] = new Bispo('p');
        pecas[19] = new Dama('p');
        pecas[20] = new Rei('p');
        pecas[21] = new Bispo('p');
        pecas[22] = new Cavalo('p');
        pecas[23] = new Torre('p');

        // E logo depois os peoes pretos da linha 7.
        for (int i = 24; i < 32; i++) {
            pecas[i] = new Peao('p');
        }

        // Ao final o vetor pecas possui de 16 a 31 todas as pecas pretas.

        // O vetor pecas possui todas as pecas do jogo.
    }

    //DEBUG
    public Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    /**
     * Este eh o loop do Jogo nele ocorrerao todas as chamadas de metodos auxiliares para efetuar a dinamica do xadrez.
     */
    public void loopJogo() {
        // Primeiro criamos um Scanner.
        Scanner ent = new Scanner(System.in);
        // Utilizaremos esta flag fim para simbolizar o fim do jogo.
        boolean fim = false;

        // Enquanto o jogo nao chegou ao fim.
        while (!fim) {
            // Chama o metodo status() para ver o tabuleiro e de quem eh a vez.
            this.status();

            // Chama o metodo verPecas() do jogador da vez para ver quais sao suas pecas.
            this.turn.verPecas();

            // Espera uma entrada.
            String str = ent.nextLine();

            // Se a entrada for "Desistir", anuncia a desistencia do jogador da vez e sai do loop.
            if (str.equalsIgnoreCase("Desistir")) {
                System.out.println(this.turn.getNome() + " desitiu.");
                break;
            }

            // Se a entrada for "empate", a proxima entrada do teclado sera a resposta do oponente para a proposta de empate.
            if (str.equalsIgnoreCase("empate")) {
                System.out.println(this.turn.getNome() + " deseja terminara partida em empate, aceita? (Digite Sim ou Nao)");
                str = ent.nextLine();
                // Se sim, anunciamos o empate e saimos do loop.
                if (str.equalsIgnoreCase("Sim")) {
                    System.out.println("Empate aceito. O jogo acaba em Empate.");
                    break;
                } else {
                    // Se nao retornamos ao comeco do loop ainda na vez de quem pediu o empate.
                    continue;
                }
            }
            // Entao, caso nao seja uma desistencia ou empate, lemos os comandos inseridos no terminal e tentamos efetuar o movimento.
            if(mover(str.charAt(0) - 48, str.charAt(1), str.charAt(3) - 48, str.charAt(4), this.turn.getCor())) {
                // Caso o movimento foi efetuado, vemos se colocou o inimigo em cheque.
                if (this.turn == j1) {
                    if (this.tabuleiro.cheque('p')) {
                        // Se sim, checamos se foi cheque mate
                        if (this.tabuleiro.chequeMate('b')) {
                            // Caso o cheque mate tenha sido efetuado, anunciamos a vitoria do jogador e colocamos a flag fim em true.
                            System.out.println("Cheque Mate! O jogador " + this.j1.getNome() + " ganha por cheque mate!");
                            fim = true;
                        } else {
                            // Se foi apenas cheque anunciamos que o jogador colocu seu inimigo em cheque.
                            System.out.println("O jogador " + this.j1.getNome() + " colocou " + this.j2.getNome() + " em cheque.");
                        }
                    }
                } else {
                    // Caso identido ao superior porem para o jogador 2.
                    if (this.tabuleiro.cheque('b')) {
                        if (this.tabuleiro.chequeMate('p')) {
                            System.out.println("Cheque Mate! O jogador " + this.j2.getNome() + " ganha por cheque mate!");
                            fim = true;
                        } else {
                            System.out.println("O jogador " + this.j2.getNome() + " colocou " + this.j1.getNome() + " em cheque.");
                        }
                    }
                }
                // Ao final do movimento passamos a vez.
                this.passarVez();
            }
        }
        // Caso o jogo tenha chego ao fim, printamos o tabuleiro uma ultima vez.
        this.tabuleiro.printTabuleiro();
        // Agradecemos por jogar e esperamos o ENTER para sair do metodo.
        System.out.println("Obrigado por jogar! Aperte ENTER para sair.");
        ent.nextLine();
        ent.close();
        return;
    }
}
