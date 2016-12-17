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

public class CourseGUI {

  public static void main(String [] args) {
    
    // Creates and shows a frame
    JFrame frame = new JFrame("Choose your Major");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel mainPanel = new JPanel(); 
    mainPanel.setLayout(new BorderLayout());
    
    //CoursePanel csPanel = new CoursePanel("CS");

//    JPanel mathPanel = new JPanel();
//    JCheckBox mathCourses = new JCheckBox("MATH 225");
//    mathPanel.add(mathCourses);
//    JPanel masPanel = new JPanel();
//    JCheckBox masCourses = new JCheckBox("ARTS 101");
//    masPanel.add(masCourses);
    frame.getContentPane().add(new CoursePanel("CS.txt"));
//    mainPanel.add(mathPanel, BorderLayout.CENTER);
//    mainPanel.add(masPanel, BorderLayout.EAST);
    
    // Creates Label to ask user question
    Label howMany = new Label("How many courses in each major have you taken?");
    mainPanel.add(howMany, BorderLayout.NORTH);
    
    //frame.add(mainPanel);
    frame.pack();
    frame.setSize(400, 150);
    frame.setVisible(true);
  }
}