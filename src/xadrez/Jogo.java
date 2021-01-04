package xadrez;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
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
    private LinkedList<Peca> pecas;

    /**
     * O construtor da classe jogo eh responsavel por alocar todas as pecas, o tabuleiro, os jogadores e atribuir aos jogadores suas pecas.
     */
    public Jogo() {
        // Instancia um tabuleiro.
        this.tabuleiro = new Tabuleiro();
        // Instancia um vetor de Peca.
        this.pecas = new LinkedList<Peca>();

        
        // Abre um Scanner
        Scanner ent = new Scanner(System.in);
        System.out.println("Bem Vindo ao Xadrez no terminal! (Programa por Leonardo Valerio Morales)");
        
        // Usamos a flag true pra sair do loop quando finalmente obtivermos uma entrada valida.
        boolean flag = true;
        do {
            System.out.println("Deseja carregar um jogo ou iniciar um novo? Digite 'Carregar' para carregar um jogo ou 'Novo' para iniciar um novo jogo.");
            try {
                String str = ent.nextLine();
                if (str.length() < 1) {
                    throw new Exception();
                }
                if (str.equalsIgnoreCase("Novo")) {
                    flag = false;
                } else if (str.equalsIgnoreCase("Carregar")) {
                    if(this.carregar(ent)){
                        return;
                    } else {
                        System.out.println("Erro ao carregar Arquivo.");
                        continue;
                    }
                } else {
                    throw new Exception("Comando invalido.");
                }
            } catch (Exception e) {
                System.out.println("Nao foi inserido um comando valido. Tente novamente.");
            }
        } while (flag);
        
        
        // Chama o metodo populaNovoJogo que cria todas as Pecas em uma ordem padrao no vetor pecas.
        this.populaNovoJogo();

        // Usamos a flag true pra sair do loop quando finalmente obtivermos uma entrada valida.
        flag = true;
        do {
            // Utilizamos esse try catch para tratar a entrada do usuario para o nome do Jogador.
            try {
                System.out.println("Digite o nome do Jogador 1, comandante das brancas.");
                // Instancia o jogador 1 com o nome inserido e a cor branca, caso o usuario nao tenha inserido nada joga execao.
                String str = ent.nextLine();
                if (str.length() < 1) {
                    throw new Exception();
                }
                this.j1 = new Jogador(str, 'b');
                // Adiciona as pecas padroes da cor branca ao jogador 1.
                for (int i = 0; i < 16; i++) {
                    this.j1.adicionaPeca(pecas.get(i));
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Nao foi inserido um nome valido para o Jogador 1. Tente novamente.");
            }
        } while (flag);
        // Usamos a flag true pra sair do loop quando finalmente obtivermos uma entrada valida.
        flag = true;
        do {
            // Utilizamos esse try catch para tratar a entrada do usuario para o nome do Jogador.
            try {
                System.out.println("Digite o nome do Jogador 2, comandante das pretas.");
                // Instancia o jogador 2 com o nome inserido e a cor preta, caso o usuario nao tenha inserido nada joga execao.
                String str = ent.nextLine();
                if (str.length() < 1) {
                    throw new Exception();
                }
                this.j2 = new Jogador(str , 'p');
                // Adiciona as pecas padroes da cor preta ao jogador 2.
                for (int i = 16; i < 32; i++) {
                    this.j2.adicionaPeca(pecas.get(i));
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Nao foi inserido um nome valido para o Jogador 2. Tente novamente.");
            }
        } while(flag);

        // Chama o metodo populaNovoTabuleiro() para inserir no tabuleiro do jeito padrao de inicio de jogo as pecas.
        this.tabuleiro.populaNovoTabuleiro(pecas);

        // Inicia o turno como turno do comandante das brancas j1.
        this.turn = j1;
    }

    /**
     * Passa para o tabuleiro os argumentos do movimento, se o tabuleiro efetuar o
     * movimento, passa a vez. Se nao for, apenas comunica que ainda eh a vez do
     * jogador.
     * 
     * @param linha_orig  Linha de origem do movimento.
     * @param coluna_orig Coluna de origem do movimento.
     * @param linha_dest  Linha de destino do moivmento.
     * @param coluna_dest Coluna de destino do movimento.
     * @param cor         Cor do jogador que esta efetuando o movimento.
     * @return true caso movimento foi efetuado e false caso contrario.
     */
    private boolean mover(char coluna_orig, int linha_orig, char coluna_dest, int linha_dest, char cor) {
        // Cria uma Peca temporaria que armazena a referencia a peca no destino do movimento.
        Peca temp = this.tabuleiro.achaPeca(linha_dest, coluna_dest);

        // Se o movimento nao foi efetuado comunicamos ao jogador, voltamos temp a null e retornamos false.
        if (!this.tabuleiro.mover(linha_orig, coluna_orig, linha_dest, coluna_dest, cor)) {
            System.out.println("Ainda esta na sua vez.");
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
        // Tambem precisamos remover da lista de pecas ativas do proprio Jogo.
        this.pecas.remove(temp);

        // Caso nao exista Peca no destino, temp possuira null e o metodo removePeca apenas nao remove Peca do
        // jogador nem do proprio Jogo.
        
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
    private void populaNovoJogo() {
        try {
            // Primeiro criamos as Pecas que estao na primeira linha, as especificas brancas.
            this.pecas.add(new Torre('b'));
            this.pecas.add(new Cavalo('b'));
            this.pecas.add(new Bispo('b'));
            this.pecas.add(new Dama('b'));
            this.pecas.add(new Rei('b'));
            this.pecas.add(new Bispo('b'));
            this.pecas.add(new Cavalo('b'));
            this.pecas.add(new Torre('b'));
    
            // Entao fazemos um loop para criar os Peoes brancos da segunda linha.
            for (int i = 8; i < 16; i++) {
                this.pecas.add(new Peao('b'));
            }
    
            // Portanto, ate aqui temos que o vetor pecas de 0 ate 15 possui todas as pecas brancas.
    
            // Depois criamos as Pecas especificas pretas, da linha 8.
            this.pecas.add(new Torre('p'));
            this.pecas.add(new Cavalo('p'));
            this.pecas.add(new Bispo('p'));
            this.pecas.add(new Dama('p'));
            this.pecas.add(new Rei('p'));
            this.pecas.add(new Bispo('p'));
            this.pecas.add(new Cavalo('p'));
            this.pecas.add(new Torre('p'));
    
            // E logo depois os peoes pretos da linha 7.
            for (int i = 24; i < 32; i++) {
                this.pecas.add(new Peao('p'));
            }
        } catch (Exception e) {
            if (e.getMessage().equals("Cor Invalida de Peca")) {
                System.out.println("Erro interno, Cores invalidas para Pecas.");
            }
        }

        // Ao final o vetor pecas possui de 16 a 31 todas as pecas pretas.

        // O vetor pecas possui todas as pecas do jogo.
    }

    /**
     * Metodo chamado pelo construtor do Jogo para carregar o estado do jogo de um arquivo txt.
     * @param in Scanner inicializado pelo Construtor.
     * @return true caso arquivo tenha sido carregado com sucesso e false caso contrario.
     */
    private boolean carregar(Scanner in) {
        System.out.println("Digite o nome do arquivo em que deseja carregar o jogo: (Sem .txt)");
        // Recebemos o nome do arquivo a ser carregado.
        String file_name = in.nextLine();
        // Utilizamos um try catch para tratar as varias excecoes que podem ser jogadas pelos metodos de entrada e saida.
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file_name + ".txt"))) {
            // Declaramos uma String line para receber cada linha do arquivo e ja captamos a primeira.
            String line = bufferedReader.readLine();
            // A primeira eh o nome do Jogador1, portanto se nao existir um nome jogamos excecao.
            if (line.length() == 0) {
                throw new Exception("Nome de Jogador Invalido.");
            }
            this.j1 = new Jogador(line, 'b');

            // A segunda eh o nome do Jogador2, portanto se nao existir um nome jogamos excecao.
            line = bufferedReader.readLine();
            if (line.length() == 0) {
                throw new Exception("Nome de Jogador Invalido.");
            }
            this.j2 = new Jogador(line, 'p');

            // Lemos a proxima linha que representa a cor do comandante que possui a vez.
            line = bufferedReader.readLine();
            if (line.equalsIgnoreCase("b")) {
                this.turn = this.j1;
            } else if (line.equalsIgnoreCase("p")) {
                this.turn = this.j2;
            } else {
                // Se for uma cor invalida jogamos excecao.
                throw new Exception("Cor de Turno Invalida no arquivo.");
            }

            // A proxima linha eh a quantidade de pecas ativas no momento.
            line = bufferedReader.readLine();
            // Utilizamos esta funcao para converter de String para int.
            int qtd_pecas = Integer.parseInt(line);

            // Criamos uma matriz temporaria que ira armazenar todas as pecas detectadas
            // Possui tamanho de 8x8 pois eh uma mimica do tabuleiro (Facilita na hora de atribuir as Pecas ao tabuleiro).
            Peca[][] pecas = new Peca[8][8];

            // Enquanto existirem pecas:
            while(qtd_pecas > 0) {
                // Leia a linha que descreve a Peca.
                line = bufferedReader.readLine();

                // Converta para um Vetor de Chars.
                char[] v_linha = line.toCharArray();

                // Deste Vetor de Chars extraimos a coluna linha e cor da Peca, se qualquer uma estiver invalida
                // jogamos uma excecao.
                char coluna = v_linha[0];
                if (coluna > 'h' || coluna < 'a') {
                    throw new Exception("Coluna Invalida no arquivo.");
                }
                int linha = v_linha[1]-48;
                if (linha > 8 || coluna < 1) {
                    throw new Exception("Linha Invalida no arquivo.");
                }
                char cor = v_linha[3];
                if (cor != 'b' && cor != 'p') {
                    throw new Exception("Cor de Peca Invalida no arquivo.");
                }

                // Finalmente le somente as 2 primeiras letras do nome da Peca. (Eh o suficiente para distinguir as Pecas)
                String nome_peca = new String(Character.toString(v_linha[5]) + Character.toString(v_linha[6]));

                // Cria uma peca temporaria que ira receber a referencia de alguma Peca de acordo com seu nome.
                Peca temp = null;
                // Peao
                if (nome_peca.equals("Pe")) {
                    temp = new Peao(cor);
                } else if (nome_peca.equals("Bi")) {
                    // Bispo
                    temp = new Bispo(cor);
                } else if (nome_peca.equals("Ca")) {
                    // Cavalo
                    temp = new Cavalo(cor);
                } else if (nome_peca.equals("Da")) {
                    // Dama
                    temp = new Dama(cor);
                } else if (nome_peca.equals("Re")) {
                    // Rei
                    temp = new Rei(cor);
                } else if (nome_peca.equals("To")) {
                    // Torre
                    temp = new Torre(cor);
                } else {
                    // Se o nome nao corresponde a nenhum destes, eh um nome invalido de Peca.
                    throw new Exception("Nome de Peca Invalido");
                }

                // Como o construtor das Pecas ja valida a cor, apenas adicionamos a lista do jogador respectivo a peca recem criada.
                if (cor == 'b') {
                    this.j1.adicionaPeca(temp);
                } else {
                    this.j2.adicionaPeca(temp);
                }
                // Adicionamos tambem a lista de Pecas do jogo.
                this.pecas.add(temp);

                // Finalmente colocamos a referencia da Peca na sua posicao na matriz de Pecas se esta posicao estiver vazia.
                if (pecas[linha-1][coluna-97] != null) {
                    pecas[linha-1][coluna-97] = temp;
                } else {
                    // Caso contrario o arquivo indica duas Pecas na mesma posicao e por isso disparamos Excecao.
                    throw new Exception("Duas Pecas na mesma Posicao no Arquivo.");
                }

                // Diminuimos a qtd de Pecas.
                qtd_pecas--;
            }

            // No final a matriz pecas possui as pecas em suas posicoes como se fosse um pseudo-tabuleiro. E um metodo do Tabuleiro
            // cuida de popular um tabuleiro com uma matriz desse tipo.
            this.tabuleiro.populaVelhoTabuleiro(pecas);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao Encontrado!");
            return false;
        } catch (IOException e) {
            System.out.println("Excecao de E/S!. Provavelmente o numero de Pecas nao equivale ao Numero de Posicoes no arquivo.");
            return false;
        } catch (Exception e) {
            // Aqui tratamos as excecoes diversas printando a mensagem e retornando false.
            System.out.println(e.getMessage()); 
            return false;
        }
        return true;
    }
    
    /**
     * Metodo responsavel por salvar o estado do jogo em um arquivo .txt.
     */
    private void salvar(Scanner in) {
        System.out.println("Digite o nome do arquivo em que deseja salvar o jogo: (Sem .txt)");
        String file_name = in.nextLine();
        // Em um bloco de try catch tentamos gravar os dados no arquivo que o usuario forneceu.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file_name+".txt"))){
            // Primeiro salvamos todos os dados que o Jogo tem, nomes dos jogadores e a cor do jogador ativo.
            bufferedWriter.write(this.j1.getNome() + "\n");
            bufferedWriter.write(this.j2.getNome() + "\n");
            bufferedWriter.write(this.turn.getCor() + "\n");
            // Entao chamamos o metodo stringTabuleiro no tabuleiro para obter na forma de uma String o estado do tabuleiro.
            // E a escrevemos no arquivo.
            bufferedWriter.write(this.tabuleiro.stringTabuleiro());
        } catch (IOException e) {
            // Caso alguma falha ocorra a tratamos dizendo que houve uma falha no salvamento do arquivo.
            System.out.println("Falha no salvamento do arquivo.");
        }
        return;
    }
    
    /**
     * Metodo chamado para tratar as entradas de movimento durante o loop de jogo.
     * 
     * @param str String de comando inserida pelo usuario.
     * @return String tratada.
     * @throws Exception Excecao que possui a mensagem de erro da entrada do
     *                   usuario.
     */
    private String trataEntrada(String str) throws Exception {
        // Se eh um dos comandos especificos retorna o comando.
        if (str.equalsIgnoreCase("Desistir") || str.equalsIgnoreCase("Empate") || str.equalsIgnoreCase("Salvar") || str.equalsIgnoreCase("Sair")) {
            return str;
        }
        // Caso nao eh um comando especifico, checamos se tem o tamanho de 5,
        // se nao nao eh um comando de movimento valido.
        if (str.length() != 5) {
            throw new Exception("Entrada nao tem 5 caracteres.");
        }
        
        // Converte a String para um array de caracteres para analisar cada um.
        char[] entrada = str.toCharArray();
        
        // Valida cada caracter do movimento.
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                if (entrada[i] != ' ') {
                    throw new Exception("Separe as coordenadas de origem e destino por espaco.");
                }
            } else {
                if (!((entrada[i] > 48 && entrada[i] < 57) || (entrada[i] > 96 && entrada[i] < 105))) {
                    throw new Exception("Linha ou Coluna maior do que o tabuleiro.");
                }
            }
        }
        
        // Caso o usuario tenha colocado a linha ao inves de coluna na primeira coordenada:
        if (entrada[0] > 48 && entrada[0] < 57) {
            // E colocou a coluna como segundo caracter:
            if (entrada[1] > 96 && entrada[1] < 105) {
                // Trocamos ambos.
                char temp = entrada[0];
                entrada[0] = entrada[1];
                entrada[1] = temp;
            } else {
                // Se o usuario inseriu duas linhas em uma coordenada jogamos excecao.
                throw new Exception("Erro de coordenada com duas Linhas.");
            }
        }
        
        // Caso o usuario tenha colocado a linha ao inves de coluna na segunda coordenada:
        if (entrada[3] > 48 && entrada[3] < 57) {
            // E colocou a coluna como segundo caracter:
            if (entrada[4] > 96 && entrada[4] < 105) {
                char temp = entrada[3];
                entrada[3] = entrada[4];
                entrada[4] = temp;
            } else {
                throw new Exception("Erro de coordenada com duas Linhas.");
            }
        }
        
        // Caso o usuario tenha inserido a coluna de maneira certa na primeira coordenada.
        if (entrada[0] > 96 && entrada[0] < 105) {
            // E nao colocou uma linha na mesma coordenada.
            if (!(entrada[1] > 48 && entrada[1] < 57)) {
                throw new Exception("Erro de coordenada com duas Colunas.");
            }
        }
        
        // Caso o usuario tenha inserido a coluna de maneira certa na segunda coordenada.
        if (entrada[3] > 96 && entrada[3] < 105) {
            // E nao colocou uma linha na mesma coordenada.
            if (!(entrada[4] > 48 && entrada[4] < 57)) {
                throw new Exception("Erro de coordenada com duas Colunas.");
            }
        }
        
        return new String(entrada);
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
            // Utilizamos um try catch para tratar qualquer excecao oriunda do Scanner.
            try {
                System.out.println("Insira um comando: ('Sair', 'Salvar', 'Desistir', 'Empate')");
                System.out.println("Ou um movimento: ('colunaOrigemLinhaOrigem colunaDestinoLinhaDestino')");
                // Espera uma entrada.
                String str = ent.nextLine();
                str = this.trataEntrada(str);

                // Se a entrada for "Sair" o avisamos que seu jogo nao sera salvo e perguntamos se tem certeza que quer sair.
                if (str.equalsIgnoreCase("Sair")) {
                    System.out.println("Deseja mesmo sair? Seu jogo nao sera salvo! Para salvar digite Nao e logo depois Salvar.");
                    System.out.println("Se quer mesmo sair digite SIM");
                    str = ent.nextLine();
                    if (str.equalsIgnoreCase("Nao")) {
                        continue;
                    } else {
                        break;
                    }
                }

                // Se a entrada for "Salvar", chama o metodo salvarJogo() e logo depois pergunta se o usuario quer continuar jogando
                // ou sair do jogo. Tratamos ambos os casos.
                if (str.equalsIgnoreCase("Salvar")) {
                    this.salvar(ent);
                    System.out.println("Deseja continuar jogando?");
                    str = ent.nextLine();
                    if (str.equalsIgnoreCase("Sim")) {
                        continue;
                    } else {
                        break;
                    }
                }

                // Se a entrada for "Desistir", anuncia a desistencia do jogador da vez e sai do loop.
                if (str.equalsIgnoreCase("Desistir")) {
                    System.out.println(this.turn.getNome() + " desitiu.");
                    break;
                }
    
                // Se a entrada for "Empate", a proxima entrada do teclado sera a resposta do oponente para a proposta de empate.
                if (str.equalsIgnoreCase("Empate")) {
                    System.out.println(this.turn.getNome() + " deseja terminara partida em empate, aceita? (Digite 'Sim' ou 'Nao')");
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
                if(mover(str.charAt(0), str.charAt(1) - 48, str.charAt(3), str.charAt(4) - 48, this.turn.getCor())) {
                    System.out.println("Movimento Efetuado!");
                    // Caso o movimento foi efetuado, vemos se colocou o inimigo em xeque.
                    if (this.turn == j1) {
                        if (this.tabuleiro.xeque('p')) {
                            // Se sim, checamos se foi xeque mate
                            if (this.tabuleiro.xequeMate('b')) {
                                // Caso o xeque mate tenha sido efetuado, anunciamos a vitoria do jogador e colocamos a flag fim em true.
                                System.out.println("Xeque Mate! O jogador " + this.j1.getNome() + " ganha por xeque mate!");
                                fim = true;
                            } else {
                                // Se foi apenas xeque anunciamos que o jogador colocu seu inimigo em xeque.
                                System.out.println("O jogador " + this.j1.getNome() + " colocou " + this.j2.getNome() + " em xeque.");
                            }
                        }
                    } else {
                        // Caso identido ao superior porem para o jogador 2.
                        if (this.tabuleiro.xeque('b')) {
                            if (this.tabuleiro.xequeMate('p')) {
                                System.out.println("Xeque Mate! O jogador " + this.j2.getNome() + " ganha por xeque mate!");
                                fim = true;
                            } else {
                                System.out.println("O jogador " + this.j2.getNome() + " colocou " + this.j1.getNome() + " em xeque.");
                            }
                        }
                    }
                    // Ao final do movimento passamos a vez.
                    this.passarVez();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Insira uma entrada Valida. As entradas validas sao:");
                System.out.println("'Sair' , 'Desistir', 'Empate', 'Salvar', 'ColunaLinha ColunaLinha'");
                continue;
            }
        }
        // Caso o jogo tenha chego ao fim, printamos o tabuleiro uma ultima vez.
        this.tabuleiro.printTabuleiro();
        // Agradecemos por jogar e esperamos o ENTER para sair do metodo.
        System.out.println("Obrigado por jogar! Aperte ENTER para sair. (Programa por Leonardo Valerio Morales)");
        ent.nextLine();
        ent.close();
        return;
    }
}
