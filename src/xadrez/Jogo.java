package xadrez;


/**
 * Classe Responsavel pela gerencia do jogo, tabuleiro e jogadores.
 * 
 * @author Leonardo Valerio.
 */
public class Jogo {

    // Possui um tabuleiro, um vetor de pecas e possui uma variavel turn que representa de quem eh a vez no momento,
    // comeca com brancos.
    private Tabuleiro tabuleiro;
    private char turn = 'b';
    private Peca[] pecas;

    // O construtor da classe jogo sera responsavel por alocar todas as pecas, o tabuleiro, os jogadores e atribuir aos jogadores suas pecas.
    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.pecas = new Peca[32];
        this.populaNovoJogo(pecas);
        
        Jogador j1 = new Jogador("Kleber", 'b');
        for (int i = 0; i < 16; i++) {
            j1.adicionaPeca(pecas[i]);
        }

        Jogador j2 = new Jogador("Gervasio" , 'p');
        for (int i = 16; i < 32; i++) {
            j2.adicionaPeca(pecas[i]);
        }
        
        this.tabuleiro.populaNovoTabuleiro(pecas);

        j1.verPecas();
        j2.verPecas();
    }

    // Passa para o tabuleiro os argumentos do movimento, se o tabuleiro efetuar o movimento, passa a vez.
    // Se nao for, apenas comunica que ainda eh a vez do jogador.
    public void mover(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
        if (this.tabuleiro.mover(linha_orig, coluna_orig, linha_dest, coluna_dest)) {
            this.passarVez();
        } else {
            System.out.println("Ainda eh sua vez");
        }
    }

    // Apresenta o tabuleiro e diz de quem eh a vez no momento
    public void status() {
        this.tabuleiro.printTabuleiro();
        if (this.getTurn() == 'b') {
            System.out.println("Esta na vez das brancas.");
        } else {
            System.out.println("Esta na vez das pretas.");
        }
    }

    // Funcao privada para passar a vez.
    private void passarVez() {
        System.out.println("Passando a vez.");
        if (this.turn == 'b') {
            this.turn = 'p';
        } else {
            this.turn = 'b';
        }
    }

    // Pode ser usado pelo gerenciador
    public char getTurn() {
        return this.turn;
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
}
