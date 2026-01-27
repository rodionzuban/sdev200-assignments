// Exercise 13.15 - Module 3 Assignment 2

package module3;

import java.math.BigInteger;
import java.util.Scanner;

public class Exercise13_15 {

    // test program
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter rational r1 with numerator and denominator separated by a space: ");
        Rational r1 = new Rational(s.nextBigInteger(), s.nextBigInteger());
        System.out.print("Enter rational r2 with numerator and denominator separated by a space: ");
        Rational r2 = new Rational(s.nextBigInteger(), s.nextBigInteger());

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
        System.out.println(r2 + " is " + r2.doubleValue());

        s.close();
    }

    static class Rational extends Number implements Comparable<Rational> {
        private BigInteger numerator = BigInteger.ZERO;
        private BigInteger denominator = BigInteger.ONE;

        public Rational() {

        }

        public Rational(BigInteger numerator, BigInteger denominator) {
            BigInteger gcd = gcd(numerator, denominator);
            this.numerator = numerator.abs().divide(gcd);
            this.denominator = denominator.abs().divide(gcd);
        }

        // find greatest common divisor using modulo
        private static BigInteger gcd(BigInteger n, BigInteger d) {
            BigInteger n1 = n.abs();
            BigInteger n2 = d.abs();

            BigInteger gcd = BigInteger.ONE;

            for (BigInteger k = BigInteger.ONE; n1.compareTo(k) > 0 && n2.compareTo(k) > 0; k = k.add(BigInteger.ONE)) {
                if (n1.mod(k).equals(BigInteger.ZERO) && n2.mod(k).equals(BigInteger.ZERO)) {
                    gcd = k;
                }
            }

            return gcd;
        }

        // getter methods
        public BigInteger getNumerator() {
            return numerator;
        }

        public BigInteger getDenominator() {
            return denominator;
        }

        // basic operations
        public Rational add(Rational secondRational) {
            BigInteger n = numerator.multiply(secondRational.getDenominator())
                    .add(denominator.multiply(secondRational.getNumerator()));
            BigInteger d = denominator.multiply(secondRational.getDenominator());
            return new Rational(n, d);
        }

        public Rational subtract(Rational secondRational) {
            BigInteger n = numerator.multiply(secondRational.getDenominator())
                    .subtract(denominator.multiply(secondRational.getNumerator()));
            BigInteger d = denominator.multiply(secondRational.getDenominator());
            return new Rational(n, d);
        }

        public Rational multiply(Rational secondRational) {
            BigInteger n = numerator.multiply(secondRational.getNumerator());
            BigInteger d = denominator.multiply(secondRational.getDenominator());
            return new Rational(n, d);
        }

        public Rational divide(Rational secondRational) {
            BigInteger n = numerator.multiply(secondRational.getDenominator());
            BigInteger d = denominator.multiply(secondRational.getNumerator());
            return new Rational(n, d);
        }

        @Override
        public String toString() {
            if (denominator.equals(BigInteger.ONE)) {
                return numerator + "";
            } else {
                return numerator + "/" + denominator;
            }
        }

        @Override
        public boolean equals(Object other) {
            if (other != null && this.subtract((Rational) (other)).getNumerator().equals(BigInteger.ZERO)) {
                return true;
            }
            return false;

        }

        // implement methods from abstract Number

        @Override
        public int intValue() {
            return (int) doubleValue();
        }

        @Override
        public float floatValue() {
            return (float) doubleValue();
        }

        @Override
        public double doubleValue() {
            return numerator.doubleValue() / denominator.doubleValue();
        }

        @Override
        public long longValue() {
            return (long) doubleValue();
        }

        // implement Comparable<Rational> behavior
        @Override
        public int compareTo(Rational other) {
            int comparable = this.subtract(other).getNumerator().compareTo(BigInteger.ZERO);
            if (comparable > 0) {
                return 1;
            } else if (comparable < 0) {
                return -1;
            }
            return 0;
        }

    }
}
