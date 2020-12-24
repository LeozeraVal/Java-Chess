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
        
        this.j1 = new Jogador("Kleber", 'b');
        for (int i = 0; i < 16; i++) {
            this.j1.adicionaPeca(pecas[i]);
        }

        this.j2 = new Jogador("Gervasio" , 'p');
        for (int i = 16; i < 32; i++) {
            this.j2.adicionaPeca(pecas[i]);
        }
        
        this.tabuleiro.populaNovoTabuleiro(pecas);
        this.turn = j1;

        this.j1.verPecas();
        this.j2.verPecas();
    }

    // Passa para o tabuleiro os argumentos do movimento, se o tabuleiro efetuar o movimento, passa a vez.
    // Se nao for, apenas comunica que ainda eh a vez do jogador.
    private void mover(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest, char cor) {
        Peca temp = null;
        if (this.tabuleiro.mover(linha_orig, coluna_orig, linha_dest, coluna_dest, cor, temp)) {
            // if (temp != null) {
            //     if (this.turn == j1) {
            //         this.j2.removePeca(temp);
            //     } else {
            //         this.j1.removePeca(temp);
            //     }
            // }
            this.passarVez();
        } else {
            System.out.println("Ainda eh sua vez");
        }
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
        while (true) {
            this.status();
            this.turn.verPecas();
            String str = ent.nextLine();
            if (str.equalsIgnoreCase("Desistir")) {
                System.out.println(this.turn.getNome() + " desitiu.");
                return;
            }
            mover(str.charAt(0) - 48, str.charAt(1), str.charAt(3) - 48, str.charAt(4), this.turn.getCor());  
        }
    }
}
