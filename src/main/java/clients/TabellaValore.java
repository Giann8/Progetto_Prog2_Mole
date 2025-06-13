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
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import mole.Colonna;
import mole.IndexImpl;
import mole.Tabella;
import mole.Interfaces.Column;
import mole.Interfaces.Index;
import mole.Interfaces.Table;
import utils.InputParsing;
import utils.InputParsing.ColumnDescriptor;
import utils.InputParsing.IndexDescriptor;
import utils.InputParsing.TableDescriptor;

public class TabellaValore {
    public TabellaValore() {
    }

    public static <V> void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            while (s.hasNextLine()) {

                TableDescriptor td = (TableDescriptor) InputParsing.parseDescriptor(s.nextLine());
                Class<?> tipo = td.type();

                if (tipo == String.class) {
                    List<Column<String>> columns = new ArrayList<>();
                    for (int i = 0; i < td.cols(); i++) {
                        ColumnDescriptor col = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                        Object[] colvalues;
                        Index index = null;
                        String line = s.nextLine();
                        if (line.contains("#")) {
                            IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
                            Object[] labels = InputParsing.parseValues(s.nextLine(), id.len());
                            index = new IndexImpl(labels, id.name());
                            colvalues = InputParsing.parseValues(s.nextLine(), col.rows());
                        } else {
                            colvalues = InputParsing.parseValues(line, col.rows());
                        }
                        List<String> lista = Arrays.asList(Arrays.copyOf(colvalues, colvalues.length, String[].class));
                        columns.add(new Colonna<>(col.name(), index, lista));
                    }
                    Table<String> table = new Tabella<>(columns);

                    System.out.println(table.atLabel(args[0], args[1]));

                } else if (tipo == Double.class) {
                    List<Column<Double>> columns = new ArrayList<>();
                    for (int i = 0; i < td.cols(); i++) {
                        ColumnDescriptor col = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                        Object[] colvalues;
                        Index index = null;
                        String line = s.nextLine();
                        if (line.contains("#")) {
                            IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
                            Object[] labels = InputParsing.parseValues(s.nextLine(), id.len());
                            index = new IndexImpl(labels, id.name());
                            colvalues = InputParsing.parseValues(s.nextLine(), col.rows());
                        } else {
                            colvalues = InputParsing.parseValues(line, col.rows());
                        }
                        List<Double> lista = Arrays.asList(Arrays.copyOf(colvalues, colvalues.length, Double[].class));
                        columns.add(new Colonna<>(col.name(), index, lista));
                    }
                    Table<Double> table = new Tabella<>(columns);
                    System.out.println(table.atLabel(args[0], args[1]));

                } else if (tipo == Integer.class) {
                    List<Column<Integer>> columns = new ArrayList<>();
                    for (int i = 0; i < td.cols(); i++) {
                        ColumnDescriptor col = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                        Object[] colvalues;
                        Index index = null;
                        String line = s.nextLine();
                        if (line.contains("#")) {
                            IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
                            Object[] labels = InputParsing.parseValues(s.nextLine(), id.len());
                            index = new IndexImpl(labels, id.name());
                            colvalues = InputParsing.parseValues(s.nextLine(), col.rows());
                        } else {
                            colvalues = InputParsing.parseValues(line, col.rows());
                        }
                        List<Integer> lista = Arrays
                                .asList(Arrays.copyOf(colvalues, colvalues.length, Integer[].class));
                        columns.add(new Colonna<>(col.name(), index, lista));
                    }
                    Table<Integer> table = new Tabella<>(columns);
                    System.out.println(table.atLabel(args[0], args[1]));

                } else if (tipo == Date.class) {
                    List<Column<Date>> columns = new ArrayList<>();
                    for (int i = 0; i < td.cols(); i++) {
                        ColumnDescriptor col = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                        Object[] colvalues;
                        Index index = null;
                        String line = s.nextLine();
                        if (line.contains("#")) {
                            IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
                            Object[] labels = InputParsing.parseValues(s.nextLine(), id.len());
                            index = new IndexImpl(labels, id.name());
                            colvalues = InputParsing.parseValues(s.nextLine(), col.rows());
                        } else {
                            colvalues = InputParsing.parseValues(line, col.rows());
                        }
                        List<Date> lista = Arrays.asList(Arrays.copyOf(colvalues, colvalues.length, Date[].class));
                        columns.add(new Colonna<>(col.name(), index, lista));
                    }
                    Table<Date> table = new Tabella<>(columns);
                    System.out.println(table.atLabel(args[0], args[1]));

                } else {
                    // fallback generico
                    List<Column<Object>> columns = new ArrayList<>();
                    for (int i = 0; i < td.cols(); i++) {
                        ColumnDescriptor col = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                        Object[] colvalues;
                        Index index = null;
                        String line = s.nextLine();
                        if (line.contains("#")) {
                            IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
                            Object[] labels = InputParsing.parseValues(s.nextLine(), id.len());
                            index = new IndexImpl(labels, id.name());
                            colvalues = InputParsing.parseValues(s.nextLine(), col.rows());
                        } else {
                            colvalues = InputParsing.parseValues(line, col.rows());
                        }
                        List<Object> lista = Arrays.asList(colvalues);
                        columns.add(new Colonna<>(col.name(), index, lista));
                    }
                    Table<Object> table = new Tabella<>(columns);
                    System.out.println(table.atLabel(args[0], args[1]));

                }
            }
        }
    }
}
