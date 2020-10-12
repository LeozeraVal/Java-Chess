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
        Rainha rainhaP = new Rainha('p');
        rainhaP.desenho();
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
        Rainha rainhaB = new Rainha('b');
        rainhaB.desenho();
        System.out.print(" - ");
        Rei reiB = new Rei('b');
        reiB.desenho();
        System.out.println("");
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
        Posicao origTeste = new Posicao();
        Posicao destTeste = new Posicao();
        origTeste.setColuna('c');
        origTeste.setLinha(3);

        System.out.println("Testes Peao:");

        System.out.println("Teste movimento lateral:");
        destTeste.setColuna('b');
        destTeste.setLinha(3);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal indefinido:");
        destTeste.setColuna('d');
        destTeste.setLinha(5);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento frontal indefinido:");
        destTeste.setColuna('c');
        destTeste.setLinha(6);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste Branco:");

        System.out.println("Teste movimento 1 pra tras:");
        destTeste.setColuna('c');
        destTeste.setLinha(2);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento 2 pra tras:");
        destTeste.setColuna('c');
        destTeste.setLinha(1);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal esquerda tras:");
        destTeste.setColuna('b');
        destTeste.setLinha(2);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal direita tras:");
        destTeste.setColuna('d');
        destTeste.setLinha(2);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento 1 pra frente:");
        destTeste.setColuna('c');
        destTeste.setLinha(4);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento 2 pra frente:");
        destTeste.setColuna('c');
        destTeste.setLinha(5);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal esquerda frente:");
        destTeste.setColuna('b');
        destTeste.setLinha(4);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal direita frente:");
        destTeste.setColuna('d');
        destTeste.setLinha(4);
        if(peaoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("############################");

        System.out.println("Teste Preto:");

        System.out.println("Teste movimento 1 pra tras:");
        destTeste.setColuna('c');
        destTeste.setLinha(2);
        if(peaoP.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento 2 pra tras:");
        destTeste.setColuna('c');
        destTeste.setLinha(1);
        if(peaoP.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal esquerda tras:");
        destTeste.setColuna('b');
        destTeste.setLinha(2);
        if(peaoP.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal direita tras:");
        destTeste.setColuna('d');
        destTeste.setLinha(2);
        if(peaoP.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento 1 pra frente:");
        destTeste.setColuna('c');
        destTeste.setLinha(4);
        if(peaoP.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento 2 pra frente:");
        destTeste.setColuna('c');
        destTeste.setLinha(5);
        if(peaoP.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal esquerda frente:");
        destTeste.setColuna('b');
        destTeste.setLinha(4);
        if(peaoP.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal direita frente:");
        destTeste.setColuna('d');
        destTeste.setLinha(4);
        if(peaoP.checaMovimento(origTeste, destTeste)) {
            System.out.println("Peao se move");
        } else {
            System.out.println("Peao nao se move");
        }
        System.out.println("############################");

        System.out.println("############################");

        System.out.println("############################");

        System.out.println("Teste Bispo:");

        System.out.println("Teste movimento diagonal direita frente e tras:");
        destTeste.setColuna('e');
        destTeste.setLinha(5);
        if(bispoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Bispo se move");
        } else {
            System.out.println("Bispo nao se move");
        }
        destTeste.setColuna('e');
        destTeste.setLinha(1);
        if(bispoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Bispo se move");
        } else {
            System.out.println("Bispo nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal esquerda frente e tras:");
        destTeste.setColuna('a');
        destTeste.setLinha(5);
        if(bispoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Bispo se move");
        } else {
            System.out.println("Bispo nao se move");
        }
        destTeste.setColuna('a');
        destTeste.setLinha(1);
        if(bispoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Bispo se move");
        } else {
            System.out.println("Bispo nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento lateral esquerda frente e tras:");
        destTeste.setColuna('a');
        destTeste.setLinha(3);
        if(bispoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Bispo se move");
        } else {
            System.out.println("Bispo nao se move");
        }
        destTeste.setColuna('e');
        destTeste.setLinha(3);
        if(bispoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Bispo se move");
        } else {
            System.out.println("Bispo nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento em L:");
        destTeste.setColuna('a');
        destTeste.setLinha(4);
        if(bispoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Bispo se move");
        } else {
            System.out.println("Bispo nao se move");
        }
        destTeste.setColuna('e');
        destTeste.setLinha(2);
        if(bispoB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Bispo se move");
        } else {
            System.out.println("Bispo nao se move");
        }
        System.out.println("############################");
        System.out.println("############################");

        System.out.println("Testes Cavalo:");
        origTeste.setLinha(3);
        origTeste.setColuna('d');

        System.out.println("Teste movimento em L:");
        destTeste.setColuna('b');
        destTeste.setLinha(2);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('b');
        destTeste.setLinha(4);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('f');
        destTeste.setLinha(4);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('f');
        destTeste.setLinha(2);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('c');
        destTeste.setLinha(5);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('e');
        destTeste.setLinha(5);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('e');
        destTeste.setLinha(1);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('c');
        destTeste.setLinha(1);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento lateral:");
        destTeste.setColuna('f');
        destTeste.setLinha(3);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('a');
        destTeste.setLinha(3);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        System.out.println("############################");
        System.out.println("Teste movimento diagonal:");
        destTeste.setColuna('f');
        destTeste.setLinha(5);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('a');
        destTeste.setLinha(1);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento vertical:");
        destTeste.setColuna('d');
        destTeste.setLinha(5);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        destTeste.setColuna('d');
        destTeste.setLinha(1);
        if(cavaloB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Cavalo se move");
        } else {
            System.out.println("Cavalo nao se move");
        }
        System.out.println("############################");
        System.out.println("############################");

        System.out.println("Testes Torre:");

        System.out.println("Teste movimento vertical:");
        destTeste.setColuna('d');
        destTeste.setLinha(5);
        if(torreB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Torre se move");
        } else {
            System.out.println("Torre nao se move");
        }
        destTeste.setColuna('d');
        destTeste.setLinha(1);
        if(torreB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Torre se move");
        } else {
            System.out.println("Torre nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento horizontal:");
        destTeste.setColuna('g');
        destTeste.setLinha(3);
        if(torreB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Torre se move");
        } else {
            System.out.println("Torre nao se move");
        }
        destTeste.setColuna('a');
        destTeste.setLinha(3);
        if(torreB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Torre se move");
        } else {
            System.out.println("Torre nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento L:");
        destTeste.setColuna('f');
        destTeste.setLinha(5);
        if(torreB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Torre se move");
        } else {
            System.out.println("Torre nao se move");
        }
        destTeste.setColuna('e');
        destTeste.setLinha(5);
        if(torreB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Torre se move");
        } else {
            System.out.println("Torre nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento diagonal:");
        destTeste.setColuna('f');
        destTeste.setLinha(5);
        if(torreB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Torre se move");
        } else {
            System.out.println("Torre nao se move");
        }
        destTeste.setColuna('b');
        destTeste.setLinha(1);
        if(torreB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Torre se move");
        } else {
            System.out.println("Torre nao se move");
        }
        System.out.println("############################");
        System.out.println("############################");

        System.out.println("Testes Rei:");

        System.out.println("Teste movimentos uma casa diagonal:");
        destTeste.setColuna('e');
        destTeste.setLinha(4);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('e');
        destTeste.setLinha(2);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('c');
        destTeste.setLinha(4);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('c');
        destTeste.setLinha(2);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimentos uma casa horzintal/vertical:");
        destTeste.setColuna('d');
        destTeste.setLinha(4);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('d');
        destTeste.setLinha(2);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('c');
        destTeste.setLinha(3);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('e');
        destTeste.setLinha(3);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento em L:");
        destTeste.setColuna('f');
        destTeste.setLinha(4);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('c');
        destTeste.setLinha(5);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento em diagonal indefinida:");
        destTeste.setColuna('g');
        destTeste.setLinha(6);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('a');
        destTeste.setLinha(6);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento em horzintal/vertical indefinida:");
        destTeste.setColuna('f');
        destTeste.setLinha(3);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        destTeste.setColuna('d');
        destTeste.setLinha(5);
        if(reiB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rei se move");
        } else {
            System.out.println("Rei nao se move");
        }
        System.out.println("############################");
        System.out.println("############################");

        System.out.println("Testes Rainha:");

        System.out.println("Teste movimento em horzintal/vertical indefinida:");
        destTeste.setColuna('f');
        destTeste.setLinha(3);
        if(rainhaB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rainha se move");
        } else {
            System.out.println("Rainha nao se move");
        }
        destTeste.setColuna('d');
        destTeste.setLinha(5);
        if(rainhaB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rainha se move");
        } else {
            System.out.println("Rainha nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento em diagonal indefinida:");
        destTeste.setColuna('f');
        destTeste.setLinha(1);
        if(rainhaB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rainha se move");
        } else {
            System.out.println("Rainha nao se move");
        }
        destTeste.setColuna('a');
        destTeste.setLinha(6);
        if(rainhaB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rainha se move");
        } else {
            System.out.println("Rainha nao se move");
        }
        System.out.println("############################");

        System.out.println("Teste movimento em L:");
        destTeste.setColuna('f');
        destTeste.setLinha(4);
        if(rainhaB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rainha se move");
        } else {
            System.out.println("Rainha nao se move");
        }
        destTeste.setColuna('c');
        destTeste.setLinha(1);
        if(rainhaB.checaMovimento(origTeste, destTeste)) {
            System.out.println("Rainha se move");
        } else {
            System.out.println("Rainha nao se move");
        }
        System.out.println("############################");



        System.out.println("############################");
    }
}
