package module4;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Exercise21_3 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        File file = new File(filename);
        if (file.exists()) {
            System.out.println("The number of keywords in " + filename
                    + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist");
        }

        input.close();
    }

    public static int countKeywords(File file) throws Exception {
        // Array of all Java keywords + true, false and null
        String[] keywordString = {
                "abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto",
                "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile",
                "while", "true", "false", "null"
        };

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));

        int count = 0;

        Scanner input = new Scanner(file);

        String targetSequence = "";

        while (input.hasNextLine()) {
            String line = input.nextLine();

            for (String word : line.split("\\s+")) {
                if (targetSequence.equals("")) {
                    if (word.length() == 1 && word.charAt(0) == '"') {
                        targetSequence = String.valueOf('"');
                    } else if (word.length() >= 2 && word.substring(0, 2).equals("//")) {
                        break;
                    } else if (word.length() >= 2 && word.substring(0, 2).equals("/*")) {
                        targetSequence = "*/";
                    } else if (keywordSet.contains(word)) {
                        count++;
                    }
                } else {
                    System.out.println();
                }
            }
        }

        input.close();

        return count;
    }
}
