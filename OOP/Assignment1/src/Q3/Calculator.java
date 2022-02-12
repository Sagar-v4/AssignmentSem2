package Q3;

import java.util.Scanner;
import java.util.Objects;
import java.math.BigDecimal;

public class Calculator {

    private long longNum = 0;
    private double doubleA = 0;
    private double doubleB = 0;
    private boolean flag, calculated;
    Scanner sc = new Scanner(System.in);

    public Calculator() {

        takeNumber(1);
        calculate(true);
    }

    public Calculator(double a) {

        this.doubleA = a;

        showNumber(1);
        calculate(true);
    }

    public Calculator(double a, double b) {

        this.doubleA = a;
        this.doubleB = b;

        showNumber(1);
        showNumber(2);
        calculate(false);
    }

    void summation() {
        this.doubleA += this.doubleB;
        this.doubleB = 0;
    }

    void summation(long x) {
        this.doubleA += x;
    }

    void summation(double x) {
        this.doubleA += x;
    }

    void subtraction() {
        this.doubleA -= this.doubleB;
        this.doubleB = 0;
    }

    void subtraction(long x) {
        this.doubleA -= x;
    }

    void subtraction(double x) {
        this.doubleA -= x;
    }

    void multiplication() {
        this.doubleA *= this.doubleB;
        this.doubleB = 0;
    }

    void multiplication(long x) {
        this.doubleA *= x;
    }

    void multiplication(double x) {
        this.doubleA *= x;
    }

    void showNumber(int n) {

        long l = (n == 1) ? (long) this.doubleA : (long) this.doubleB;
        double d = (n == 1) ? this.doubleA : this.doubleB;
        this.flag = (l == d);

        if (this.flag) System.out.println("Number " + n + ": " + l);
        else System.out.println("Number " + n + ": " + d);
    }

    String askOperation() {

        String s;

        do {
            System.out.print("Enter +, - or * (0 to exit): ");
            s = this.sc.next();
            s = s.trim();

            if (Objects.equals(s, "0")) displayAns(true);

        } while (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("0"));

        return s;
    }

    void takeNumber(int index) {

        long l;
        double d;
        BigDecimal num;

        System.out.print("Enter " + index + ((index == 1) ? "st" : "nd") + " number: ");
        num = this.sc.nextBigDecimal();

        l = num.longValue();
        d = num.doubleValue();

        if (index == 1) {
            this.doubleA = d;
        } else {
            this.flag = (l == d);
            if (this.flag) this.longNum = l;
            else this.doubleB = d;
        }
    }

    void calculate(boolean takeSecond) {

        String op = askOperation();

        if (!Objects.equals(op, "0")) {

            this.calculated = true;
            if (takeSecond) takeNumber(2);

            if (Objects.equals(op, "+")) {

                if (takeSecond) {
                    if (this.flag) summation(this.longNum);
                    else summation(this.doubleB);
                } else {
                    summation();
                }
                displayAns(false);

            } else if (Objects.equals(op, "-")) {

                if (takeSecond) {
                    if (this.flag) subtraction(this.longNum);
                    else subtraction(this.doubleB);
                } else {
                    subtraction();
                }
                displayAns(false);

            } else if (Objects.equals(op, "*")) {

                if (takeSecond) {
                    if (this.flag) multiplication(this.longNum);
                    else multiplication(this.doubleB);
                } else {
                    multiplication();
                }
                displayAns(false);

            } else System.out.println("Invalid Operation...");
        }
    }

    public void displayAns(boolean exit) {

        if (this.calculated) {
            long l = (long) this.doubleA;
            double d = this.doubleA;
            this.flag = (l == d);

            if (exit) System.out.print("Final ");
            if (this.flag) System.out.println("Answer: " + l);
            else System.out.println("Answer: " + d);

            if (!exit) calculate(true);

        } else System.out.println("Exit without calculation...");
    }
}

class Q3 {
    public static void main(String[] args) {

        System.out.println("Calling Default Constructor...");
        Calculator calc = new Calculator();

        System.out.println("\nCalling 1 parameter Constructor...");
        Calculator calc1 = new Calculator(40);

        System.out.println("\nCalling 2 parameter Constructor...");
        Calculator calc2 = new Calculator(20.25, 70);

        System.out.println("\nAll Calculator's Exit status:");
        calc.displayAns(true);
        calc1.displayAns(true);
        calc2.displayAns(true);
    }
}