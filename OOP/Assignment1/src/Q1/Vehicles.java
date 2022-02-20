/**
 * @author Sagar Variya | 202112114
 */

package Q1;

import java.util.Scanner;

/**
 *  Vehicles class contains 4 data members (with getters & setters) that describes the price, seats, wheels and the name of the company.
 *  it has member function that can describes the class value in formal way.
 */
public class Vehicles {

    /**
     *  creating 4 private data members
     */
    private int price;
    private int no_of_seats;
    private int no_of_wheels;
    private String name_of_company;

    /**
     * this function gives the price of Vehicle class
     * @return price of vehicle
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * this function sets the price of Vehicle class
     * @param price price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * this function gives the number of seats of Vehicle
     * @return number of seats of vehicle
     */
    public int getNo_of_seats() {
        return this.no_of_seats;
    }

    /**
     * this function sets the number of seats of Vehicle
     * @param no_of_seats number of seats
     */
    public void setNo_of_seats(int no_of_seats) {
        this.no_of_seats = no_of_seats;
    }

    /**
     * this function gives the number of wheels of Vehicle
     * @return number of wheels of vehicle
     */
    public int getNo_of_wheels() {
        return this.no_of_wheels;
    }

    /**
     * this function sets the number of wheels of Vehicle
     * @param no_of_wheels number of wheels
     */
    public void setNo_of_wheels(int no_of_wheels) {
        this.no_of_wheels = no_of_wheels;
    }

    /**
     * this function gives the name of the company of Vehicle
     * @return name of the company of vehicle
     */
    public String getName_of_company() {
        return this.name_of_company;
    }

    /**
     * this function sets the name of the company of Vehicle
     * @param name_of_company name of the company
     */
    public void setName_of_company(String name_of_company) {
        this.name_of_company = name_of_company;
    }

    /**
     * this function gives the description about the vehicle in formal way
     */
    public void description() {
        System.out.println("\nThis vehicle came from " + getName_of_company() + ". "
                + "It's " + getNo_of_wheels() + " wheeler vehicle comes with the "
                + getNo_of_seats() + " seats in a price of " + getPrice() + "/- only.");
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