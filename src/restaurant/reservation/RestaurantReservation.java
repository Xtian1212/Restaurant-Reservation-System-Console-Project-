/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurant.reservation;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author a0916
 */
public class RestaurantReservation {

    /*
        1. View all reservation
        2. Make Reservation
        3. Delete Reservation
        4. Generate Report
        5. Exit
     */
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        ReservationView view = new ReservationView();
        while (view.isIsTrue()) {
            view.reservationView();
            String choice = scan.nextLine();
            view.userChoice(choice);
        }
    }

}
