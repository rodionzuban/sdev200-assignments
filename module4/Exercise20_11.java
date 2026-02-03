// Exercise 20.11 - Module 4 Assignment 1

package module4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Exercise20_11 {
    public static void main(String args[]) {
        // load file and Scanner object

        String filePath = args[0];
        File file = new File(filePath);
        Scanner sourceCode;

        // open file, if exists
        try {
            sourceCode = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("File name doesn't exist!");
            return;
        }

        Stack<Character> groupingSymbols = new Stack<Character>();
        String groups = "()[]{}";

        boolean valid = true;

        while (sourceCode.hasNextLine()) {
            String token = sourceCode.nextLine();
            System.out.println(token);

            if (!valid) {
                continue;
            }

            // go through each character in each line
            for (int i = 0; i < token.length(); i++) {
                int character = groups.indexOf(token.charAt(i));

                // skip if character not a grouping symbol
                if (character == -1) {
                    continue;
                }

                // remove if pair found, otherwise flag as mistake
                if (character % 2 == 1) {
                    if (groups.charAt(character - 1) == groupingSymbols.peek()) {
                        groupingSymbols.pop();
                    } else {
                        valid = false;
                        break;
                    }
                } else if (character % 2 == 0) {
                    groupingSymbols.add(groups.charAt(character));
                }
            }
        }

        System.out.println(valid ? "Correct grouping pairs" : "Incorrect grouping pairs");

        sourceCode.close();
    }
}
