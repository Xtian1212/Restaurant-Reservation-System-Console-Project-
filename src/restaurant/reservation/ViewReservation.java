/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant.reservation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a0916
 */
public class ViewReservation {

    public void viewReservation() {
        
        try (BufferedReader reader = new BufferedReader(new FileReader("CustomerReservation.txt"))){
            String line;
            System.out.println("#\t Name\t Date\t\t Time\t\tAdults  Children");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
