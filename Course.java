/** Katherine Gao, Xochitl Say, Hannah Weismann
  * 
  * CS 230 Final Project
  * 
  * 12/14/16
  * 
  * Course.java
  */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Course extends JPanel {
  
  private JButton nextButton;
  private JPanel questionPanel;
  private JPanel buttonPanel;
  private JLabel question1;
  private JLabel question2;
  private JLabel question3;
  private LinkedList<String> completedCourses;
  private CourseNamePanel oneHundred;
  private CourseNamePanel twoHundred;
  private CourseNamePanel threeHundred;
  
  public Course(String one, String two, String three) {
    // set layout of the panel:
    setLayout(new BorderLayout());
    oneHundred = new CourseNamePanel(one);
    oneHundred.setPreferredSize(new Dimension(400,200));
    twoHundred = new CourseNamePanel(two);
    twoHundred.setPreferredSize(new Dimension(400,200));
    threeHundred = new CourseNamePanel(three);
    threeHundred.setPreferredSize(new Dimension(400,200));
    
    questionPanel = new JPanel();
    questionPanel.setPreferredSize(new Dimension(50, 50));
    questionPanel.setLayout(new BorderLayout());
    question1 = new JLabel("How many 100 level classes have you taken?", question1.LEADING);
    question2 = new JLabel("How many 200 level classes have you taken?", question2.CENTER);
    question3 = new JLabel("How many 300 level classes have you taken?", question3.RIGHT);
    questionPanel.add(question1, BorderLayout.WEST);
    questionPanel.add(question2, BorderLayout.CENTER);
    questionPanel.add(question3, BorderLayout.EAST);
    
    buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(25,40));
    nextButton = new JButton("Next");
    nextButton.addActionListener(new ButtonListener());
    buttonPanel.add(nextButton);
    
    add(oneHundred, BorderLayout.WEST);
    add(twoHundred, BorderLayout.CENTER);
    add(threeHundred, BorderLayout.EAST);
    add(questionPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      completedCourses = new LinkedList<String>();
      if (event.getSource() == nextButton) {
        LinkedList<String> first = oneHundred.getTakenCourses();
        LinkedList<String> second = twoHundred.getTakenCourses();
        LinkedList<String> third = threeHundred.getTakenCourses();
        for (int i=0; i<first.size(); i++){
          String class1 = first.get(i);
          String [] class1Array = class1.split(":");
          completedCourses.add(class1Array[0]);
        }
        for (int j=0; j<second.size(); j++) {
          String class2 = second.get(j);
          String [] class2Array = class2.split(":");
          completedCourses.add(class2Array[0]);
        }
        for (int k=0; k<third.size(); k++) {
          String class3 = third.get(k);
          String [] class3Array = class3.split(":");
          completedCourses.add(class3Array[0]);
        }
        for (int t=0; t<completedCourses.size(); t++) {
        System.out.println(completedCourses.get(t));
        }
      }
      // DO STUFF WITH OTHER METHODS:
    }
  }
  
  public LinkedList<String> getCourses() {
    return completedCourses;
  }
  
  
  public static void main(String [] args) {
    Course cs = new Course("ALL100.txt", "ALL200.txt", "ALL300.txt"); 
    JFrame frame = new JFrame("Choose your Major");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(cs);
    frame.pack();
    frame.setSize(1200, 600);
    frame.setVisible(true);
  }
}