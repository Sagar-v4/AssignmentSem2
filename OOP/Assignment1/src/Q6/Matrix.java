/**
 * @author Sagar Variya | 202112114
 */

package Q6;

import java.util.Scanner;

/**
 * the matrix class can take any dimension of matrix and can provide the power if it.
 * to calculate the power of matrix it should be square like 2*2, 3*3 etc...
 */
public class Matrix {

    /**
     * declaring private data members
     */
    private final int n;
    private long[][] matrixPowered;
    private long[][] identityMatrix;
    private final long[][] matrixOriginal;

    /**
     * constructor with 1 parameter
     * it will create the size of the matrix
     * @param n integer type to create n*n matrix
     */
    public Matrix(int n) {
        this.matrixOriginal = new long[n][n];
        this.n = n;
    }

    /**
     * this function used to insert the matrix value.
     * just call it, and it will ask user all the values and store it.
     */
    public void insert() {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Enter [" + (i + 1) + "][" + (j + 1) + "] element of matrix: ");
                this.matrixOriginal[i][j] = sc.nextLong();
            }
        }
    }

    /**
     * this function will display original matrix user has inserted
     */
    public void displayOriginalMatrix() {
        System.out.print("\nOriginal matrix: \n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(this.matrixOriginal[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * this function will handle the multiplication of the matrix
     * it will multiply by itself or by identity according to parameter value came and also store it to accordingly.
     * @param withIdentity boolean type if with identity then true else false
     */
    void matrixMultiplication(final boolean withIdentity) {
        long temp, mod = Long.MAX_VALUE;
        long[][] tempMatrix = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tempMatrix[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    if (withIdentity) temp = (this.identityMatrix[i][k] * this.matrixPowered[k][j]) % mod;
                    else temp = (this.matrixPowered[i][k] * this.matrixPowered[k][j]) % mod;

                    tempMatrix[i][j] = (tempMatrix[i][j] + temp) % mod;
                }
            }
        }

        if (withIdentity) this.identityMatrix = tempMatrix;
        else this.matrixPowered = tempMatrix;
    }

    /**
     * this function is used to calculate the matrix to multiply by itself or identity
     * it will reduce the multiplication time complexity from O(n) to O(log n)
     * to reduce time complexity this function uses identity matrix (all diagonal elements are 1).
     * so, the multiplication with identity matrix will produce the multiply by 1 and store it in
     * @param power integer type power of the matrix
     */
    void calculateMatrixPower(int power) {
        this.matrixPowered = this.matrixOriginal;
        this.identityMatrix = new long[n][n];
        for (int i = 0; i < n; i++) this.identityMatrix[i][i] = 1;

        while (power != 0) {
            if (power % 2 != 0) {
                matrixMultiplication(true);
                power--;
            } else {
                matrixMultiplication(false);
                power /= 2;
            }
        }

        this.matrixPowered = this.identityMatrix;
    }

    /**
     * this function take power number of matrix then calculate and show the result
     * @param power integer type power of the matrix
     */
    public void displayPowerMatrix(final int power) {
        calculateMatrixPower(power);

        System.out.print("\nPower " + power + " matrix: \n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(this.matrixPowered[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Q6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nPower of matrix can calculate only if rows and cols are same...\nEnter matrix Dimension: ");
        int n = sc.nextInt();

        Matrix matrix = new Matrix(n);
        matrix.insert();

        System.out.print("\nEnter power of matrix: ");
        int p = sc.nextInt();

        matrix.displayOriginalMatrix();
        matrix.displayPowerMatrix(p);

    }
}