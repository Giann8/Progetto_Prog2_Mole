/*

Copyright 2024 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package clients;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import mole.Colonna;
import mole.IndexImpl;
import mole.TablePrinter;
import mole.Interfaces.Column;
import mole.trasformazioni.MinutiOre;
import utils.InputParsing;
import utils.InputParsing.ColumnDescriptor;
import utils.InputParsing.IndexDescriptor;

public class ColonnaOrario {
    public ColonnaOrario() {
        // Costruttore vuoto
    }

    public static <V, U> void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            while (s.hasNextLine()) {

                ColumnDescriptor columnDescriptor = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                IndexDescriptor indexDescriptor = null;
                IndexImpl index = null;
                Object[] values;
                Object[] labels;
                String linea = s.nextLine();

                if (linea.startsWith("#")) {
                    indexDescriptor = (IndexDescriptor) InputParsing.parseDescriptor(linea);
                    labels = InputParsing.parseValues(s.nextLine(), columnDescriptor.rows());

                    index = new IndexImpl(labels, indexDescriptor.name());
                    values = InputParsing.parseValues(s.nextLine(), columnDescriptor.rows());
                } else {
                    values = InputParsing.parseValues(linea, columnDescriptor.rows());
                }

                Class<?> tipo = columnDescriptor.type();

                if (tipo == String.class) {
                    Colonna<String> colonna;
                    List<String> lista = Arrays.asList(Arrays.copyOf(values, values.length, String[].class));
                    colonna = new Colonna<>(columnDescriptor.name(), index, lista);
                    TablePrinter.printColumn(colonna);

                } else if (tipo == Boolean.class) {
                    Colonna<Boolean> colonna;
                    List<Boolean> lista = Arrays.asList(Arrays.copyOf(values, values.length, Boolean[].class));
                    colonna = new Colonna<>(columnDescriptor.name(), index, lista);
                    TablePrinter.printColumn(colonna);

                } else if (tipo == Integer.class) {
                    Colonna<Integer> colonna;
                    List<Integer> lista = Arrays.asList(Arrays.copyOf(values, values.length, Integer[].class));
                    colonna = new Colonna<>(columnDescriptor.name(), index, lista);
                    TablePrinter.printColumn(colonna);

                } else if (tipo == LocalDateTime.class) {
                    Colonna<LocalDateTime> colonna;
                    List<LocalDateTime> lista = Arrays
                            .asList(Arrays.copyOf(values, values.length, LocalDateTime[].class));
                    colonna = new Colonna<LocalDateTime>(columnDescriptor.name(), index, lista);
                    
                    Function<LocalDateTime, LocalTime> trasformazione = new MinutiOre();
                    TablePrinter.printColumn(colonna.map(trasformazione));

                } else {

                    Column<Object> colonna = new Colonna<>(columnDescriptor.name(), index, Arrays.asList(values));
                    TablePrinter.printColumn(colonna);

                }
            }

        } catch (Exception e) {
            System.err.println("Si è verificato un errore: " + e.getMessage());
        }

    }
}
