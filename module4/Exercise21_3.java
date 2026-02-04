package module4;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Exercise21_3 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String filename = args[0];

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

        boolean inComment = false;
        boolean inString = false;

        String currentWord = "";
        String lastTwo = "";

        // check each line in code
        while (input.hasNextLine()) {
            String line = input.nextLine();
            currentWord = "";

            for (int i = 0; i < line.length(); i++) {

                // if character can be used in an identifier, keep building the word. otherwise,
                // check if the identifier
                // is a valid keyword
                if (!Character.isJavaIdentifierPart(line.charAt(i))) {
                    if (keywordSet.contains(currentWord) && !inComment && !inString) {
                        count++;
                    }
                    currentWord = "";
                } else {
                    currentWord += line.charAt(i);
                }
                if (i >= 1) {
                    lastTwo = line.substring(i - 1, i + 1);
                }

                // escape out of block comment
                if (inComment) {
                    if (lastTwo.equals("*/")) {
                        inComment = false;
                        currentWord = "";
                    }
                } else {

                    // escape or enter string
                    if (line.charAt(i) == '"') {
                        inString = !inString;
                        currentWord = "";
                    }
                    // Check for block or line comments
                    else if (lastTwo.equals("/*")) {
                        inComment = true;
                        currentWord = "";

                    } else if (lastTwo.equals("//")) {
                        lastTwo = "";
                        break;
                    }
                }
            }

            // if keyword at end of line
            if (keywordSet.contains(currentWord) && !inComment && !inString) {
                count++;
            }
        }

        input.close();

        return count;
    }
}
