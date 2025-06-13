package mole.trasformazioni;

import java.util.List;
import java.util.function.Function;

import mole.Colonna;
import mole.IndexImpl;
import mole.Interfaces.Column;

/**
 * Una classe che implementa {@link Function} per calcolare la somma dei valori di una colonna
 * di tipo {@link Integer}.
 */
public class SommaTable implements Function<Column<Integer>, Column<Integer>> {
    /**
     * Crea una funzione che calcola la somma dei valori di una colonna.
     */
    public SommaTable() {}

    @Override
    public Column<Integer> apply(Column<Integer> t) {
        if (t == null) {
            throw new NullPointerException("La colonna non può essere null");
        }
        Integer somma = 0;
        for (Integer value : t) {
            if (value != null) {
                somma += value;
            }
        }
        return new Colonna<>(t.getName(), new IndexImpl(1), List.of(somma));
    }
}
