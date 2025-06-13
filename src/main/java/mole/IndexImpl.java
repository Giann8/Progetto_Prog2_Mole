package mole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import mole.Interfaces.Index;

/**
 * Classe immutabile rappresentante un'{@code indice}.
 * <p>
 * Un indice è una sequenza {@code immutabile} di etichette distinte utilizzate
 * per
 * identificare dei valori in una colonna e/o tabella.
 * Per mantenere lo stato di immutabilità le etichette fornite per la creazione
 * di un indice non devono essere mutabili.
 * </p>
 * 
 * <p>
 * L'uguaglianza tra indici è basata sulla lista di etichette.
 * </p>
 */
public class IndexImpl implements Index {

    /** Il nome dell'indice */
    private final String name;

    /** Le etichette dell'indice */
    private final Object[] labels;

    /*
     * - AF: Un indice è rappresentato da name,il suo nome o una stringa vuota, e
     * labels, una sequenza ordinata di etichette distinte.
     * 
     * 
     * - IR:
     * - labels non è null
     * - labels non è vuoto e non contiene etichette duplicate o null
     */

    /**
     * Costruttore dell'indice.
     * 
     * <p>
     * Crea un indice a partire da una lista di etichette che non presenta
     * duplicati o etichette vuote.
     * </p>
     * 
     * @param labels i label da cui è formato l'indice
     * @param name   il nome dell'indice o null se non si vuole assegnare un nome
     * @throws IllegalArgumentException se labels è vuoto o se contiene labels
     *                                  duplicati.
     * @throws NullPointerException     se labels è null.
     */
    public IndexImpl(Object[] labels, String name) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(labels, "Le etichette dell'indice non possono essere null");
        Set<Object> uniqueLabels = new HashSet<>();

        for (Object o : labels) {
            Objects.requireNonNull(o, "Le etichette dell'indice non possono essere null");
            if (!uniqueLabels.add(o)) {
                throw new IllegalArgumentException("L'indice non può contenere etichette duplicate: " + o);
            }
        }

        if (labels.length == 0) {
            throw new IllegalArgumentException("L'indice deve contenere almeno un'etichetta");
        }

        this.name = name;
        this.labels = Arrays.copyOf(labels, labels.length);
    }

    /**
     * costruttore per la creazione di un indice numerico senza nome
     * 
     * @param vi    valore iniziale dell'indice
     * @param passo passo dell'indice
     * @param vf    valore finale dell'indice
     * @throws IllegalArgumentException se passo è 0 o se vi è uguale a vf
     */
    public IndexImpl(int vi, int passo, int vf) throws IllegalArgumentException {

        if (vi == vf) {
            throw new IllegalArgumentException("Il valore iniziale e il valore finale non possono essere uguali");
        }
        if (passo == 0) {
            throw new IllegalArgumentException("Il passo non può essere 0");
        }

        List<Object> labelsList = new ArrayList<>();
        var iniziale = vi;
        var finale = vf;

        if (passo < 0) {
            for (int j = iniziale; j > finale; j += passo) {
                labelsList.add(j);
            }
        } else {

            for (int j = iniziale; j < finale; j += passo) {
                labelsList.add(j);
            }
        }
        this.labels = labelsList.toArray();
        this.name = null;
    }

    /**
     * Permette di creare un indice numerico di una data dimensione senza nome.
     * 
     * @param size la dimensione dell'indice.
     * @throws IllegalArgumentException se size è minore o uguale a 0
     */
    public IndexImpl(int size) throws IllegalArgumentException {
        List<Object> labelsList = new ArrayList<>();
        if (size <= 0) {
            throw new IllegalArgumentException("La dimensione dell'indice deve essere maggiore di 0");
        }
        for (int i = 0; i < size; i++) {
            labelsList.add(i);
        }
        this.labels = labelsList.toArray();
        this.name = null;
    }

    @Override
    public int len() {
        return this.labels.length;
    }

    @Override
    public Index indexFusion(Index other) throws NullPointerException {
        Objects.requireNonNull(other, "L'indice da fondere non può essere null");

        if (this.equals(other)) {
            return this;
        }

        List<Object> newLabels = new ArrayList<>(Arrays.asList(this.labels));
        for (Object label : other) {
            if (!newLabels.contains(label)) {
                newLabels.add(label);
            }
        }

        return new IndexImpl(newLabels.toArray(), this.name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int positionOf(Object i) {
        for (int j = 0; j < labels.length; j++) {

            if (String.valueOf(labels[j]).equals(String.valueOf(i))) {
                return j;
            }
        }
        return -1;
    }

    @Override
    public Object labelAt(int i) {
        return labels[i];
    }

    @Override
    public Iterator<Object> iterator() {
        return new ArrayList<>(Arrays.asList(labels)).iterator();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null)
            return false;

        if (!(o instanceof IndexImpl other))
            return false;

        return Arrays.equals(this.labels, other.labels);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(labels);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("index={ name: " + name).append(" , labels: [");
        for (int i = 0; i < labels.length; i++) {
            sb.append(labels[i]);
            if (i < labels.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

}
