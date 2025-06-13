package mole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mole.Interfaces.Column;
import mole.Interfaces.Index;
import mole.Interfaces.Table;

/**
 * Classe di utilità per stampare in formato leggibile le tabelle e i loro
 * elementi (colonne e indici).
 * <p>
 * La classe fornisce metodi statici che permettono di stampare in un formato
 * tabellare leggibile oggetti
 * che implementano le interfacce {@link Index}, {@link Column} e {@link Table}.
 * </p>
 * 
 * <h2>Funzioni fornite</h2>
 * <ul>
 * <li>{@link TablePrinter#printIndex} riceve un {@link Index} e lo stampa in un
 * formato tabellare mostrando nome ed etichette.</li>
 * <li>{@link TablePrinter#printColumn} riceve un {@link Column} e lo stampa in
 * un formato tabellare mostrando l'{@code Indice} ad esso associato e poi la
 * colonna alla sua destra associando i valori alle rispettive righe.</li>
 * <li>{@link TablePrinter#printTable} riceve un {@link Table} permette di
 * stampare una {@link Table} in un formato tabellare simile a
 * {@code printColumn()} ma aggiungendo tutte le colonne da cui è formata la
 * tabella.</li>
 * </ul>
 */
public class TablePrinter {

    /**
     * Costruttore vuoto.
     * <p>
     * Inizializza un'istanza della classe
     * </p>
     */
    public TablePrinter() {
    }

    /**
     * Stampa la rappresentazione tabellare di un {@link Index} in un formato
     * leggibile.
     *
     * @param index l'indice da rappresentare
     * @return una stringa che rappresenta l'indice in un formato leggibile.
     * @throws NullPointerException se {@code index} è {@code null}.
     */
    public static String printIndex(Index index) throws NullPointerException {
        Objects.requireNonNull(index, "L'indice non può essere null");
        StringBuilder sb = new StringBuilder();
        int length = 0;

        String name = index.getName();

        if (name != null) {
            length = name.length();
            sb.append(name).append("\n");
        } else {
            sb.append("\n");
        }

        for (Object label : index) {
            String l = label.toString();
            if (l.length() > length) {
                length = l.length();
            }
        }

        sb.append("-".repeat(length)).append("\n");

        for (Object label : index) {
            sb.append(String.format("%" + length + "s", label.toString())).append("\n");
        }

        return sb.toString();
    }

    /**
     * Stampa una {@link Column} in un formato tabellare leggibile.
     * <p>
     * La colonna viene stampata con l'indice a sinistra, seguito dal nome della
     * colonna e dai valori della colonna.
     * Eventuali valori null vengono convertiti in {@code ""}
     * </p>
     * 
     * @param colonna la colonna da stampare
     * @return una stringa che rappresenta la colonna in un formato leggibile.
     * @throws NullPointerException se {@code colonna} è {@code null}.
     */
    public static String printColumn(Column<?> colonna) throws NullPointerException {
        Objects.requireNonNull(colonna, "La colonna non può essere null");
        Index index = colonna.getIndex();
        String name = colonna.getName();
        String indexName = index.getName();

        int idxWidth = 0;
        int valWidth = 0;

        if (name != null && !name.equals("null")) {
            valWidth = name.length();
        } else {
            name = "";
            valWidth = 0;
        }

        if (indexName != null && !indexName.equals("null")) {
            idxWidth = indexName.length();
        } else {
            indexName = "";
            idxWidth = 0;
        }

        for (int i = 0; i < colonna.size(); i++) {
            String idxStr = index.labelAt(i).toString();

            if (idxStr.length() > idxWidth)
                idxWidth = idxStr.length();
            Object val = colonna.atPosition(i);
            if (val == null) {

                val = "";
            }
            String valStr = val.toString();
            if (valStr.length() > valWidth)
                valWidth = valStr.length();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%" + idxWidth + "s | %s\n", indexName, name));
        sb.append("-".repeat(idxWidth)).append("-+-").append("-".repeat(valWidth)).append("\n");

        for (int i = 0; i < colonna.size(); i++) {
            Object val = colonna.atPosition(i);
            if (val == null) {
                val = "";
            }
            String idxStr = String.format("%" + idxWidth + "s", index.labelAt(i).toString());
            String valStr = String.format("%-" + valWidth + "s", val.toString());
            sb.append(idxStr).append(" | ").append(valStr).append("\n");
        }

       return sb.toString();

    }

    /**
     * Stampa una rappresentazione tabellare di {@link Table} in un formato
     * leggibile.
     * <p>
     * La tabella viene stampata con l'indice a sinistra, seguito da ogni colonna
     * della tabella.
     * Eventuali valori null vengono convertiti in {@code ""}
     * </p>
     *
     * @param table la tabella da stampare
     * @return una stringa che rappresenta la tabella in un formato leggibile.
     * @throws NullPointerException se {@code table} è {@code null}.
     */
    public static String printTable(Table<?> table) throws NullPointerException {
        Objects.requireNonNull(table, "La tabella non può essere null");
        Index index = table.getIndex();
        var idxWidth = 0;
        var ncol = table.getNumberOfColumns();

        List<Integer> colWidths = new ArrayList<>();

        if (index.getName() != null) {
            idxWidth = index.getName().length();
        }

        for (Object label : index) {
            String labelStr = label.toString();
            if (labelStr.length() > idxWidth) {
                idxWidth = labelStr.length();
            }
        }

        for (Column<?> colonna : table) {
            String colName = colonna.getName();
            int valWidth = 0;
            if (colName.length() > valWidth) {
                valWidth = colName.length();
            }
            for (int i = 0; i < colonna.size(); i++) {
                Object val = colonna.atPosition(i);
                if (val == null) {
                    val = "";
                }
                String valStr = val.toString();
                if (valStr.length() > valWidth) {
                    valWidth = valStr.length();
                }
            }
            colWidths.add(valWidth);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%" + idxWidth + "s | ", index.getName() != null ? index.getName() : ""));

        int c = 0;
        for (Column<?> colonna : table) {
            sb.append(String.format("%-" + (colWidths.get(c)) + "s ", colonna.getName()));
            c++;
            if (c < ncol) {
                sb.append("| ");
            }
        }

        sb.append("\n");
        sb.append("-".repeat(idxWidth));
        for (int i = 0; i < ncol; i++) {
            sb.append("-+-").append("-".repeat(colWidths.get(i)));
        }

        sb.append("\n");

        for (Object label : index) {
            sb.append(String.format("%" + idxWidth + "s | ", label.toString()));
            int colCount = 0;
            for (Column<?> colonna : table) {
                Object val = colonna.atLabel(label);
                if (val == null) {
                    val = "";
                }
                sb.append(String.format("%-" + (colWidths.get(colCount)) + "s ", val.toString()));
                colCount++;
                if (colCount < ncol) {
                    sb.append("| ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
