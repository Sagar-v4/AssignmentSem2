package Q3;

import java.math.BigInteger;
import java.util.Scanner;

public class Calculator {

    private long longA;
    private long longB;
    private double doubleA;
    private double doubleB;
    private final boolean[] flag = new boolean[2];
    Scanner sc = new Scanner(System.in);

    Calculator() {
        longA = longB = 0;
        doubleA = doubleB = 0;
    }

    Calculator(long a, long b){
        longA = a;
        longB = b;
        doubleA = doubleB = 0;
        flag[0] = flag[1] = true;
    }

    Calculator(long a, double b){
        longA = a;
        longB = 0;
        doubleA = b;
        doubleB = 0;
        flag[0] =  true;
        flag[1] = false;
    }

    Calculator(double a, double b){
        longA = longB = 0;
        doubleA = a;
        doubleB = b;
        flag[0] = flag[1] = false;
    }

    Calculator(double a, long b){
        longA = b;
        longB = 0;
        doubleA = a;
        doubleB = 0;
        flag[0] =  false;
        flag[1] = true;
    }

    void summation() {
        if(flag[0] && flag[1]) {
            longA += longB;
            longB = 0;
        } else if(!flag[0] && !flag[1]) {
            doubleA += doubleB;
            doubleB = 0;
        } else {
            doubleA += longA;
            longA = 0;
        }
    }

    void summation(long x) {
        if(flag[0] && flag[1]){
            longA += x;
        } else {
            doubleA += x;
        }
    }

    void summation(double x) {
        if(flag[0] && flag[1]){
            doubleA = longA + x;
            longA = 0;
            flag[1] = false;
        } else {
            doubleA += x;
        }
    }

    void subtraction() {
        if(flag[0] && flag[1]) {
            longA -= longB;
            longB = 0;
        } else if(!flag[0] && !flag[1]) {
            doubleA -= doubleB;
            doubleB = 0;
        } else if(flag[0] && !flag[1]){
            longA -= doubleA;
            doubleA = 0;
        } else if(!flag[0] && flag[1]){
            doubleA -= longA;
            longA = 0;
        }
    }

    void subtraction(long x) {
        if(flag[0] && flag[1]){
            longA -= x;
        } else {
            doubleA -= x;
        }
    }

    void subtraction(double x) {
        if(flag[0] && flag[1]){
            doubleA = longA - x;
            longA = 0;
            flag[1] = false;
        } else {
            doubleA -= x;
        }
    }

    void multiplication() {
        if(flag[0] && flag[1]) {
            longA *= longB;
            longB = 0;
        } else if(!flag[0] && !flag[1]) {
            doubleA *= doubleB;
            doubleB = 0;
        } else {
            doubleA *= longA;
            longA = 0;
        }
    }

    void multiplication(long x) {
        if(flag[0] && flag[1]){
            longA *= x;
        } else {
            doubleA *= x;
        }
    }

    void multiplication(double x) {
        if(flag[0] && flag[1]){
            doubleA = longA * x;
            longA = 0;
            flag[1] = false;
        } else {
            doubleA *= x;
        }
    }

    void displayIfNoConstructor() {

        long l;
        double d1, d2;
        BigInteger num;

        System.out.println("Enter number 1: ");
        num = sc.nextBigInteger();

        l = num.longValue();
        d1 = num.doubleValue();

        if(l == d1) {
            longA = l;
            flag[0] = true;
        } else doubleA = d1;

        System.out.println("Enter number 2: ");
        num = sc.nextBigInteger();

        l = num.longValue();
        d2 = num.doubleValue();

        if(l == d2 && flag[0]) {
            longB = l;
            flag[1] = true;
        } else {
            if(doubleA == d1) doubleB = d2;
            else doubleA = d2;
        }

        System.out.println("Which operation you want to perform: ");



    }


}
