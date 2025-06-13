package mole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import mole.Interfaces.Column;
import mole.Interfaces.Index;

/**
 * Una classe rappresentante una colonna che implementa {@link Column}.
 * <p>
 * La colonna è immutabile e per mantenere tale stato deve essere fornita una
 * lista values contenente solo oggetti immutabili.
 * </p>
 * 
 * @param <V> il tipo dei valori nella colonna
 */
public class Colonna<V> implements Column<V> {
  /** Il nome della colonna */
  private final String name;
  /** L'indice associato alla colonna */
  private final Index index;
  /** I valori della colonna */
  private final List<V> values;

  /*
   * 
   * - IR:
   * - la lista values non può essere null o vuota
   * - index non può essere null
   * - values.size() deve essere uguale a index.len()
   * Più formalmente: values != null && values.size()==index.size() e > 0 && index
   * != null inoltre per ogni i in [0, values.size()) values.get(i) ->
   * index.get(i)
   * 
   * - AF:
   * AF(name, index, values) = Una colonna è data da un name, un index e dai
   * valori presenti in values.
   * I valori dell'index e di values sono associati tramite la loro posizione.
   * 
   */

  /**
   * Costruttore della colonna.
   * <p>
   * Permette di creare una colonna con un nome, un indice e una lista di valori.
   * Se l'indice è {@code null}, viene creato un nuovo indice numerico con valori
   * da 0 a values.size() - 1.
   * </p>
   *
   * @param name   il nome della colonna.
   * @param index  the index associated with the column or null
   * @param values the values in the column
   * 
   * 
   * @throws IllegalArgumentException se {@code values} è {@code null} o è vuoto.
   */
  public Colonna(String name, Index index, List<V> values) throws IllegalArgumentException {
    if (values == null || values.isEmpty()) {
      throw new IllegalArgumentException("Values non può essere null o vuoto");
    }
    if (index != null && (index.len() != values.size())) {
      throw new IllegalArgumentException("Index e values devono avere la stessa dimensione");
    }

    if (index == null) {
      this.index = new IndexImpl(values.size());
    } else {
      this.index = index;
    }

    if (name == null) {
      this.name = "";
    } else {
      this.name = name;
    }

    this.values = values;
  }

  @Override
  public Column<V> reindexColumn(Index newIndex) throws NullPointerException {
    Objects.requireNonNull(newIndex);

    List<V> newValues = new ArrayList<>();

    for (Object label : newIndex) {
      int labelPos = this.index.positionOf(label);
      if (labelPos < 0) {
        newValues.add(null);
      } else {
        newValues.add(this.values.get(labelPos));
      }
    }

    return new Colonna<>(this.name, newIndex, newValues);
  }

  @Override
  public Column<V> impilaColonna(Column<V> colonna2) throws NullPointerException {
    Objects.requireNonNull(colonna2, "La colonna da impilare non può essere null");

    boolean similarIndex = false;

    Index newIndex = this.index;
    Index fi = colonna2.getIndex();

    for (Object label : colonna2.getIndex()) {
      if (this.index.positionOf(label) >= 0) {
        similarIndex = true;
        break;
      }
    }

    if (similarIndex) {
      fi = new IndexImpl(this.size(), 1, this.size() * 2);
    }

    newIndex = this.index.indexFusion(fi);

    List<V> allValues = new ArrayList<>(this.values);

    for (V value : colonna2) {
      allValues.add(value);
    }
    return new Colonna<>(this.name, newIndex, allValues);
  }

  @Override
  public <T> Column<T> map(Function<V, T> trasformazione) throws NullPointerException {
    Objects.requireNonNull(trasformazione);

    List<T> transformedValues = new ArrayList<>();
    for (V value : values) {
      transformedValues.add(trasformazione.apply(value));
    }

    return new Colonna<>(this.name, this.index, transformedValues);
  }

  @Override
  public Column<V> cambiaIndice(Index newIndex) throws NullPointerException {
    Objects.requireNonNull(newIndex, "Il nuovo indice non può essere null");
    if (newIndex.len() < this.index.len()) {
      newIndex = new IndexImpl(this.size());
    }
    return new Colonna<>(this.name, newIndex, this.values);
  }

  @Override
  public Column<V> renameColumn(String newName) {
    return new Colonna<>(newName, this.index, this.values);
  }

  @Override
  public Index getIndex() {
    return index;
  }

  @Override
  public Iterator<V> iterator() {

    return new ArrayList<>(values).iterator();
  }

  @Override
  public V atPosition(int i) throws IndexOutOfBoundsException {
    if (i < 0 || i >= values.size()) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }

    return values.get(i);
  }

  @Override
  public V atLabel(Object o) throws NullPointerException, IllegalArgumentException {
    if (o == null) {
      throw new NullPointerException("Il label non può essere null");
    }

    int labelPos = index.positionOf(o);

    if (labelPos < 0) {
      throw new IllegalArgumentException("il label insserito non è presente nell'indice della colonna");
    }

    return this.values.get(labelPos);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int size() {
    return values.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Colonna={");
    sb.append("name: '").append(name != null ? name : "Nessun nome assegnato").append('\'');
    sb.append(", ").append(index);
    sb.append(", values: {").append(values);
    sb.append("}}");
    return sb.toString();
  }
}
