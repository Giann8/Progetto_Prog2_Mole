package mole.trasformazioni;

import java.util.function.Function;

/**
 * Una classe che implementa {@link Function} per moltiplicare un intero per un
 * valore specificato.
 */
public class Moltiplica implements Function<Integer, Integer> {
    /**
     * Il moltiplicatore da applicare all'intero.
     */
    private int moltiplicatore;

    /**
     * Crea una funzione che moltiplica un intero per il valore specificato.
     * 
     * @param moltiplicatore il valore per cui moltiplicare
     */
    public Moltiplica(int moltiplicatore) {
        this.moltiplicatore = moltiplicatore;
    }

    @Override
    public Integer apply(Integer t) {
        if (t instanceof Number) {
            return t * moltiplicatore;
        } else {
            throw new IllegalArgumentException("Il valore deve essere un numero");
        }
    }

}
