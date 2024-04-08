/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant.reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author a0916
 */
public class DeleteReservation {

    public void deleteReservation() {
        Scanner scan = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter Id you want to delete: ");
                int idNumber = scan.nextInt();
                scan.nextLine();
                System.out.print("Are you sure you want to delete reservation id no " + idNumber + "? (Y/N/C): ");
                String choice = scan.nextLine();
                switch (choice.toLowerCase()) {
                    case "y":
                        deleteId(idNumber);
                        break;
                    case "n":
                        deleteReservation();
                        break;
                    case "c":
                        ReservationView rsView = new ReservationView();
                        rsView.reservationView();
                }
                break;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            deleteReservation();
        }
    }

    private void deleteId(int idNumber) {
        File inputFile = new File("CustomerReservation.txt");
        File tempFile = new File("tempCustomerReservation.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean idFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // This skips empty line
                }

                String[] parts = line.split("\\s+", 2); // "\\s+" its looking for ( white space, tabs, new line, and carriage return ). And the + means one or more
                int parsedIntValue = Integer.parseInt(parts[0]);

                if (parsedIntValue == idNumber) {
                    idFound = true;
                    continue; // Skip writing this line
                }

                writer.write(line + System.getProperty("line.separator")); // System.getProperty("line.separator") -> adds new line at the end of the row
            }

            if (!idFound) {
                System.out.println("ID not found in the file");
                deleteReservation();
            }
        } catch (IOException e) {
            System.err.println("Error reading ID counter file: " + e.getMessage());
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else {
            System.err.println("Failed to delete or rename file.");
        }
    }

}
