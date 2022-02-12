package Q5;

import static java.lang.Math.*;

public class QuadraticEquation {
    //ax^2+bx+c=0
    private final int a, b, c;

    //defining a static method that finds the roots of the quadratic equation
    public QuadraticEquation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void calculateRoots() {
//comparing the value of a with 0, if A is 0 then the equation is not quadratic
        if (a == 0) {
            System.out.println("The value of a cannot be 0.");
            return;
        }
//calculating discriminant (d)
        int d = b * b - 4 * a * c;
        double sqrtOfd = sqrt(abs(d));

        if (d > 0) {
            System.out.println("The roots of the equation are real and different. \n");
            System.out.println((-b + sqrtOfd) / (2 * a) + "\n" + (-b - sqrtOfd) / (2 * a));
        } else if (d == 0) {
            System.out.println("The roots of the equation are real and same. \n");
            System.out.println(-(double) b / (2 * a) + "\n" + -(double) b / (2 * a));
        }
// executes if d < 0
        else {
            System.out.println("The roots of the equation are complex and different. \n");
            System.out.println(-(double) b / (2 * a) + " + i" + sqrtOfd + "\n" + -(double) b / (2 * a) + " - i" + sqrtOfd);
        }
    }
}

class Q5 {

    //main method
    public static void main(String[] args) {
        //Output 1
        int a = 3, b = -6, c = 3;

        //calling function
        QuadraticEquation qe = new QuadraticEquation(a, b, c);
        qe.calculateRoots();
        //Output 2: int a=1, b=1, c=1
        //Output 3: int a=1, b=-2, c=1
    }
}