package mole.trasformazioni;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import mole.Colonna;
import mole.Interfaces.Column;

/**
 * Una classe che implementa {@link Function} per moltiplicare i valori di una colonna
 * di tipo {@link Integer} per un valore specificato.
 */
public class MoltiplicaTable implements Function<Column<Integer>, Column<Integer>> {
    /**
     * Il moltiplicatore da applicare ai valori della colonna.
     */
    private int moltiplicatore;
    
    /**
     * Crea una funzione che moltiplica i valori di una colonna per il valore specificato.
     * 
     * @param moltiplicatore il valore per cui moltiplicare
     */
    public MoltiplicaTable(int moltiplicatore) {
        this.moltiplicatore = moltiplicatore;
    }

    @Override
    public Column<Integer> apply(Column<Integer> t) {
        if (t == null) {
            throw new NullPointerException("La colonna non può essere null");
        }
        List<Integer> nuovaLista = new ArrayList<>();
        for (Integer value : t) {
            if (value == null) {
                nuovaLista.add(moltiplicatore);
            } else {
                nuovaLista.add(value * moltiplicatore);
            }
        }
        return new Colonna<>(t.getName(), t.getIndex(), nuovaLista);
    }
    
}
