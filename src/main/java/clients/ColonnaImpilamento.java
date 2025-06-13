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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import mole.Colonna;
import mole.IndexImpl;
import mole.TablePrinter;
import mole.Interfaces.Column;
import mole.Interfaces.Index;
import utils.InputParsing;
import utils.InputParsing.ColumnDescriptor;
import utils.InputParsing.IndexDescriptor;

public class ColonnaImpilamento {
    public ColonnaImpilamento() {
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {

            while (s.hasNextLine()) {

                ColumnDescriptor cd1 = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                IndexDescriptor indexDescriptor1 = null;
                Index index1 = null;
                Object[] values1;
                Object[] labels1;
                String linea = s.nextLine();
                if (linea.startsWith("#")) {
                    indexDescriptor1 = (IndexDescriptor) InputParsing.parseDescriptor(linea);
                    labels1 = InputParsing.parseValues(s.nextLine(), cd1.rows());

                    index1 = new IndexImpl(labels1, indexDescriptor1.name());
                    values1 = InputParsing.parseValues(s.nextLine(), cd1.rows());
                } else {
                    values1 = InputParsing.parseValues(linea, cd1.rows());
                }

                if (cd1.type() == String.class) {

                    List<String> lista1 = Arrays.asList(Arrays.copyOf(values1, values1.length, String[].class));
                    Column<String> colonna1 = new Colonna<>(cd1.name(), index1, lista1);

                    ColumnDescriptor cd2 = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    IndexDescriptor indexDescriptor2 = null;
                    Index index2 = null;
                    Object[] values2;
                    String linea2 = s.nextLine();
                    if (linea2.startsWith("#")) {
                        indexDescriptor2 = (IndexDescriptor) InputParsing.parseDescriptor(linea2);
                        Object[] labels2 = InputParsing.parseValues(s.nextLine(), cd2.rows());

                        index2 = new IndexImpl(labels2, indexDescriptor2.name());
                        values2 = InputParsing.parseValues(s.nextLine(), cd2.rows());
                    } else {
                        values2 = InputParsing.parseValues(linea2, cd2.rows());
                    }
                    List<String> lista2 = Arrays
                            .asList(Arrays.copyOf(values2, values2.length, String[].class));
                    Column<String> colonna2 = new Colonna<>(cd1.name(), index2, lista2);
                    TablePrinter.printColumn(colonna1.impilaColonna(colonna2));

                } else if (cd1.type() == Boolean.class) {
                    List<Boolean> lista1 = Arrays.asList(Arrays.copyOf(values1, values1.length, Boolean[].class));
                    Column<Boolean> colonna1 = new Colonna<>(cd1.name(), index1, lista1);

                    ColumnDescriptor cd2 = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    IndexDescriptor indexDescriptor2 = null;
                    Index index2 = null;
                    Object[] values2;
                    String linea2 = s.nextLine();
                    if (linea2.startsWith("#")) {
                        indexDescriptor2 = (IndexDescriptor) InputParsing.parseDescriptor(linea2);
                        Object[] labels2 = InputParsing.parseValues(s.nextLine(), cd2.rows());

                        index2 = new IndexImpl(labels2, indexDescriptor2.name());
                        values2 = InputParsing.parseValues(s.nextLine(), cd2.rows());
                    } else {
                        values2 = InputParsing.parseValues(linea2, cd2.rows());
                    }
                    List< Boolean> lista2 = Arrays
                            .asList(Arrays.copyOf(values2, values2.length, Boolean[].class));
                    Column<Boolean> colonna2 = new Colonna<>(cd1.name(), index2, lista2);
                    TablePrinter.printColumn(colonna1.impilaColonna(colonna2));

                } else if (cd1.type() == Integer.class) {
                    List<Integer> lista1 = Arrays.asList(Arrays.copyOf(values1, values1.length, Integer[].class));
                    Column<Integer> colonna1 = new Colonna<>(cd1.name(), index1, lista1);

                    ColumnDescriptor cd2 = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    IndexDescriptor indexDescriptor2 = null;
                    Index index2 = null;
                    Object[] values2;
                    String linea2 = s.nextLine();
                    if (linea2.startsWith("#")) {
                        indexDescriptor2 = (IndexDescriptor) InputParsing.parseDescriptor(linea2);
                        Object[] labels2 = InputParsing.parseValues(s.nextLine(), cd2.rows());

                        index2 = new IndexImpl(labels2, indexDescriptor2.name());
                        values2 = InputParsing.parseValues(s.nextLine(), cd2.rows());
                    } else {
                        values2 = InputParsing.parseValues(linea2, cd2.rows());
                    }
                    List< Integer> lista2 = Arrays
                            .asList(Arrays.copyOf(values2, values2.length, Integer[].class));
                    Column< Integer> colonna2 = new Colonna<>(cd1.name(), index2, lista2);
                    TablePrinter.printColumn(colonna1.impilaColonna(colonna2));

                } else if (cd1.type() == LocalDateTime.class) {
                    List<LocalDateTime> lista = Arrays
                            .asList(Arrays.copyOf(values1, values1.length, LocalDateTime[].class));
                    Column<LocalDateTime> colonna1 = new Colonna<>(cd1.name(), index1, lista);

                    ColumnDescriptor cd2 = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    IndexDescriptor indexDescriptor2 = null;
                    Index index2 = null;
                    Object[] values2;
                    String linea2 = s.nextLine();
                    if (linea2.startsWith("#")) {
                        indexDescriptor2 = (IndexDescriptor) InputParsing.parseDescriptor(linea2);
                        Object[] labels2 = InputParsing.parseValues(s.nextLine(), cd2.rows());

                        index2 = new IndexImpl(labels2, indexDescriptor2.name());
                        values2 = InputParsing.parseValues(s.nextLine(), cd2.rows());
                    } else {
                        values2 = InputParsing.parseValues(linea2, cd2.rows());
                    }
                    List<LocalDateTime> lista2 = Arrays
                            .asList(Arrays.copyOf(values2, values2.length, LocalDateTime[].class));
                    Column<LocalDateTime> colonna2 = new Colonna<>(cd1.name(), index2, lista2);
                    TablePrinter.printColumn(colonna1.impilaColonna(colonna2));

                } else if (cd1.type() == Double.class) {
                    List<Double> lista = Arrays.asList(Arrays.copyOf(values1, values1.length, Double[].class));
                    Column<Double> colonna1 = new Colonna<>(cd1.name(), index1, lista);

                    ColumnDescriptor cd2 = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    IndexDescriptor indexDescriptor2 = null;
                    Index index2 = null;
                    Object[] values2;
                    String linea2 = s.nextLine();
                    if (linea2.startsWith("#")) {
                        indexDescriptor2 = (IndexDescriptor) InputParsing.parseDescriptor(linea2);
                        Object[] labels2 = InputParsing.parseValues(s.nextLine(), cd2.rows());

                        index2 = new IndexImpl(labels2, indexDescriptor2.name());
                        values2 = InputParsing.parseValues(s.nextLine(), cd2.rows());
                    } else {
                        values2 = InputParsing.parseValues(linea2, cd2.rows());
                    }
                    List<Double> lista2 = Arrays
                            .asList(Arrays.copyOf(values2, values2.length, Double[].class));
                    Column<Double> colonna2 = new Colonna<>(cd1.name(), index2, lista2);
                    TablePrinter.printColumn(colonna1.impilaColonna(colonna2));

                } else if (cd1.type() == Number.class) {
                    List<Number> lista = Arrays.asList(Arrays.copyOf(values1, values1.length, Number[].class));
                    Column<Number> colonna1 = new Colonna<>(cd1.name(), index1, lista);

                    ColumnDescriptor cd2 = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    IndexDescriptor indexDescriptor2 = null;
                    Index index2 = null;
                    Object[] values2;
                    String linea2 = s.nextLine();
                    if (linea2.startsWith("#")) {
                        indexDescriptor2 = (IndexDescriptor) InputParsing.parseDescriptor(linea2);
                        Object[] labels2 = InputParsing.parseValues(s.nextLine(), cd2.rows());

                        index2 = new IndexImpl(labels2, indexDescriptor2.name());
                        values2 = InputParsing.parseValues(s.nextLine(), cd2.rows());
                    } else {
                        values2 = InputParsing.parseValues(linea2, cd2.rows());
                    }
                    List< Number> lista2 = Arrays
                            .asList(Arrays.copyOf(values2, values2.length, Number[].class));
                    Column< Number> colonna2 = new Colonna<>(cd1.name(), index2, lista2);
                    TablePrinter.printColumn(colonna1.impilaColonna(colonna2));

                } else {
                    List<Object> lista = Arrays.asList(values1);
                    Column<Object> colonna1 = new Colonna<>(cd1.name(), index1, lista);

                    ColumnDescriptor cd2 = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    IndexDescriptor indexDescriptor2 = null;
                    Index index2 = null;
                    Object[] values2;
                    String linea2 = s.nextLine();

                    if (linea2.startsWith("#")) {
                        indexDescriptor2 = (IndexDescriptor) InputParsing.parseDescriptor(linea2);
                        Object[] labels2 = InputParsing.parseValues(s.nextLine(), cd2.rows());

                        index2 = new IndexImpl(labels2, indexDescriptor2.name());
                        values2 = InputParsing.parseValues(s.nextLine(), cd2.rows());
                    } else {
                        values2 = InputParsing.parseValues(linea2, cd2.rows());
                    }
                    List<Object> lista2 = Arrays.asList(values2);
                    Column<Object> colonna2 = new Colonna<>(cd1.name(), index2, lista2);

                    TablePrinter.printColumn(colonna1.impilaColonna(colonna2));
                }
            }

        }
    }
}