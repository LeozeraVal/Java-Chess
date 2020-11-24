package xadrez;

public abstract class Peca {

    private char cor;

    public Peca(char cor) {
        if (cor == 'p' || cor == 'b') {
            this.cor = cor;
        } else {
            System.out.println("Uma peca tem que ser preta ou branca.");
        }
    }
    
    public char getCor() {
        return this.cor;
    }

    public abstract void desenho();

    public abstract boolean checaMovimento(Posicao pos_orig, Posicao pos_dest);
}