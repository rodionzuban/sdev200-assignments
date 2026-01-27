// Exercise 11.1 - Module 2 Assignment 2

package module2;

import java.util.*;

public class Exercise11_01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Collect side length, color, filled info about triangle
        System.out.println("Enter triangle side length 1: ");
        double side1 = s.nextDouble();

        System.out.println("Enter triangle side length 2: ");
        double side2 = s.nextDouble();

        System.out.println("Enter triangle side length 3: ");
        double side3 = s.nextDouble();

        s.nextLine();

        System.out.println("Enter triangle color: ");
        String color = s.nextLine();

        System.out.println("Is the triangle filled? true / false ");
        boolean filled = s.nextBoolean();

        // initialize triangle with user input
        Triangle triangle = new Triangle(side1, side2, side3);
        triangle.setColor(color);
        triangle.setFilled(filled);

        // output triangle information
        System.out.println("Triangle area: " + triangle.getArea());
        System.out.println("Triangle perimeter: " + triangle.getPerimeter());
        System.out.println("Triangle color: " + triangle.getColor());
        System.out.println("Triangle filled: " + triangle.isFilled());

        s.close();
    }

    public static class Triangle extends GeometricObject {
        double side1 = 1.0;
        double side2 = 1.0;
        double side3 = 1.0;

        public Triangle() {
            super();
        }

        public Triangle(double side1, double side2, double side3) {
            super();
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }

        public double getSide1() {
            return side1;
        }

        public double getSide2() {
            return side2;
        }

        public double getSide3() {
            return side3;
        }

        public double getPerimeter() {
            return side1 + side2 + side3;
        }

        public double getArea() {
            double s = getPerimeter() / 2;

            return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        }

        public String toString() {
            return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
        }
    }

}