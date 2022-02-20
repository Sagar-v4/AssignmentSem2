/**
 * @author Sagar Variya | 202112114
 */

package Q3;

import java.util.Scanner;
import java.util.Objects;
import java.math.BigDecimal;

/**
 * this class has several data members and member function to perform tasks.
 * this calculator class can calculate addition, subtraction and multiplication of integer and float numbers.
 * it has 3 constructor with no parameter, 1 parameter and 2 parameter.
 * it will run in a loop and ask you about input value like which operations and numbers.
 * it can decide automatically about the number is integer or float.
 * to exit give 0 when it asks about operations.
 */
public class Calculator {

    /**
     * private 5 data members
     */
    private long longNum = 0;
    private double doubleA = 0;
    private double doubleB = 0;
    private boolean flag, calculated;
    Scanner sc = new Scanner(System.in);

    /**
     * default constructor.
     */
    public Calculator() {

        takeNumber(1);
        calculate(true);
    }

    /**
     * 1 parameter constructor
     * it will set the 1st number and ask you 2nd number
     * @param a 1st number
     */
    public Calculator(double a) {

        this.doubleA = a;

        showNumber(1);
        calculate(true);
    }

    /**
     * 2 parameter constructor
     * it will set both the numbers
     * @param a 1st number
     * @param b 2nd number
     */
    public Calculator(double a, double b) {

        this.doubleA = a;
        this.doubleB = b;

        showNumber(1);
        showNumber(2);
        calculate(false);
    }

    /**
     * default addition function
     */
    void summation() {
        this.doubleA += this.doubleB;
        this.doubleB = 0;
    }

    /**
     * it will add 1st number and 2nd number come in the parameter
     * overloaded addition function with the integer value as parameter
     * @param x 2nd number integer type
     */
    void summation(long x) {
        this.doubleA += x;
    }

    /**
     * it will add 1st number and 2nd number come in the parameter
     * overloaded addition function with the float/decimal value as parameter
     * @param x 2nd number float/decimal type
     */
    void summation(double x) {
        this.doubleA += x;
    }

    /**
     * default subtraction function
     */
    void subtraction() {
        this.doubleA -= this.doubleB;
        this.doubleB = 0;
    }

    /**
     * it will subtract 1st number and 2nd number come in the parameter
     * overloaded addition function with the integer value as parameter
     * @param x 2nd number integer type
     */
    void subtraction(long x) {
        this.doubleA -= x;
    }

    /**
     * it will subtract 1st number and 2nd number come in the parameter
     * overloaded addition function with the float/decimal value as parameter
     * @param x 2nd number float/decimal type
     */
    void subtraction(double x) {
        this.doubleA -= x;
    }

    /**
     * default multiplication function
     */
    void multiplication() {
        this.doubleA *= this.doubleB;
        this.doubleB = 0;
    }

    /**
     * it will multiply 1st number and 2nd number come in the parameter
     * overloaded addition function with the integer value as parameter
     * @param x 2nd number integer type
     */
    void multiplication(long x) {
        this.doubleA *= x;
    }

    /**
     * it will multiply 1st number and 2nd number come in the parameter
     * overloaded addition function with the float/decimal value as parameter
     * @param x 2nd number float/decimal type
     */
    void multiplication(double x) {
        this.doubleA *= x;
    }

    /**
     * this function take 1st or 2nd number parameter (which to show e.g. 1 or 2)
     * it will show the number according to its type (integer/float)
     * @param n integer type index number 1 or 2
     */
    void showNumber(int n) {

        long l = (n == 1) ? (long) this.doubleA : (long) this.doubleB;
        double d = (n == 1) ? this.doubleA : this.doubleB;
        this.flag = (l == d);

        if (this.flag) System.out.println("Number " + n + ": " + l);
        else System.out.println("Number " + n + ": " + d);
    }

    /**
     * this function ask use to insert the operation type +, -, *
     * it will continue ask about operation if user give input is other than +, -. * or zero
     * if you want to quite/exit then enter 0(zero)
     * @return the operation you want to perform
     */
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

    /**
     * this function will take the number value from the user to do operation
     * it gives the flag about which type of data(integer/float) user has inserted
     * @param index integer type to take 1st or 2nd number
     */
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

    /**
     * this function do the main work of calculation.
     * it will decide with operation(+, -, * or exit) to do according to flag value(integer or float)
     * @param takeSecond boolean type if it has to take 2nd number than true else false
     */
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

    /**
     * this function displays the answer of the calculation
     * if user hasn't done any calculation then it will show "Exit without calculation..."
     * this is the only public function in the class
     * @param exit boolean value to check if true then exit else continue
     */
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