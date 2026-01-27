// Exercise 12.9 - Module 2 Exercise 3

package module2;

import java.util.*;

public class Exercise12_09 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter binary number: ");
        String bin = s.next();

        // attempt to convert binary to decimal and catch BinaryFormatException
        try {
            int dec = bin2dec(bin);
            System.out.println("Decimal form: " + dec);
        } catch (BinaryFormatException ex) {
            System.out.println("Not a binary number");
        }

        s.close();
    }

    // converts binary to decimal form, throws custom BinaryFormatException
    public static int bin2dec(String binary) throws BinaryFormatException {
        try {
            int result = Integer.parseInt(binary, 2);
            return result;
        } catch (NumberFormatException ex) {
            throw new BinaryFormatException();
        }
    }

    static class BinaryFormatException extends Exception {
        public BinaryFormatException() {
            super();
        }
    }
}