package xadrez;

/**
 * Classe Responsavel pela gerencia do jogo, tabuleiro e jogadores.
 * @author Leonardo Valerio.
 */
public class Jogo {

    // Cria um tabuleiro e possui uma variavel turn que representa de quem eh a vez no momento,
    // comeca com brancos.
    private Tabuleiro tabuleiro = new Tabuleiro();
    private char turn = 'b';

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
}
