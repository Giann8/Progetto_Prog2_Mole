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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import mole.Colonna;
import mole.TablePrinter;
import mole.Interfaces.Column;
import utils.InputParsing;
import utils.InputParsing.ColumnDescriptor;

public class ColonnaNome {

    public ColonnaNome() {
        // Costruttore vuoto
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            while (s.hasNextLine()) {

                ColumnDescriptor columnDescriptor = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());

                Object[] values = InputParsing.parseValues(s.nextLine(), columnDescriptor.rows());

                Class<?> tipo = columnDescriptor.type();
                Column<?> colonna;

                if (tipo == String.class) {

                    List<String> lista = Arrays.asList(Arrays.copyOf(values, values.length, String[].class));
                    colonna = new Colonna<>(columnDescriptor.name(), null, lista);

                } else if (tipo == Double.class) {

                    List<Boolean> lista = Arrays.asList(Arrays.copyOf(values, values.length, Boolean[].class));
                    colonna = new Colonna<>(columnDescriptor.name(), null, lista);

                } else if (tipo == Integer.class) {

                    List<Integer> lista = Arrays.asList(Arrays.copyOf(values, values.length, Integer[].class));
                    colonna = new Colonna<>(columnDescriptor.name(), null, lista);

                } else if (tipo == Date.class) {
                    List<Date> lista = Arrays.asList(Arrays.copyOf(values, values.length, Date[].class));
                    colonna = new Colonna<>(columnDescriptor.name(), null, lista);

                } else {

                    colonna = new Colonna<>(columnDescriptor.name(), null, Arrays.asList(values));
                }

                colonna = colonna.renameColumn(args[0]);

                TablePrinter.printColumn(colonna);
            }

        } catch (Exception e) {
            System.err.println("Si è verificato un errore: " + e.getMessage());
        }

    }
}
