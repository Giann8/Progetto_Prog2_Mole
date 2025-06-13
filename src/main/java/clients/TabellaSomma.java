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


import mole.Colonna;
import mole.IndexImpl;
import mole.Tabella;
import mole.TablePrinter;
import mole.Interfaces.Column;
import mole.Interfaces.Index;
import mole.Interfaces.Table;
import mole.trasformazioni.SommaTable;
import utils.InputParsing;
import utils.InputParsing.ColumnDescriptor;
import utils.InputParsing.IndexDescriptor;
import utils.InputParsing.TableDescriptor;

public class TabellaSomma {
    public TabellaSomma(){}

    public static void main(String[] args) {
                try (Scanner s = new Scanner(System.in)) {
            while (s.hasNextLine()) {
                TableDescriptor td = (TableDescriptor) InputParsing.parseDescriptor(s.nextLine());

                Class<?> tipo = td.type();
                if (tipo == Integer.class) {
                    List<Column<Integer>> columns = new ArrayList<>();
                    for (int i = 0; i < td.cols(); i++) {
                        ColumnDescriptor cd = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());

                        Index index = null;
                        String line = s.nextLine();
                        IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
                        if (id != null) {
                            Object[] labels = InputParsing.parseValues(s.nextLine(), id.len());
                            line = s.nextLine();
                            index = new IndexImpl(labels, id.name());
                        }
                        Object[] values = InputParsing.parseValues(line, cd.rows());
                        List<Integer> lista = Arrays
                                .asList(Arrays.copyOf(values, values.length, Integer[].class));
                        columns.add(new Colonna<>(cd.name(), index, lista));
                    }
                    Table<Integer> table = new Tabella<>(columns);
                    TablePrinter.printTable(table.map(new SommaTable()));
                }   
            }

        }
    }
}
