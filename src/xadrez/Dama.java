package xadrez;

public class Dama extends Peca {

    public Dama(char cor) {
        super(cor);
    }

    // Usa unicode para printar seu simbolo.
    @Override
    public void desenho() {
        if (this.getCor() == 'b') {
            System.out.print('\u2655');
        } else {
            System.out.print('\u265B');
        }
    }
    
    @Override
    public boolean checaMovimento(int linha_orig, char coluna_orig, int linha_dest, char coluna_dest) {
        // Se destino for a posicao de origem retorne falso
        if (linha_dest == linha_orig && coluna_dest == coluna_orig) {
            return false;
        }
        
        // Uma dama tem sua checagem de movimento como uma juncao das checagens da torre e do bispo, portanto reutilizamos o codigo.
        
        if (linha_orig == linha_dest && coluna_orig != coluna_dest) {
            return true;
        }

        if (linha_orig != linha_dest && coluna_orig == coluna_dest) {
            return true;
        }

        if (Math.abs(linha_dest - linha_orig) == Math.abs(coluna_dest - coluna_orig)) {
            return true;
        }

        // Qualquer outro movimento eh invalido.
        return false;
    }
    
}
