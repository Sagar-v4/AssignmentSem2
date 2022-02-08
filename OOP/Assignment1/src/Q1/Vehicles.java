package Q1;

import java.util.Scanner;
import java.lang.instrument.Instrumentation;

public class Vehicles {

    private int price;
    private int no_of_seats;
    private int no_of_wheels;
    private String name_of_company;

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getNo_of_seats() { return no_of_seats; }
    public void setNo_of_seats(int no_of_seats) { this.no_of_seats = no_of_seats; }

    public int getNo_of_wheels() { return no_of_wheels; }
    public void setNo_of_wheels(int no_of_wheels) { this.no_of_wheels = no_of_wheels; }

    public String getName_of_company() { return name_of_company; }
    public void setName_of_company(String name_of_company) { this.name_of_company = name_of_company; }

    public void description()
    {
        System.out.println("\nThis vehicle came from " + getName_of_company() + ". "
                + "It's " + getNo_of_wheels() + "wheeler vehicle comes with the " + getNo_of_seats() + " seats in a price of " + getPrice() + "/- only.");
        //System.out.println("Memory consumption of this object is " + Instrumentation.getObjectSize(this) + " Bytes.\n");
    }


}

class Q1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Vehicles motorcycles = new Vehicles();
        Vehicles car = new Vehicles();

        int[] price = new int[2];
        int[] seats = new int[2];
        int[] wheels = new int[2];
        String[] company = new String[2];

        System.out.print("Enter name of motorcycles : ");
        company[0] = sc.next();
        System.out.print("Enter number of wheel of motorcycles : ");
        wheels[0] = sc.nextInt();
        System.out.print("Enter number of seats of motorcycles : ");
        seats[0] = sc.nextInt();
        System.out.print("Enter price of motorcycle : ");
        price[0] = sc.nextInt();

        motorcycles.setName_of_company(company[0]);
        motorcycles.setNo_of_wheels(wheels[0]);
        motorcycles.setNo_of_seats(seats[0]);
        motorcycles.setPrice(price[0]);

        motorcycles.description();

        System.out.print("Enter company name of car : ");
        company[1] = sc.next();
        System.out.print("Enter number of wheel of car : ");
        wheels[1] = sc.nextInt();
        System.out.print("Enter number of seats of car : ");
        seats[1] = sc.nextInt();
        System.out.print("Enter price of car : ");
        price[1] = sc.nextInt();

        car.setName_of_company(company[1]);
        car.setNo_of_wheels(wheels[1]);
        car.setNo_of_seats(seats[1]);
        car.setPrice(price[1]);

        car.description();

    }
}