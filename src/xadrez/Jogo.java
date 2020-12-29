package xadrez;

import java.util.Scanner;

/**
 * Classe Responsavel pela gerencia do jogo, tabuleiro e jogadores.
 * 
 * @author Leonardo Valerio.
 */
public class Jogo {

    // Possui um tabuleiro, um vetor de pecas e possui uma variavel turn que representa de quem eh a vez no momento,
    // comeca com brancos.
    private Tabuleiro tabuleiro;
    private Jogador turn;
    private Jogador j1;
    private Jogador j2;
    private Peca[] pecas;

    // O construtor da classe jogo sera responsavel por alocar todas as pecas, o tabuleiro, os jogadores e atribuir aos jogadores suas pecas.
    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.pecas = new Peca[32];
        this.populaNovoJogo(pecas);
        Scanner ent = new Scanner(System.in);
        System.out.println("Digite o nome do Jogador 1, comandante das brancas.");
        this.j1 = new Jogador(ent.nextLine(), 'b');
        for (int i = 0; i < 16; i++) {
            this.j1.adicionaPeca(pecas[i]);
        }

        System.out.println("Digite o nome do Jogador 2, comandante das pretas.");
        this.j2 = new Jogador(ent.nextLine() , 'p');
        for (int i = 16; i < 32; i++) {
            this.j2.adicionaPeca(pecas[i]);
        }
        
        this.tabuleiro.populaNovoTabuleiro(pecas);
        this.turn = j1;
    }

    // Passa para o tabuleiro os argumentos do movimento, se o tabuleiro efetuar o movimento, passa a vez.
    // Se nao for, apenas comunica que ainda eh a vez do jogador.
    private boolean mover(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest, char cor) {
        if (!this.tabuleiro.mover(linha_orig, coluna_orig, linha_dest, coluna_dest, cor)) {
            System.out.println("Ainda eh sua vez");
            return false;
        }
        return true;
    }

    // Apresenta o tabuleiro e diz de quem eh a vez no momento
    private void status() {
        this.tabuleiro.printTabuleiro();
        if (this.turn == this.j1) {
            System.out.println("Esta na vez de " + this.j1.getNome() + " comandante das brancas.");
        } else {
            System.out.println("Esta na vez de " + this.j2.getNome() + " comandante das pretas.");
        }
    }

    // Funcao privada para passar a vez.
    private void passarVez() {
        System.out.println("Passando a vez.");
        if (this.turn == this.j1) {
            this.turn = this.j2;
        } else {
            this.turn = this.j1;
        }
    }

    private void populaNovoJogo(Peca[] pecas) {
        pecas[0] = new Torre('b');
        pecas[1] = new Cavalo('b');
        pecas[2] = new Bispo('b');
        pecas[3] = new Dama('b');
        pecas[4] = new Rei('b');
        pecas[5] = new Bispo('b');
        pecas[6] = new Cavalo('b');
        pecas[7] = new Torre('b');

        for (int i = 8; i < 16; i++) {
            pecas[i] = new Peao('b');
        }

        pecas[16] = new Torre('p');
        pecas[17] = new Cavalo('p');
        pecas[18] = new Bispo('p');
        pecas[19] = new Dama('p');
        pecas[20] = new Rei('p');
        pecas[21] = new Bispo('p');
        pecas[22] = new Cavalo('p');
        pecas[23] = new Torre('p');

        for (int i = 24; i < 32; i++) {
            pecas[i] = new Peao('p');
        }
    }

    //DEBUG
    public Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    public void loopJogo() {
        Scanner ent = new Scanner(System.in);
        boolean fim = false;
        while (!fim) {
            
            this.status();
            this.turn.verPecas();
            String str = ent.nextLine();
            if (str.equalsIgnoreCase("Desistir")) {
                System.out.println(this.turn.getNome() + " desitiu.");
                return;
            }
            if(mover(str.charAt(0) - 48, str.charAt(1), str.charAt(3) - 48, str.charAt(4), this.turn.getCor())) {
                if (this.turn == j1) {
                    if (this.tabuleiro.cheque('p')) {
                        if (this.tabuleiro.chequeMate('b')) {
                            System.out.println("Cheque Mate! O jogador " + this.j1.getNome() + " ganha por cheque mate!");
                            fim = true;
                        } else {
                            System.out.println("O jogador " + this.j1.getNome() + " colocou " + this.j2.getNome() + " em cheque.");
                        }
                    }
                } else {
                    if (this.tabuleiro.cheque('b')) {
                        if (this.tabuleiro.chequeMate('p')) {
                            System.out.println("Cheque Mate! O jogador " + this.j2.getNome() + " ganha por cheque mate!");
                            fim = true;
                        } else {
                            System.out.println("O jogador " + this.j2.getNome() + " colocou " + this.j1.getNome() + " em cheque.");
                        }
                    }
                }
                this.passarVez();
            }
        }
        this.tabuleiro.printTabuleiro();
        System.out.println("Obrigado por jogar! Aperte qualquer tecla para sair.");
        ent.nextLine();
        ent.close();
        return;
    }
}
