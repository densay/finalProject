/** 
 * SelectCoursesGUI is the class that the user interacts with when he 
 * or she is selecting the courses he or she has taken. It creates three
 * CoursePanel objects each displaying either 100, 200, or 300 level courses
 * within CS, Math, and MAS majors. The three object are placed next to each 
 * other in a border layout format so that the user is able to see his or her options
 * at once. After the user selects a course, the name of the course he or she has 
 * selected is displayed at the bottom of the screen in a text area. Both the 
 * list of courses and the text area are scrollable in case the user has 
 * taken many classes. Once the user is finished selecting the courses he or she
 * has taken, he or she will click the Submit button, which will then prompt the 
 * creation of a text file that contains all the courses the user has selected. 
 * 
 * @ author Katherine Gao
 * 
 * @ version 1.0 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SelectCoursesGUI extends JPanel {
  
  private JButton nextButton;
  private JPanel questionPanel;
  private JPanel buttonPanel;
  private JLabel whichOneCourses;
  private JLabel whichTwoCourses;
  private JLabel whichThreeCourses;
  private LinkedList<String> completedCourses;
  private CoursePanel oneHundred;
  private CoursePanel twoHundred;
  private CoursePanel threeHundred;
  
  
  /** 
   * Sets up the GUI that the user will interact with. Creates three CoursePanel
   * objects from text files containing the 100, 200, and 300 level courses
   * that count for the CS, Math, and MAS majors. Uses a border layout format 
   * manager. Creates three JLabels to instruct the user to choose all 100, 200,
   * and 300 level courses he or she has taken. Creates the submit button that 
   * the user will click on after he or she has selected all of the courses he or
   * she has taken. 
   * 
   * @ param one name of the text file containing 100 level classes
   * @ param two name of the text file containing 200 level classes
   * @ param three name of the text file containing 300 level classes
   * 
   */
  
  public SelectCoursesGUI(String one, String two, String three) {
    // set layout of the panel:
    setLayout(new BorderLayout());
    
    // 
    oneHundred = new CoursePanel(one);
    oneHundred.setPreferredSize(new Dimension(400,200));
    twoHundred = new CoursePanel(two);
    twoHundred.setPreferredSize(new Dimension(400,200));
    threeHundred = new CoursePanel(three);
    threeHundred.setPreferredSize(new Dimension(400,200));
    
    questionPanel = new JPanel();
    questionPanel.setPreferredSize(new Dimension(50, 50));
    questionPanel.setLayout(new BorderLayout());
    whichOneCourses = new JLabel("Select all 100 level classes you have taken");
    whichTwoCourses = new JLabel("Select all 200 level classes you have taken", whichTwoCourses.CENTER);
    whichThreeCourses = new JLabel("Select all 300 level classes you have taken", whichThreeCourses.CENTER);
    questionPanel.add(whichOneCourses, BorderLayout.LINE_START);
    questionPanel.add(whichTwoCourses, BorderLayout.CENTER);
    questionPanel.add(whichThreeCourses, BorderLayout.EAST);
    
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
  
  /**
   * Declares an event handler class that specfies that the class 
   * implements an ActionListener interface 
   */
  
  private class ButtonListener implements ActionListener {
    
    /**
     * Implements the methods in the listener interface. Uses the 
     * getTakenCourses() method from CoursePanel class to get a 
     * linked list of the 100 level classes, 200 level classes, and 
     * 300 level classes. Compiles these lists into one linked list
     * and splits the names of the classes so that only the course 
     * number is stored in the list. Writes the items in this created linked
     * list to a text file so that this information can be retained
     * and used in other classes
     */
    
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
      // Write information into text file to be used in other classes
      writeToFile(completedCourses, "SelectedCourses.txt");
    }
  }
  
  /**
   * Private helper method that writes all items in a linked list to
   * a text file named outputName
   * 
   * @ param list the LinkedList of items to be written to a file
   * @ param name of the text file that will be written
   */
  
  private void writeToFile(LinkedList<String> list, String outputName) {
    try {
      PrintWriter outFile = new PrintWriter(new File(outputName));
      for (int i=0; i<list.size(); i++) {
        outFile.println(list.get(i));
      }
      outFile.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  
  /** 
   * Driver method to test this class
   */
  
  public static void main(String [] args) {
    SelectCoursesGUI allCourses = new SelectCoursesGUI("ALL100.txt", "ALL200.txt", "ALL300.txt"); 
    JFrame frame = new JFrame("Choose your Major");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(allCourses);
    frame.pack();
    frame.setSize(1200, 600);
    frame.setVisible(true);
  }
}