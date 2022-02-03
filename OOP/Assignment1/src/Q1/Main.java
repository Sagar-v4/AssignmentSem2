package Q1;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Vehicles motorcycles = new Vehicles();
        Vehicles car = new Vehicles();

        int price;
        int seats;
        int wheels;
        String company;

        System.out.print("Enter name of motorcycles : ");
        company = sc.nextLine();
        System.out.print("Enter number of wheel of motorcycles : ");
        wheels = sc.nextInt();
        System.out.print("Enter number of seats of motorcycles : ");
        seats = sc.nextInt();
        System.out.print("Enter price of motorcycle : ");
        price = sc.nextInt();

        motorcycles.setName_of_company(company);
        motorcycles.setNo_of_wheels(wheels);
        motorcycles.setNo_of_seats(seats);
        motorcycles.setPrice(price);

        System.out.print("Enter company name of car : ");
        company = sc.nextLine();
        System.out.print("Enter number of wheel of car : ");
        wheels = sc.nextInt();
        System.out.print("Enter number of seats of car : ");
        seats = sc.nextInt();
        System.out.print("Enter price of car : ");
        price = sc.nextInt();

        car.setName_of_company(company);
        car.setNo_of_wheels(wheels);
        car.setNo_of_seats(seats);
        car.setPrice(price);

        motorcycles.description();

        car.description();




    }
}