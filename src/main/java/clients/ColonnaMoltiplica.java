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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import mole.Colonna;
import mole.IndexImpl;
import mole.TablePrinter;
import mole.trasformazioni.Moltiplica;
import utils.InputParsing;
import utils.InputParsing.ColumnDescriptor;
import utils.InputParsing.IndexDescriptor;

public class ColonnaMoltiplica {
    public ColonnaMoltiplica() {
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            while (s.hasNextLine()) {

                ColumnDescriptor cd = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                IndexDescriptor indexDescriptor = null;
                IndexImpl index = null;

                String linea = s.nextLine();
                indexDescriptor = (IndexDescriptor) InputParsing.parseDescriptor(linea);
                if (indexDescriptor != null) {
                    Object[] labels = InputParsing.parseValues(s.nextLine(), cd.rows());
                    index = new IndexImpl(labels, indexDescriptor.name());
                    linea = s.nextLine();
                }
                Object[] values = InputParsing.parseValues(linea, cd.rows());

                Class<?> tipo = cd.type();

                if (tipo == Number.class) {
                    Colonna<Number> colonna;
                    List<Number> lista = new ArrayList<>();

                    for (Object v : values) {
                        lista.add((Number) v);
                    }
                    colonna = new Colonna<>(cd.name(), index, lista);
                    TablePrinter.printColumn(colonna);

                } else if (tipo == Double.class) {
                    Colonna<Double> colonna;
                    List<Double> lista = new ArrayList<>();

                    for (Object v : values) {
                        lista.add((Double) v);
                    }
                    colonna = new Colonna<>(cd.name(), index, lista);
                    TablePrinter.printColumn(colonna);

                } else if (tipo == Integer.class) {
                    Colonna<Integer> colonna;
                    List<Integer> lista = Arrays.asList(Arrays.copyOf(values, values.length, Integer[].class));
                    colonna = new Colonna<>(cd.name(), index, lista);
                    Function<Integer, Integer> moltiplicatore = new Moltiplica(Integer.parseInt(args[0]));
                    TablePrinter.printColumn(colonna.map(moltiplicatore));

                }
            }

        } catch (Exception e) {
            System.err.println("Si è verificato un errore: " + e.getMessage());
        }

    }
}
