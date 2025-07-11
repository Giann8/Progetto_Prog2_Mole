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
import mole.TablePrinter;

public class IndiceNumerico {
    public IndiceNumerico() {
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            while (s.hasNextLine()) {
                var input = s.nextLine().split(" ");


                IndexImpl index = new IndexImpl(Integer.parseInt(input[0]), Integer.parseInt(args[0]),
                        Integer.parseInt(input[1]));

                System.out.println();

                TablePrinter.printIndex(index);
            }

        }
    }
}
