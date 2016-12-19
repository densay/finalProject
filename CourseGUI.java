/** Katherine Gao, Xochitl Say, Hannah Weismann
  * 
  * CS 230 Final Project
  * 
  * 12/2/16
  * 
  * CourseGUI.java
  */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CourseGUI {

  public static void main(String [] args) {
    
    // Creates and shows a frame
    JFrame frame = new JFrame("Choose your Major");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel mainPanel = new JPanel(); 
    mainPanel.setLayout(new BorderLayout());
    
    Course cs = new Course("CS100.txt", "CS200.txt", "CS300.txt", "CS"); 
    LinkedList<String> csCourses = cs.getCourses();
//    if (!(csCourses.size() == 0)) {
//      System.out.println("yes");
//    }
    mainPanel.add(cs, BorderLayout.WEST);

    // Creates Label to ask user question
    JLabel howMany = new JLabel("How many courses in each major have you taken?");
    howMany.setHorizontalAlignment(howMany.CENTER);
    mainPanel.add(howMany, BorderLayout.NORTH);
  
    frame.add(mainPanel);
    frame.pack();
    frame.setSize(1200, 600);
    frame.setVisible(true);
  }
}