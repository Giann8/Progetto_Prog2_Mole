package mole.Interfaces;

/**
 * Interfaccia per l'implementazione dei metodi utilizzabili da un'indice di tabelle e colonne.
 * <p> Un indice è una sequenza di etichette che possono essere utilizzate per
 * identificare le posizioni in una tabella o in una colonna.</p>
 */
public interface Index extends Iterable<Object> {

    /**
     * Restituisce il nome dell'indice.
     * @return il nome dell'indice o null se non è stato specificato.
     */
    String getName();

    /**
     * Restituisce la posizione dell'etichetta data.
     * @param i l'etichetta.
     * @return  il label alla posizione specificata o -1 se non è presente in questo indice.
     */
    int positionOf(Object i) throws NullPointerException;


    /**
     * Restituisce l'etichetta alla posizione specificata.
     * @param i la posizione dell'etichetta.
     * @return l'etichetta alla posizione specificata.
     * @throws IndexOutOfBoundsException se {@code i < 0 || i >= len()}.
     */
    Object labelAt(int i);


    /**
     * Restituisce la lunghezza dell'indice, cioè il numero di etichette che lo
     * compongono.
     * 
     * @return la lunghezza dell'indice
     */
    int len();

    /**
     * Crea un nuovo indice dato dalla fusione di {@code this}
     * con un altro {@link Index} mantenendo il nome dell'indice corrente.
     * 
     * @param other l'indice da fondere con questo
     * @return un nuovo indice che contiene tutte le etichette di entrambi gli
     *         indici senza duplicati se {@code other} presenta etichette diverse da
     *         {@code this} altrimenti una copia di questo indice se {@code other.equals(this) == True}  .
     * @throws NullPointerException se l'indice da fondere è null
     */
    Index indexFusion(Index other) throws NullPointerException;
    
    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
