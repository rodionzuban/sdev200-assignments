// Exercise 13.9 - Module 3 Assignment 1

package module3;

public class Exercise13_09 {
    class Circle extends GeometricObject implements Comparable<Circle> {
        private double radius;

        public Circle() {
            super();
        }

        public Circle(double radius) {
            super();
            this.radius = radius;
        }

        // getter and setter methods for radius
        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        // implement abstract GeometricObject methods + calculateDiameter
        @Override
        public double getArea() {
            return radius * radius * Math.PI;
        }

        public double getDiameter() {
            return 2 * radius;
        }

        @Override
        public double getPerimeter() {
            return 2 * radius * Math.PI;
        }

        public void printCircle() {
            System.out.println("The circle is created " + getDateCreated() + " and the radius is " + radius);
        }

        // implement Comparable<Circle> behavior

        @Override
        public int compareTo(Circle other) {
            return Double.compare(this.radius, other.getRadius());
        }

        @Override
        public boolean equals(Object obj) {
            // check if objects are same reference
            if (obj == this) {
                return true;
            }

            // check if objects are same class, make sure not null

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Circle other = (Circle) obj;

            return other.getRadius() == this.radius;
        }
    }
}
