// Exercise 8.29 - Module 1 Programming Assignment 3 

import java.util.Scanner;

public class IdenticalArrays {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter m1 (a 3 by 3 matrix) row by row: ");
        int[][] m1 = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m1[i][j] = s.nextInt();
            }
        }

        System.out.print("Enter m2 (a 3 by 3 matrix) row by row: ");
        int[][] m2 = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m2[i][j] = s.nextInt();
            }
        }

        if (equals(m1, m2)) {
            System.out.println("The two arrays are strictly identical");
        } else {
            System.out.println("The two arrays are not strictly identical");
        }

        s.close();
    }

    // checks if two 2-dimensional arrays are strictly identical
    public static boolean equals(int[][] m1, int[][] m2) {
        if (m1.length != m2.length) {
            return false;
        }

        for (int i = 0; i < m1.length; i++) {
            if (m1[i].length != m2[i].length) {
                return false;
            }

            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }

        return true;

    }
}
