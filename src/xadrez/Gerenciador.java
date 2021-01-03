package xadrez;

/**
 * Classe que mimica uma main.
 * @author Leonardo Valerio
 */
public class Gerenciador {

    public static void main(String[] args) throws Exception {
        // Instacia um novo Jogo.
        Jogo xadrez = new Jogo();
        // Chama o metodo loopJogo para comecar o Jogo.
        xadrez.loopJogo();
    }
    
}
