package Q6;

import java.util.Scanner;

public class Matrix {
    private final int n;
    private long[][] matrixPowered;
    private long[][] identityMatrix;
    private final long[][] matrixOriginal;

    public Matrix(int n) {
        this.matrixOriginal = new long[n][n];
        this.n = n;
    }

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

    public void displayOriginalMatrix() {
        System.out.print("\nOriginal matrix: \n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(this.matrixOriginal[i][j] + "\t");
            }
            System.out.println();
        }
    }

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