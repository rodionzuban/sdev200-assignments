// Exercise 10.14 - Module 2 Assignment 1

package module2;

import java.time.Instant;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Exercise10_14 {
    public static void main(String[] args) {
        // Create MyDate objects and test
        MyDate date1 = new MyDate();

        System.out.println("Date 1: new MyDate()");
        System.out.println("Year: " + date1.getYear() + "\nMonth: " + date1.getMonth() + "\nDay: " + date1.getDay());

        MyDate date2 = new MyDate(34355555133101L);

        System.out.println("Date 1: with elapsedTime constructor");
        System.out.println("Year: " + date2.getYear() + "\nMonth: " + date2.getMonth() + "\nDay: " + date2.getDay());
    }

    static class MyDate {
        private int year;
        private int month;
        private int day;

        // Use GregorianCalendar to extract year, month, day, from UTC timestamp (via
        // Instant.now())
        public MyDate() {
            GregorianCalendar cal = new GregorianCalendar(
                    TimeZone.getTimeZone("UTC"));

            cal.setTimeInMillis(Instant.now().toEpochMilli());

            this.year = cal.get(GregorianCalendar.YEAR);
            this.month = cal.get(GregorianCalendar.MONTH);
            this.day = cal.get(GregorianCalendar.DAY_OF_MONTH);
        }

        public MyDate(long elapsedTime) {
            GregorianCalendar cal = new GregorianCalendar(
                    TimeZone.getTimeZone("UTC"));

            cal.setTimeInMillis(elapsedTime);

            this.year = cal.get(GregorianCalendar.YEAR);
            this.month = cal.get(GregorianCalendar.MONTH);
            this.day = cal.get(GregorianCalendar.DAY_OF_MONTH);
        }

        public MyDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public void setDate(long elapsedTime) {
            GregorianCalendar cal = new GregorianCalendar(
                    TimeZone.getTimeZone("UTC"));

            cal.setTimeInMillis(elapsedTime);

            this.year = cal.get(GregorianCalendar.YEAR);
            this.month = cal.get(GregorianCalendar.MONTH);
            this.day = cal.get(GregorianCalendar.DAY_OF_MONTH);
        }
    }
}
