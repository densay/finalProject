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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.*;
import java.io.*;

public class CourseNamePanel extends JPanel{
  
  private DefaultListModel courseNames;
  private JList courseList;
  private JScrollPane courseListScrollPane;
  private JScrollPane takenCoursesScrollPane;
  private JTextArea takenCourses;

  /* Creates a panel that, given a file of all the courses within
   * a major, will display course names in a list on a 
   * scroll panel. When user clicks submit button, courses selected
   * are shown in a text field at the bottom of the screen
   */
 
  public CourseNamePanel(String major) {
    
    // set layout of panel to box layout so that the course names
    // will be stacked on top of each other in a column
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    //setLayout(new BorderLayout());
    // use file to string helper method to place all course names
    // into a linked list
    LinkedList<String> classes = fileToString(major);
    
    // add each course name to a default list model so that it can
    // be placed into a JList:
    courseNames = new DefaultListModel();
    for (int i=0; i<classes.size(); i++) {
      courseNames.addElement(classes.get(i));
    }
    courseList = new JList(courseNames); 
    
    // set selection mode to multiple selection so that the user
    // can choose more than one class he or she has taken from the list
    courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    courseList.addListSelectionListener(new SharedListSelectionHandler());
    
    takenCourses = new JTextArea(1, 50);
    takenCourses.setEditable(false);

    // put the list onto a scroll pane so that the user can
    // scroll through the list of course names
    courseListScrollPane = new JScrollPane(courseList);
    takenCoursesScrollPane = new JScrollPane(takenCourses);
    
    
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                               courseListScrollPane, takenCoursesScrollPane);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(300);
    
    //Provide minimum sizes for the two components in the split pane
    Dimension minimumSize = new Dimension(50, 50);
    courseListScrollPane.setMinimumSize(minimumSize);
    takenCoursesScrollPane.setMinimumSize(minimumSize);

    add(splitPane);
    
  }
  
  private class SharedListSelectionHandler implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) { 

      if (!e.getValueIsAdjusting()) {
        JList source = (JList)e.getSource();
        String selected = source.getSelectedValue().toString();
        takenCourses.append(selected);
      }
      takenCourses.append("\n");
      takenCourses.setCaretPosition(takenCourses.getDocument().getLength());
    }
  }
  
  public LinkedList<String> getTakenCourses() {
    LinkedList<String> taken = new LinkedList<String>();
    //String courseNames = takenCourses.getText();
    for (String courseNames : takenCourses.getText().split("\n")) {
      taken.add(courseNames);
    }

    return taken;
  }
  
  /* Private helper method to read a file in and convert it to an array 
   * of strings. This is used in the constructor to load all courses as 
   * check boxes in the panel.
   */
  
  private static LinkedList<String> fileToString(String fileName) {
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
  

  /* Driver method to test that the panel creation works
    */
  
  public static void main(String [] args) {
    CourseNamePanel test = new CourseNamePanel("CS.txt");
    JFrame frame = new JFrame("Choose your Major");
    LinkedList<String> list = test.getTakenCourses();
    //System.out.println(list.get(2));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(test);
    frame.pack();
    frame.setSize(550, 500);
    frame.setVisible(true);
  }
  
  
}