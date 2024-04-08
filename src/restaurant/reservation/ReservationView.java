/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant.reservation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author a0916
 */
public class ReservationView {

    Scanner scan = new Scanner(System.in);
    private boolean isTrue = true;

    public ReservationView() {
    }

    public void reservationView() {
        System.out.println();
        System.out.println("RESTAURANT RESERVATION SYSTEM\n");
        System.out.println("System Menu\n");
        System.out.println("a. View all Reservation");
        System.out.println("b. Make Reservation");
        System.out.println("c. Delete Reservation");
        System.out.println("d. Generate Report");
        System.out.println("e. Exit\n");
        System.out.print("Enter your choice:  ");

    }

    public boolean isIsTrue() {
        return isTrue;
    }

    public void userChoice(String choice) throws IOException {
        if (choice.equalsIgnoreCase("a")) {
            ViewReservation viewReservation = new ViewReservation();
            viewReservation.viewReservation();
        }

        if (choice.equalsIgnoreCase("b")) {
            CreateReservation create = new CreateReservation();
            create.makeReservation();
        }

        if (choice.equalsIgnoreCase("c")) {
            DeleteReservation deleteReservation = new DeleteReservation();
            deleteReservation.deleteReservation();
        }

        if (choice.equalsIgnoreCase("d")) {
            GenerateReport generateReport = new GenerateReport();
            generateReport.generateReport();
        }

        if (choice.equalsIgnoreCase("e")) {
            System.out.println("Thank you!");
            isTrue = false;
        }
    }

   

    

    public void seeIfMakeReservationAgain(Scanner scan) throws IOException {

        while (true) {
            System.out.println("Do you want to make another reservation? (Y/N): ");
            String choice = scan.nextLine();
            try {
                if (choice.equalsIgnoreCase("y")) {
                    CreateReservation createReservation = new CreateReservation();
                    createReservation.makeReservation();
                    break;
                } else if (choice.equalsIgnoreCase("n")) {
                    reservationView();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please try again");
            }
        }
    }

}
