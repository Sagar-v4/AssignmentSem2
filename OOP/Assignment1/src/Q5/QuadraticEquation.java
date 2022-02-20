/**
 * @author Sagar Variya | 202112114
 */

package Q5;

import java.util.Scanner;

import static java.lang.Math.*;

/**
 * this class can calculate the quadratic equation : ax^2 + bx + c = 0
 */
public class QuadraticEquation {

    /**
     * ax^2+bx+c=0
     * declaring 3 private data members
     */
    private final int a, b, c;

    /**
     * public constructor that sets the value of equation
     *
     * @param a integer type A's value
     * @param b integer type B's value
     * @param c integer type C's value
     */
    public QuadraticEquation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * this function will find the roots of the quadratic equation
     * first it will compare the value of a with 0, if A is 0 then the equation is not quadratic
     * then it will calculate discriminant (d)
     */
    public void calculateRoots() {

        if (a == 0) {
            System.out.println("The value of A cannot be 0.");
            return;
        }

        int d = b * b - 4 * a * c;

        if (d == 0) {
            // discriminant is zero
            System.out.println(-(double) b / (2 * a) + "\n" + -(double) b / (2 * a));
        } else if (d > 0) {
            // discriminant is positive
            double sqrtOfd = sqrt(abs(d));
            System.out.println((-b + sqrtOfd) / (2 * a) + "\n" + (-b - sqrtOfd) / (2 * a));
        } else {
            // discriminant is negative
            System.out.println("there are no real solutions for this equation.");
        }
    }
}

class Q5 {

    public static void main(String[] args) {

        int[] val = new int[3];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter value of " + (i + 1) + "th : ");
            val[i] = sc.nextInt();
        }

        QuadraticEquation qe = new QuadraticEquation(val[0], val[1], val[2]);
        qe.calculateRoots();

    }
}