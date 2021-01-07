package xadrez;

import java.util.Iterator;
import java.util.LinkedList;

public class Jogador {
    // Cada Jogador Possui um nome.
    private String nome;
    // Uma cor que comanda.
    private char cor;
    // E uma lista ligada com todas suas pecas ativas.
    private LinkedList<Peca> pecas;

    /**
     * Construtor do Jogador, cria um novo jogador com o nome e cor inseridos caso validos.
     * @param nome Nome do Jogador a ser criado.
     * @param cor Cor que o Jogador comanda.
     * @throws Exception Possui a mensagem de cor invalida do Jogador.
     */
    public Jogador(String nome, char cor) throws Exception {
        // Atribui o nome ao jogador
        this.nome = nome;
        // Valida a cor, caso for invalida Joga Excecao.
        if (cor != 'p' && cor != 'b') {
            throw new Exception("Cor invalida Jogador");
        }
    
        // Atribui a cor e cria uma nova Lista Ligada para as Pecas.
        this.cor = cor;
        this.pecas = new LinkedList<Peca>();
    }

    public String getNome() {
        return this.nome;
    }

    public char getCor() {
        return this.cor;
    }

    /**
     * Metodo usado para adicionar uma Peca ao controle deste Jogador,
     * @param peca Peca a ser adicionada.
     */
    public void adicionaPeca(Peca peca) {
        this.pecas.add(peca);
    }

    /**
     * Metodo usado para remover uma Peca do controle deste Jogador.
     * @param peca Peca a ser removida.
     */
    public void removePeca(Peca peca) {
        this.pecas.remove(peca);
    }

    /**
     * Metodo usado para limpar a Lista de Pecas de um Jogador.
     */
    public void limpaPecas() {
        this.pecas.clear();
    }

    /**
     * Metodo utilizado para ver uma lista das Pecas que estao no controle do Jogador.
     */
    public void verPecas() {
        System.out.println("Pecas do jogador " + this.nome + ':');

        // Cria um iterador para passar pela lista de Pecas do Jogador.
        Iterator<Peca> it = pecas.iterator();
        // Enquanto possuir um proximo na lista:
        while(it.hasNext()) {
            // Chama o metodo desenho() da Peca e printa um espaco depois.
            it.next().desenho();
            System.out.print(" ");
        }
        System.out.println("");
    }
}
