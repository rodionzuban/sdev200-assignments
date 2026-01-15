// Exercise 6.31 - Module 1 Programming Assignment 2

import java.util.Scanner;

public class LuhnCheck {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter a credit card number as a long integer: ");
        long num = s.nextLong();

        boolean valid = isValid(num);

        if (valid) {
            System.out.println(num + " is valid");
        } else {
            System.out.println(num + " is invalid");
        }

        s.close();
    }

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        return (sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0;
    }

    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        number /= 10;

        while (number > 0) {
            int digit = (int) (number % 10);
            sum += getDigit(digit * 2);
            number /= 100;

        }

        return sum;
    }

    /**
     * Return this number if it is a single digit, otherwise,
     * return the sum of the two digits
     */
    public static int getDigit(int number) {
        if (number > 9) {
            return number % 10 + (number / 10);
        }

        return number;
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number) {
        int sum = 0;

        while (number > 0) {
            int digit = (int) (number % 10);
            sum += digit;
            number /= 100;

        }

        return sum;
    }

    /** Return true if the number d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        while (number > 9) {
            number /= 10;
        }

        return number == d;
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        int count = 0;
        while (d > 9) {
            count++;
            d /= 10;
        }

        return count;
    }

    /**
     * Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number.
     */
    public static long getPrefix(long number, int k) {
        while (number >= Math.pow(10, k)) {
            number /= 10;
        }

        return number;
    }
}
