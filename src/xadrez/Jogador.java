package xadrez;

import java.util.Iterator;
import java.util.LinkedList;

public class Jogador {
    private String nome;
    private char cor;
    private LinkedList<Peca> pecas;


    public Jogador(String nome, char cor) {
        this.nome = nome;
        if (cor != 'p' && cor != 'b') {
            System.out.println("Um jogador so pode controlar as pecas brancas ou pretas");
            return;
        }
    
        this.cor = cor;
        this.pecas = new LinkedList<Peca>();
    }

    public String getNome() {
        return this.nome;
    }

    public char getCor() {
        return this.cor;
    }

    public void adicionaPeca(Peca peca) {
        this.pecas.add(peca);
    }

    public void removePeca(Peca peca) {
        this.pecas.remove(peca);
    }

    public void verPecas() {
        System.out.println("Pecas do jogador " + this.nome + ':');

        Iterator<Peca> it = pecas.iterator();
        while(it.hasNext()) {
            it.next().desenho();
            System.out.print(" ");
        }
        System.out.println("");
    }
}
