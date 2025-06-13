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

public class ColonnaReindicizzazione {
    public ColonnaReindicizzazione() {
    }

    public static <V> void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            Object[] values;
            Object[] labels = null;
            ColumnDescriptor cd = (ColumnDescriptor) InputParsing.parseDescriptor(s.nextLine());
            String line = s.nextLine();
            IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
            Index index = null;
            if (id == null) {
                values = InputParsing.parseValues(line, cd.rows());
            } else {
                labels = InputParsing.parseValues(s.nextLine(), id.len());
                values = InputParsing.parseValues(s.nextLine(), cd.rows());
                index = new IndexImpl(labels, id.name());
            }
            Column<?> column = new Colonna<>(cd.name(), index, Arrays.asList(values));

            IndexDescriptor newIndexDescriptor = (IndexDescriptor) InputParsing.parseDescriptor(s.nextLine());
            Object[] newLabels = InputParsing.parseValues(s.nextLine(), newIndexDescriptor.len());
            Index newIndex = new IndexImpl(newLabels, newIndexDescriptor.name());

            
            Column<?> reindexedColumn = column.reindexColumn(newIndex);

            TablePrinter.printColumn(reindexedColumn);
        }
    }
}

