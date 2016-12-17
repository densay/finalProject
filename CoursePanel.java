/** Katherine Gao, Xochitl Say, Hannah Weismann
  * 
  * CS 230 Final Project
  * 
  * 12/10/16
  * 
  * CoursePanel.java
  */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class CoursePanel {
  
  private JPanel coursePanel;
  
  public CoursePanel(String major) {
    coursePanel = new JPanel();
    coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
    LinkedList<String> classes = fileToString(major);
    for (int i=0; i<classes.size(); i++) {
      JCheckBox course = new JCheckBox(classes.get(i));
      coursePanel.add(course);
    }
  }
  
  
  /* Private helper method to read a file in and convert it to an array 
   * of strings. This is used in the constructor to load all courses as 
   * check boxes in the panel.
   */
  
  private static LinkedList fileToString(String fileName) {
    LinkedList<String> list = new LinkedList<String>(); 
    try {
      Scanner scan = new Scanner(new File(fileName));
      while (scan.hasNext()){
        String line = scan.nextLine();
        list.add(line);
      }
      return list;
    } catch (FileNotFoundException ex) {
      System.out.println(ex);
      return list;
    }
  }
  
  public static void main(String [] args) {
    //CoursePanel test = new CoursePanel("CS.txt");
    JFrame frame = new JFrame("Choose your Major");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new CoursePanel("CS.txt"));
    frame.pack();
    frame.setSize(400, 150);
    frame.setVisible(true);
  }
  
  
}