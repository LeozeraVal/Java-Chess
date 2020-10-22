package xadrez;

public class Teste {
    public static void main(String[] args) throws Exception {
        System.out.println("Testes de representacao:");

        System.out.println("Teste de Tabuleiro:");
        Tabuleiro tabuleiro_test = new Tabuleiro();
        tabuleiro_test.printTabuleiro();
        System.out.println("############################");
        
        System.out.println("Teste de pecas pretas:");
        Torre torreP = new Torre('p');
        torreP.desenho();
        System.out.print(" - ");
        Bispo bispoP = new Bispo('p');
        bispoP.desenho();
        System.out.print(" - ");
        Cavalo cavaloP = new Cavalo('p');
        cavaloP.desenho();
        System.out.print(" - ");
        Peao peaoP = new Peao('p');
        peaoP.desenho();
        System.out.print(" - ");
        Dama damaP = new Dama('p');
        damaP.desenho();
        System.out.print(" - ");
        Rei reiP = new Rei('p');
        reiP.desenho();
        System.out.println("");
        System.out.println("############################");

        System.out.println("Teste de pecas brancas:");
        Torre torreB = new Torre('b');
        torreB.desenho();
        System.out.print(" - ");
        Bispo bispoB = new Bispo('b');
        bispoB.desenho();
        System.out.print(" - ");
        Cavalo cavaloB = new Cavalo('b');
        cavaloB.desenho();
        System.out.print(" - ");
        Peao peaoB = new Peao('b');
        peaoB.desenho();
        System.out.print(" - ");
        Dama damaB = new Dama('b');
        damaB.desenho();
        System.out.print(" - ");
        Rei reiB = new Rei('b');
        reiB.desenho();
        System.out.println("");
        System.out.println("############################");

        System.out.println("############################");

        System.out.println("Testes de criacao de posicao:");

        System.out.println("Testes criacao com linha invalida:");
        Posicao testpos = new Posicao('b', 136, 'f', false);
        testpos = new Posicao('b', 0, 'f', false);
        System.out.println("############################");

        System.out.println("Testes criacao com coluna invalida:");
        testpos = new Posicao('b', 5, 'z', false);
        testpos = new Posicao('b', 0, '-', false);
        System.out.println("############################");

        System.out.println("Testes criacao com cor invalida:");
        testpos = new Posicao('a', 5, 'f', false);
        testpos = new Posicao('c', 0, 'f', false);
        System.out.println("############################");

        System.out.println("Testes criacao valida:");
        testpos = new Posicao('b', 5, 'f', false);
        System.out.println(testpos);
        testpos = new Posicao('b', 1, 'g', false);
        System.out.println(testpos);
        System.out.println("############################");

        System.out.println("############################");
        
        System.out.println("Testes de movimentos no tabuleiro e status do tabuleiro:");
        Jogo jogoTeste = new Jogo();

        System.out.println("Teste linha origem invalida:");
        jogoTeste.mover(0, 'f', 5, 'g');
        jogoTeste.mover(10, 'f', 5, 'g');
        System.out.println("############################");

        System.out.println("Teste coluna origem invalida:");
        jogoTeste.mover(5, 'r', 5, 'g');
        jogoTeste.mover(5, 'B', 5, 'g');
        System.out.println("############################");

        System.out.println("Teste linha destino invalida:");
        jogoTeste.mover(5, 'f', 0, 'g');
        jogoTeste.mover(5, 'f', 10, 'g');
        System.out.println("############################");

        System.out.println("Teste coluna destino invalida:");
        jogoTeste.mover(5, 'f', 5, 'r');
        jogoTeste.mover(5, 'f', 5, 'A');
        System.out.println("############################");

        System.out.println("Teste origem = destino invalida:");
        jogoTeste.mover(5, 'f', 5, 'f');
        System.out.println("############################");

        System.out.println("Teste movimento valido:");
        jogoTeste.mover(5, 'f', 4, 'g');
        System.out.println("############################");


        System.out.println("Teste status do tabuleiro:");
        jogoTeste.status();
        jogoTeste.mover(4, 'f', 2, 'a');
        jogoTeste.status();
        System.out.println("############################");

        System.out.println("############################");

        System.out.println("Teste de pecas especificas:");
        Posicao origTeste = new Posicao('p', 4, 'd', false);
        Posicao destTeste = new Posicao('b', 3, 'b', false);
        
        System.out.println("Testes Peao:");
        System.out.println("Branco:");
        for (int linha = 7; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < 8; coluna++) {
                destTeste = new Posicao('b', linha+1, (char)(coluna+97), false);
                if (destTeste.getLinha() == origTeste.getLinha() && destTeste.getColuna() == origTeste.getColuna()) {
                    peaoB.desenho();
                    System.out.print(" ");
                } else if (peaoB.checaMovimento(origTeste, destTeste)) {
                    System.out.print('\u2713' + " ");
                } else {
                    System.out.print('\u2717' + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");

        System.out.println("Preto:");
        for (int linha = 7; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < 8; coluna++) {
                destTeste = new Posicao('b', linha+1, (char)(coluna+97), false);
                if (destTeste.getLinha() == origTeste.getLinha() && destTeste.getColuna() == origTeste.getColuna()) {
                    peaoP.desenho();
                    System.out.print(" ");
                } else if (peaoP.checaMovimento(origTeste, destTeste)) {
                    System.out.print('\u2713' + " ");
                } else {
                    System.out.print('\u2717' + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");

        System.out.println("############################");

        System.out.println("Teste Bispo:");

        for (int linha = 7; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < 8; coluna++) {
                destTeste = new Posicao('b', linha+1, (char)(coluna+97), false);
                if (destTeste.getLinha() == origTeste.getLinha() && destTeste.getColuna() == origTeste.getColuna()) {
                    bispoP.desenho();
                    System.out.print(" ");
                } else if (bispoP.checaMovimento(origTeste, destTeste)) {
                    System.out.print('\u2713' + " ");
                } else {
                    System.out.print('\u2717' + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");

        System.out.println("############################");

        System.out.println("Testes Cavalo:");

        for (int linha = 7; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < 8; coluna++) {
                destTeste = new Posicao('b', linha+1, (char)(coluna+97), false);
                if (destTeste.getLinha() == origTeste.getLinha() && destTeste.getColuna() == origTeste.getColuna()) {
                    cavaloP.desenho();
                    System.out.print(" ");
                } else if (cavaloP.checaMovimento(origTeste, destTeste)) {
                    System.out.print('\u2713' + " ");
                } else {
                    System.out.print('\u2717' + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");

        System.out.println("############################");

        System.out.println("Testes Torre:");

        for (int linha = 7; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < 8; coluna++) {
                destTeste = new Posicao('b', linha+1, (char)(coluna+97), false);
                if (destTeste.getLinha() == origTeste.getLinha() && destTeste.getColuna() == origTeste.getColuna()) {
                    torreP.desenho();
                    System.out.print(" ");
                } else if (torreP.checaMovimento(origTeste, destTeste)) {
                    System.out.print('\u2713' + " ");
                } else {
                    System.out.print('\u2717' + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");

        System.out.println("############################");

        System.out.println("Testes Rei:");

        for (int linha = 7; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < 8; coluna++) {
                destTeste = new Posicao('b', linha+1, (char)(coluna+97), false);
                if (destTeste.getLinha() == origTeste.getLinha() && destTeste.getColuna() == origTeste.getColuna()) {
                    reiP.desenho();
                    System.out.print(" ");
                } else if (reiP.checaMovimento(origTeste, destTeste)) {
                    System.out.print('\u2713' + " ");
                } else {
                    System.out.print('\u2717' + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");

        System.out.println("############################");

        System.out.println("Testes Dama:");

        for (int linha = 7; linha >= 0; linha--) {
            System.out.print(linha+1 + " -- ");
            for (int coluna = 0; coluna < 8; coluna++) {
                destTeste = new Posicao('b', linha+1, (char)(coluna+97), false);
                if (destTeste.getLinha() == origTeste.getLinha() && destTeste.getColuna() == origTeste.getColuna()) {
                    damaP.desenho();
                    System.out.print(" ");
                } else if (damaP.checaMovimento(origTeste, destTeste)) {
                    System.out.print('\u2713' + " ");
                } else {
                    System.out.print('\u2717' + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("     | | | | | | | |");
        System.out.println("     A B C D E F G H");

        System.out.println("############################");
    }
}
