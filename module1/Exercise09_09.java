// Exercise 9.9 - Module 1 Assignment 4

public class Exercise09_09 {
    public static void main(String[] args) {
        RegularPolygon p1 = new RegularPolygon();
        RegularPolygon p2 = new RegularPolygon(6, 4);
        RegularPolygon p3 = new RegularPolygon(10, 4, 5.6, 7.8);

        System.out.println("Polygon 1\nPerimeter: " + p1.getPerimeter() + "\nArea: " + p1.getArea());
        System.out.println("Polygon 2\nPerimeter: " + p2.getPerimeter() + "\nArea: " + p2.getArea());
        System.out.println("Polygon 3\nPerimeter: " + p3.getPerimeter() + "\nArea: " + p3.getArea());
    }
}

class RegularPolygon {
    private int sides = 3;
    private double side = 1.0;
    private double x = 0;
    private double y = 0;

    public RegularPolygon() {

    }

    public RegularPolygon(int sides, double side) {
        this.sides = sides;
        this.side = side;
    }

    public RegularPolygon(int sides, double side, double x, double y) {
        this.sides = sides;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    public int getSides() {
        return sides;
    }

    public double getSide() {
        return side;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getPerimeter() {
        return side * sides;
    }

    public double getArea() {
        return (sides * Math.pow(side, 2)) / (4 * Math.tan(Math.PI / sides));
    }
}