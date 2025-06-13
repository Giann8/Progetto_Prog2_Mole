package mole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import mole.Interfaces.Column;
import mole.Interfaces.Index;
import mole.Interfaces.Table;

/**
 * Una tabella immutabile che implementa {@link Table}.
 * 
 * <p>
 * Una tabella è formata da un indice e da una lista di colonne entrambe
 * immutabili.
 * </p>
 * 
 * @param <V> il tipo della tabella
 */
public class Tabella<V> implements Table<V> {
    /** L'indice della tabella */
    private final Index index;
    /** La lista delle colonne della tabella */
    private final List<Column<V>> colonne = new ArrayList<>();

    /*
     * AF:
     * 
     * AF(colonne)= una tabella avente una lista di colonne definita come segue
     * C(colonne)= {colonna(name1,indice,values1),colonna(name2,indice,values2),...}
     * e un Index = colonna.getFirst().getIndex()
     * 
     * RI:
     * 
     * -indice != null
     * -colonne != null && colonne.size() > 0
     * -per ogni i, j in [0, colonne.size()), i != j tale che
     * colonne.get(i).getName() != colonne.get(j).getName() &&
     * colonne.get(i).getIndex().equals(colonne.get(j).getIndex()) && equals this.indice
     * 
     */

    /**
     * Crea una tabella.
     * <p>
     * Crea una tabella con un indice e una lista di colonne.
     * In caso le colonne avessere {@code Nome == Null} allora gliene sarà assegnato
     * uno nella forma {@code "Column_" + i} dove {@code i} è l'indice di posizione
     * della colonna
     * </p>
     * 
     * @param colonne la lista di colonne della tabella.
     * 
     * @throws IllegalArgumentException se {@code colonne} è vuota, se almeno una
     *                                  colonna ha un indice diverso da
     *                                  {@code index}
     *                                  o se almeno una coppia di colonne ha lo
     *                                  stesso {@code nome}.
     * 
     * @throws NullPointerException     se {@code colonne}
     *                                  è null.
     */
    public Tabella(List<Column<V>> colonne) throws IllegalArgumentException, NullPointerException {

        Objects.requireNonNull(colonne, "La lista di colonne non può essere null");

        if (colonne.isEmpty()) {
            throw new IllegalArgumentException("La lista di colonne non può essere vuota");
        }

        this.index = colonne.getFirst().getIndex();

        for (int i = 0; i < colonne.size(); i++) {
            Column<V> colonna = colonne.get(i);
            if (!colonna.getIndex().equals(this.index)) {
                throw new IllegalArgumentException("Tutte le colonne devono avere lo stesso indice");
            }

            if (colonna.getName() == null || colonna.getName().isEmpty()) {
                colonna = colonna.renameColumn("Column_" + i);
            } else {
                for (Column<V> colonna2 : colonne) {
                    if (colonna.getName() == colonna2.getName() && !colonna.equals(colonna2)) {
                        throw new IllegalArgumentException(
                                "Le colonne non possono avere lo stesso nome: " + colonna.getName());
                    }
                }
            }

            this.colonne.add(colonna);
        }

    }

    @Override
    public Table<V> reIndexTable(Index index) throws NullPointerException {
        if (index == null) {
            throw new NullPointerException("L'indice non può essere null");
        }

        List<Column<V>> nuoveColonne = new ArrayList<>();
        for (Column<V> colonna : colonne) {
            nuoveColonne.add(colonna.cambiaIndice(index));
        }
        return new Tabella<>(nuoveColonne);

    }

    @Override
    public Table<V> changeColumnLabels(String[] newLabels) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(newLabels, "Le etichette delle colonne non possono essere null");
        if (newLabels.length != colonne.size()) {
            throw new IllegalArgumentException("Il numero di etichette deve corrispondere al numero di colonne");
        }

        List<Column<V>> nuoveColonne = new ArrayList<>();
        for (int i = 0; i < colonne.size(); i++) {
            nuoveColonne.add(colonne.get(i).renameColumn(newLabels[i]));
        }
        return new Tabella<>(nuoveColonne);
    }

    @Override
    public Table<V> map(Function<Column<V>, Column<V>> trasformazione)
            throws NullPointerException, IllegalArgumentException {
        if (trasformazione == null) {
            throw new NullPointerException("La trasformazione non può essere null");
        }

        List<Column<V>> nuoveColonne = new ArrayList<>();
        for (Column<V> colonna : colonne) {
            nuoveColonne.add(trasformazione.apply(colonna));
        }
        for (Column<V> colonna : nuoveColonne) {
            if (!nuoveColonne.getFirst().getIndex().equals(colonna.getIndex())) {
                throw new IllegalArgumentException(
                        "Tutte le colonne devono avere lo stesso indice dopo la trasformazione");
            }
            for (int i = 0; i < nuoveColonne.size(); i++) {
                if (colonna.getName().equals(nuoveColonne.get(i).getName()) && !colonna.equals(nuoveColonne.get(i))) {
                    throw new IllegalArgumentException(
                            "Le colonne non possono avere lo stesso nome dopo la trasformazione");
                }
            }
        }
        return new Tabella<>(nuoveColonne);
    }

    @Override
    public Table<V> affiancaTabella(Table<V> tabella2) throws NullPointerException {

        Objects.requireNonNull(tabella2, "La tabella da affiancare non può essere null");
        List<Column<V>> nuoveColonne = new ArrayList<>();
        Index nuovaIndex = this.index.indexFusion(tabella2.getIndex());

        for (Column<V> colonna : this) {
            Column<V> reindexedcolonna = colonna.reindexColumn(nuovaIndex);
            nuoveColonne.add(reindexedcolonna);
        }

        for (Column<V> colonna2 : tabella2) {
            Column<V> reindexedcolonna = colonna2.reindexColumn(nuovaIndex);
            nuoveColonne.add(reindexedcolonna);
        }

        return new Tabella<>(nuoveColonne);

    }

    @Override
    public Table<V> impilaTabella(Table<V> tabella2) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(tabella2, "La tabella da impilare non può essere null");
        List<Column<V>> nuoveColonne = new ArrayList<>();
        List<String> colonneImpilate = new ArrayList<>();

        if (this.index.equals(tabella2.getIndex())) {
            throw new IllegalArgumentException("Le tabelle devono avere indici diversi per essere impilate");
        }

        for (Object label : this.index) {
            if (tabella2.getIndex().positionOf(label) >= 0) {
                throw new IllegalArgumentException("Le tabelle devono avere indici diversi per essere impilate: ");
            }
        }

        Index newIndex = this.index.indexFusion(tabella2.getIndex());

        for (Column<V> colonna : this) {
            Column<V> colonnaSameLabel = tabella2.columnAtLabel(colonna.getName());
            if (colonnaSameLabel != null) {
                nuoveColonne.add(colonna.impilaColonna(colonnaSameLabel));
                colonneImpilate.add(colonna.getName());
            } else {
                nuoveColonne.add(colonna.reindexColumn(newIndex));
            }
        }

        for (Column<V> colonna2 : tabella2) {
            if (!colonneImpilate.contains(colonna2.getName())) {
                nuoveColonne.add(colonna2.reindexColumn(newIndex));
            }
        }
        return new Tabella<>(nuoveColonne);
    }

    @Override
    public Index getIndex() {
        return index;
    }

    @Override
    public int getNumberOfColumns() {
        return colonne.size();
    }

    @Override
    public Iterator<Column<V>> iterator() {
        return new ArrayList<>(colonne).iterator();
    }

    @Override
    public Column<V> columnAtPosition(int j) {
        return colonne.get(j);
    }

    @Override
    public Column<V> columnAtLabel(String j) throws NullPointerException {
        if (j == null) {
            throw new NullPointerException("L'etichetta della colonna non può essere null");
        }
        for (Column<V> colonna : colonne) {
            if (colonna.getName().equals(j)) {
                return colonna;
            }
        }
        return null;
    }

    @Override
    public V atPosition(int i, int j) throws IndexOutOfBoundsException {
        if (j < 0 || j >= colonne.size()) {
            throw new IndexOutOfBoundsException("Indice di colonna fuori dai limiti: " + i);
        }
        Column<V> colonna = colonne.get(j);
        return colonna.atPosition(i);
    }

    @Override
    public V atLabel(Object i, String j) throws NullPointerException {
        if (i == null || j == null) {
            throw new NullPointerException("Le etichette non possono essere null");
        }
        Column<V> colonna = columnAtLabel(j);
        if (colonna == null) {
            return null;
        }
        return colonna.atLabel(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tabella={\n");
        for (Column<V> colonna : colonne) {
            sb.append(colonna.getName()).append(": ").append(colonna.toString()).append("\n");
        }
        return sb.append("}").toString();
    }

}
