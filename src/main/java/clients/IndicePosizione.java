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

import mole.IndexImpl;
import utils.InputParsing;
import utils.InputParsing.IndexDescriptor;

public class IndicePosizione {
    /** Costruttore vuoto */
    public IndicePosizione() {
        // Costruttore vuoto
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            IndexImpl index;
            String line = s.nextLine();
            IndexDescriptor id = (IndexDescriptor) InputParsing.parseDescriptor(line);
            
            if (id != null) {
                Object[] labels = InputParsing.parseValues(s.nextLine(), id.len());

                index = new IndexImpl(labels, id.name());

                System.out.println(index.positionOf(InputParsing.parseValues(args[0], 1)[0]));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
