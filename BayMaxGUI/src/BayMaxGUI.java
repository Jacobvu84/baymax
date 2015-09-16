
import gui.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hyungin Choi
 */
public class BayMaxGUI {

    private static String title = "BayMax GUI";

    public static void main(String[] args) {
        MainGUI mainFrame;
        try {
            mainFrame = new MainGUI(title);
            mainFrame.setVisible(true);
        } catch (InstantiationException ex) {
            Logger.getLogger(BayMaxGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
