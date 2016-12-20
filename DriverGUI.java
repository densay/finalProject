/** 
 * DriverGUI is where the program is run. 
 * 
 * @ author Katherine Gao, 
 * 
 * @ version 1.0 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DriverGUI {
  
  /**
   * Main driver class creates a SelectCoursesGUI object that the user will
   * interact with and use to select the courses he or she has taken. It then
   * creates an ___ class object that will use the text file created from SelectCoursesGUI
   * to determine the percentage of the major the user has completed and suggested 
   * next courses in each major. These will be displayed using an AllResultsDialog 
   * object in the form of a dialog box that pops up and closes once the user clicks
   * the quit button
   * 
   */
  
  public static void main(String [] args) {
    // creates and shows a Frame 
    JFrame frame = new JFrame("Choose Your Major");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    SelectCoursesGUI inputGUI = new SelectCoursesGUI("ALL100.txt", "ALL200.txt", "ALL300.txt");
    
    // call class that will take in text file and determine the percentage of each
    // major the user has completed and suggest the next course the user should take
    //InformationDriver information = new InformationDriver("SelectedCourses.txt");
    
    //AllResultsDialog results = new AllResultsDialog(inputGUI, )
    
    frame.getContentPane().add(inputGUI);
    frame.pack();
    frame.setSize(1200, 600);
    frame.setVisible(true);
  }
  
}