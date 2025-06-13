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
import java.util.Scanner;

import mole.Colonna;
import mole.IndexImpl;
import mole.TablePrinter;
import mole.Interfaces.Column;
import mole.Interfaces.Index;
import utils.InputParsing;
import utils.InputParsing.ColumnDescriptor;
import utils.InputParsing.IndexDescriptor;

public class ColonnaIndice {
    public ColonnaIndice() {
    }

    public static <V> void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            while (s.hasNextLine()) {
                ColumnDescriptor cd = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
                String line = s.nextLine();
                IndexDescriptor id1 = (IndexDescriptor) InputParsing.parseDescriptor(line);
                Object[] labels = null;
                Object[] colValues = null;
                Index index = null;

                if (id1 != null) {
                    labels = InputParsing.parseValues(s.nextLine(), id1.len());
                    colValues = InputParsing.parseValues(s.nextLine(), cd.rows());
                    index =  new IndexImpl(labels, id1.name());
                }else{
                    colValues = InputParsing.parseValues(line, cd.rows());
                }

                Column<?> column = new Colonna<>(cd.name(), index, Arrays.asList(colValues));

                Index newIndex = null;

                if (s.hasNextLine()) {
                    IndexDescriptor id2 = (IndexDescriptor) InputParsing.parseDescriptor(s.nextLine());
                    Object[] labels2 = InputParsing.parseValues(s.nextLine(), id2.len());
                    newIndex = new IndexImpl(labels2, id2.name());
                }

                Column<?> newcolumn = column.cambiaIndice(newIndex);
                TablePrinter.printColumn(newcolumn);
            }
        }
    }
}

