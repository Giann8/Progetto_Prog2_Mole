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

import java.util.Scanner;
import mole.Interfaces.Index;
import mole.IndexImpl;
import utils.InputParsing;
import utils.InputParsing.IndexDescriptor;

public class IndiceMedesimo {
    public IndiceMedesimo() {
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {

            while (s.hasNextLine()) {
                IndexDescriptor indexDescriptor1 = (IndexDescriptor) InputParsing.parseDescriptor(s.nextLine());
                Object[] labels1 = InputParsing.parseValues(s.nextLine(), indexDescriptor1.len());
                IndexImpl index1 = new IndexImpl(labels1, indexDescriptor1.name());

                IndexDescriptor indexDescriptor2 = (IndexDescriptor) InputParsing.parseDescriptor(s.nextLine());
                Object[] labels2 = InputParsing.parseValues(s.nextLine(), indexDescriptor2.len());
                Index index2 = new IndexImpl(labels2, indexDescriptor2.name());

                System.out.println(index1.equals(index2));
            }

        }
    }
}
