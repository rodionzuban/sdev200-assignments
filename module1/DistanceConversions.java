// Exercise 6.9 - Module 1 Programming Assignment 1

public class DistanceConversions {
    public static void main(String[] args) {
        System.out.printf("%-10s %10s     %-10s %10s%n", "Feet", "Meters", "Meters", "Feet");
        System.out.println("--------------------------------------------");
        for (int i = 1; i <= 10; i++) {
            double feet = i * 1.0;
            double meters = 15 + i * 5.0;

            System.out.printf("%10.1f %10.3f     %10.1f %10.3f%n", feet, footToMeter(feet), meters,
                    meterToFoot(meters));
        }
    }

    public static double footToMeter(double foot) {
        return 0.305 * foot;
    }

    public static double meterToFoot(double meter) {
        return 3.279 * meter;
    }

}