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
import utils.InputParsing;
import utils.InputParsing.ColumnDescriptor;
import utils.InputParsing.IndexDescriptor;
import utils.InputParsing.TableDescriptor;

public class TabellaAffiancamento {
    public TabellaAffiancamento() {
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            Table<Object> t1 = null;
            Table<Object> t2 = null;
            while (s.hasNextLine()) {

                TableDescriptor td = (TableDescriptor) InputParsing.parseDescriptor(s.nextLine());
                List<Column<Object>> colonne = new ArrayList<>();
                for (int i = 0; i < td.cols(); i++) {
                    ColumnDescriptor col = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    // Class<?> tipo = col.type();
                    Index index = null;
                    String line = s.nextLine();
                    IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
                    if (id != null) {
                        Object[] labels = InputParsing.parseValues(s.nextLine(), id.len());
                        line = s.nextLine();
                        index = new IndexImpl(labels, id.name());
                    }
                    Object[] values = InputParsing.parseValues(line, col.rows());
                    // if (tipo == String.class) {
                    // List<String> list = new ArrayList<>(Arrays.asList((String[]) values));

                    // Column<String> c = new Colonna<>(col.name(), index, list);

                    // colonne.add(c);

                    // } else if (tipo == Integer.class) {
                    // List<Integer> list = new ArrayList<>(
                    // Arrays.asList(Arrays.copyOf(values, values.length, Integer[].class)));

                    // Column<Integer> c = new Colonna<>(col.name(), index, list);

                    // colonne.add(c);
                    // } else {
                    Column<Object> c = new Colonna<Object>(col.name(), index, Arrays.asList(values));
                    colonne.add(c);
                    // }
                }
                if (t1 == null) {
                    t1 = new Tabella<>(colonne);
                } else {
                    t2 = new Tabella<>(colonne);
                }

            }

            TablePrinter.printTable(t1.affiancaTabella(t2));
        }
    }
}
