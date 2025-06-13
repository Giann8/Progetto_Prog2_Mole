package mole.Interfaces;

import java.util.function.Function;

/**
 * Un'interfaccia che permette di implementare le funzionalità principali per la
 * manipolazione e l'utilizzo di tabelle
 * 
 * @param <V> il tipo della tabella
 */
public interface Table<V> extends Iterable<Column<V>> {
    /**
     * Restituisce la colonna presente alla posizione data.
     * 
     * @param j la posizione della colonna
     * @return se presente la colonna alla posizione specificata altrimenti null;
     * @throws IndexOutOfBoundsException se {@code j < 0 || j >= size()};
     */
    Column<V> columnAtPosition(int j) throws IndexOutOfBoundsException;

    /**
     * Restituisce la colonna associata all'etichetta specificata.
     * 
     * @param j il nome della colonna
     * @return se presente la colonna associata all'etichetta specificata altrimenti
     *         {@code null};
     * @throws NullPointerException se j è {@code null};
     */
    Column<V> columnAtLabel(String j) throws NullPointerException;

    /**
     * Restituisce il valore alla posizione definita agli indici di poszione di riga
     * e di colonna
     * 
     * @param i l'indice della riga
     * @param j l'indice della colonna
     * @return il valore alla posizione specificata nella colonna con etichetta j
     *         oppure null se la colonna non esiste o se l'indice i non è presente
     *         nell'indice della colonna.
     * @throws IndexOutOfBoundsException se {@code i < 0 || i >= getIndex().len()}
     * @throws NullPointerException      se j è null
     */
    V atPosition(int i, int j) throws IndexOutOfBoundsException, NullPointerException;

    /**
     * Restituisce il valore alla posizione definita dalle etichetta di riga e di
     * colonna.
     * 
     * @param i l'etichetta dell'indice
     * @param j l'etichetta della colonna
     * @return il valore alla posizione specificata nella colonna con etichetta j
     *         oppure null se la colonna non esiste o se l'etichetta i non è
     *         presente
     *         nell'indice della colonna.
     * @throws NullPointerException se i o j sono null
     */
    V atLabel(Object i, String j) throws NullPointerException;

    /**
     * Restituisce l'indice della tabella.
     * 
     * @return l'indice della tabella
     */
    Index getIndex();

    /**
     * Restituisce il numero di colonne nella tabella.
     * 
     * @return il numero di colonne
     */
    int getNumberOfColumns();

    /**
     * Reindicizza la tabella con un nuovo indice.
     * 
     * @param index il nuovo indice
     * @return una nuova tabella con l'indice aggiornato
     * @throws NullPointerException se l'indice è null
     */
    Table<V> reIndexTable(Index index) throws NullPointerException;

    /**
     * Restituisce una copia di questa tabella con le colonne rinominate.
     * 
     * @param newLabels un array di stringhe contenente i nuovi nomi delle colonne.
     * @return una nuova tabella con le colonne rinominate.
     * @throws NullPointerException     se newLabels è null
     * @throws IllegalArgumentException se newLabels ha una lunghezza diversa dal
     *                                  numero di colonne della tabella.
     */
    Table<V> changeColumnLabels(String[] newLabels) throws NullPointerException, IllegalArgumentException;

    /**
     * Affianca la tabella con un'altra tabella.
     * 
     * <p>
     * Restituisce una nuova tabella avente come indice la fusione degli indici
     * delle due tabelle e come colonne le colonne reindicizzate in caso fossero.
     * </p>
     * 
     * @param tabella2 la tabella da affiancare
     * @return una nuova tabella con le colonne affiancate
     * @throws NullPointerException se la tabella da affiancare è null
     */
    Table<V> affiancaTabella(Table<V> tabella2) throws NullPointerException;

    /**
     * Impila la tabella con un'altra tabella.
     * 
     * <p>
     * Restituisce una nuova tabella avente come indice l'unione degli indici delle
     * due tabelle e come colonne le colonne reindicizzate in caso fossero.
     * </p>
     * 
     * @param tabella2 la tabella da impilare
     * @return una nuova tabella con le colonne impilate
     * @throws NullPointerException se la tabella da impilare è null
     */
    Table<V> impilaTabella(Table<V> tabella2) throws NullPointerException;

    /**
     * Applica una funzione {@code f} a tutte le colonne della tabella e restituisce
     * una nuova tabella con le colonne modificate.
     * 
     * @param f la funzione da applicare a ogni colonna della tabella
     * @return una nuova tabella con le colonne modificate
     * @throws NullPointerException se la funzione {@code f} è null
     */
    Table<V> map(Function<Column<V>, Column<V>> f) throws NullPointerException;
}
