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
public class CreateReservation {

    Scanner scan = new Scanner(System.in);

    public void makeReservation() throws IOException {
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        String dateValue = checkIfDate(scan);
        String timeValue = checkIfTime(scan);
        int noOfAdultValue = numberOfAdult(scan);
        int noOfChildrenValue = numberOfChildren(scan);
        int generatedId = generateId();
        saveReservation(name, dateValue, timeValue, noOfAdultValue, noOfChildrenValue, generatedId);
        ReservationView ifCreateAgain = new ReservationView();
        ifCreateAgain.seeIfMakeReservationAgain(scan);
    }

    private String checkIfTime(Scanner scan) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setLenient(false); // Prevents automatic correction of invalid dates
        String time;
        while (true) {
            try {
                System.out.print("Set time (HH:mm): ");
                time = scan.nextLine();
                timeFormat.parse(time);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use HH:mm");
            }
        }
        return time;
    }

    private String checkIfDate(Scanner scan) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false); // Prevents automatic correction of invalid dates
        String date;
        while (true) {
            try {
                System.out.print("Set date (MM/dd/yyyy): ");
                date = scan.nextLine();
                dateFormat.parse(date);
                break;
            } catch (ParseException e) { // Use specific exception so that it will return to the question (in try block)
                System.out.println("Invalid date format. Please use MM/dd/yyyy");
            }
        }
        return date;
    }

    private int numberOfAdult(Scanner scan) {
        int noOfAdult;
        while (true) {
            try {
                System.out.print("Enter number of Adults:  ");
                noOfAdult = scan.nextInt();
                scan.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
        return noOfAdult;
    }

    private int numberOfChildren(Scanner scan) {
        int noOfChildren;
        while (true) {
            try {
                System.out.print("Enter number of children:  ");
                noOfChildren = scan.nextInt();
                scan.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
        return noOfChildren;
    }

    private void saveReservation(String name, String dateValue, String timeValue,
            int noOfAdultValue, int noOfChildrenValue, int generatedId) throws IOException {
        FileWriter myWriter = new FileWriter("CustomerReservation.txt", true);
        fileExist();
        try {
            myWriter.write(generatedId + "\t" + name + "\t" + dateValue + "\t" + timeValue + "\t" + noOfAdultValue + "\t" + noOfChildrenValue + "\t" + System.getProperty("line.separator"));
            myWriter.close();
            System.out.println("\nSuccessfully save");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Saving failed");
        }
    }

  

    private int generateId() {
        int nextId = getNextIdFromFile();
//        updateIdCounter(nextId + 1); // Increment for next use
        return nextId;
    }

    // to-do
    private int getNextIdFromFile() {
        int lastId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader("CustomerReservation.txt"))) { // using parenthesis with try automatically closes the BufferedReader
            while (reader.readLine() != null) {
                lastId++;
            }
        } catch (IOException e) {
            System.err.println("Error reading ID counter file: " + e.getMessage());

        }
        return lastId;
    }

    private void updateIdCounter(int i) {
        try (FileWriter writer = new FileWriter("CustomerReservation.txt")) {
            writer.write(String.valueOf(i));
        } catch (IOException e) {
            System.err.println("Error updating ID counter file: " + e.getMessage());
        }
    }

    private void fileExist() throws IOException {
        File myFile = new File("CustomerReservation.txt");
        try {
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

}
