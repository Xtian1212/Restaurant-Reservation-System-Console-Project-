/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant.reservation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a0916
 */
public class GenerateReport {

    private int getTotalAdultChildrenAmount;
    private int totalAdultChildrenAmountValue = 0;
    private int totalChildren = 0;
    private int totalAdult = 0;

    public int getGetTotalAdultChildrenAmount() {
        return getTotalAdultChildrenAmount;
    }

    public void setGetTotalAdultChildrenAmount(int getTotalAdultChildrenAmount) {
        this.getTotalAdultChildrenAmount = getTotalAdultChildrenAmount;
    }

    public void generateReport() {
        extractNumbers();
        try (BufferedReader reader = new BufferedReader(new FileReader("CustomerReservation.txt"))) {
            String line;
            System.out.println("\t\t\tReport");
            System.out.println("#\t Name\t Date\t\t Time\t\tAdults\t  Children");
            while ((line = reader.readLine()) != null) {
                System.out.println(line + " " + getGetTotalAdultChildrenAmount());
                totalAdultChildrenAmountValue += getGetTotalAdultChildrenAmount();
                
            }
            System.out.println("\nTotal number of Adults: " + totalAdult);
            System.out.println("Total number of Kids: " + totalChildren);
            System.out.println("Grand Total: PHP " + totalAdultChildrenAmountValue);
            System.out.println("----------Nothing Follows---------");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractNumbers() {
        String fileName = "CustomerReservation.txt";
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                extractLastTwoNumbers(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void extractLastTwoNumbers(String line) {
        String[] numbers = line.split("\\s+");
        int len = numbers.length;

        if (len >= 2) {
            String children = numbers[len - 1];
            String adult = numbers[len - 2];

            try {
                int childrenParseValue = Integer.parseInt(children);
                int adultParseValue = Integer.parseInt(adult);
                totalAdult += adultParseValue;
                totalChildren += childrenParseValue;
                setGetTotalAdultChildrenAmount(totalOfAdultAndChild(adultParseValue, childrenParseValue));
            } catch (NumberFormatException e) {
                System.out.println("Invalid numbers found in row.");
            }
        } else {
            System.out.println("Row doesn't have enough numbers.");
        }
    }

    private int totalOfAdultAndChild(int noOfAdultValue, int noOfChildrenValue) {
        return noOfAdultValue * 500 + noOfChildrenValue * 300;
    }
}
