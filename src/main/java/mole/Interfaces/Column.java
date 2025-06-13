package mole.Interfaces;

import java.util.function.Function;

/**
 * Interfaccia che rappresenta una colonna di tipo generico.
 * 
 * @param <V> il tipo dei valori della colonna
 */
public interface Column<V> extends Iterable<V> {

    /**
     * Restituisce il valore alla posizione specificata.
     * 
     * @param i la posizione del valore
     * @return il valore alla posizione specificata
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti della colonna
     */
    V atPosition(int i) throws IndexOutOfBoundsException;

    /**
     * Restituisce il valore associato all'etichetta specificata.
     * 
     * @param o l'etichetta del valore
     * @return il valore associato all'etichetta specificata. 
     * @throws NullPointerException se l'etichetta data {@code o} è null;
     */
    V atLabel(Object o) throws NullPointerException;

    /**
     * Restituisce l'indice associato alla colonna.
     * 
     * @return l'indice della colonna
     */
    Index getIndex();

    /**
     * Restituisce il nome della colonna.
     * 
     * @return il nome della colonna
     */
    String getName();

    /**
     * Restituisce la dimensione della colonna.
     * 
     * @return la dimensione della colonna
     */
    int size();

    /**
     * Crea una copia della colonna rinominata.
     * <p> Crea una copia della colonna con un nuovo nome mantenendo i valori e i label dell'indice invariati.</p>
     * 
     * @param newName il nuovo nome della colonna
     * @return una nuova colonna con il nome aggiornato
     */
    Column<V> renameColumn(String newName);

    /**
     * Reindicizza la colonna.
     * 
     * <p>
     * Crea una copia della colonna con l'indice modificato riassociando ogni valore
     * al label di riga a cui
     * era associato, se {@code newIndex} contiene labels diversi dall'{@code index}
     * corrente allora ad essi verranno associati dei valori {@code Null} mentre i
     * valori cui label non è presente verranno ignorati.
     * </p>
     * 
     * @param newIndex il nuovo indice
     * @return una nuova colonna con l'indice aggiornato
     * @throws NullPointerException se il nuovo indice è null.
     */
    Column<V> reindexColumn(Index newIndex) throws NullPointerException;

    /**
     * Cambia l'indice della colonna.
     * 
     * <p>
     * Crea una copia della colonna con un nuovo indice mantenendo i valori di
     * {@code this} e le loro posizioni invariati. Inoltre se
     * {@code newIndex.len() < this.index} allora alla colonna viene assegnato un
     * indice numerico della dimensione corretta.
     * </p>
     * 
     * @param newIndex il nuovo indice
     * @return una nuova colonna con l'indice aggiornato
     * @throws NullPointerException se il nuovo indice è null.
     */
    Column<V> cambiaIndice(Index newIndex) throws NullPointerException;

    /**
     * Crea una nuova colonna impilando questa colonna con un'altra colonna.
     * 
     * @param colonna la colonna da impilare con questa colonna
     * @return una nuova colonna con i valori impilati
     * @throws NullPointerException se la colonna da impilare è null
     */
    Column<V> impilaColonna(Column<V> colonna) throws NullPointerException;

    /**
     * Permette di mappare i valori della colonna con una funzione.
     * <p>
     * Applica la funzione {@code f} a ogni valore della colonna e restituisce una
     * nuova colonna con i valori risultanti.
     * </p>
     * 
     * @param <U> il tipo di ritorno della funzione
     * @param f   la funzione da applicare ai valori della colonna
     * @return una nuova colonna con i valori mappati
     * @throws NullPointerException se la funzione è null
     */
    <U> Column<U> map(Function<V, U> f) throws NullPointerException;
}
