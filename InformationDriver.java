import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class InformationDriver {
  /* 
   * This is the driver for determine the next course
   * and the percentage completed of each major
   * this driver is called by AllCourses which is the driver
   * for the whole program
   */
  
  // instance variables
  LinkedList<String> selected;
  private double[] percentageResult;
  private String[] nextCourses;
  
  /*
   * @param takes in a linked list of user courses
   */
  public InformationDriver(LinkedList<String> courses) {
    selected = courses;
    percentageResult = new double[3]; // intializing to size 3 for the 3 majors
    nextCourses = new String[3];
  }
  
  /*
   * method which determines next course and percentage completed
   * this method calls all the methods that necessary to calculate
   * the next course and percentage completed
   */
  public void setUpGraph() {
    
    try {
    // creates course trajectories objects for each major
    // initializing the graphs for each major
    CourseTrajectories csCT = new CourseTrajectories("csCourses.tgf");
    CourseTrajectories mathCT = new CourseTrajectories("mathCourses.tgf");
    CourseTrajectories masCT = new CourseTrajectories("masCourses.tgf");
    
    // creates sorted course objects 
    // these course objects are needed so we can sort courses by major
    SortedCourses cs = new SortedCourses(csCT, selected);
    SortedCourses math = new SortedCourses(mathCT, selected);
    SortedCourses mas = new SortedCourses(masCT, selected);
    
    // sorts course based on major
    // calls a method for SortedCourses
    LinkedList<String> userCSCourses = cs.majorCompletedCourses();
    LinkedList<String> userMathCourses = math.majorCompletedCourses();
    LinkedList<String> userMASCourses = mas.majorCompletedCourses();
    
    // CS
    MajorCourseDeterminant mcdCS = new MajorCourseDeterminant(csCT, userCSCourses);
    mcdCS.allCSMath("CSRequired.txt");
    percentageResult[0] = mcdCS.csMajor(); // calculates percentage completed of CS major
      if(percentageResult[0] != 100.0) { // if they have completed the major, next course isn't called
        nextCourses[0] = mcdCS.nextCourseMathCS();
      }  
    
    
    // Math
    MajorCourseDeterminant mcdMath = new MajorCourseDeterminant(mathCT, userMathCourses);
    mcdMath.allCSMath("MathRequired.txt");
    percentageResult[1] = mcdMath.mathMajor(); // calculates percentage completed of math major
      if(percentageResult[0] != 100.0) { // if they have completed the major, next course isn't called
        nextCourses[1] = mcdMath.nextCourseMathCS();
      }
    
  
    
    // MAS
    MajorCourseDeterminant mcdMAS = new MajorCourseDeterminant(masCT, userMASCourses);
    mcdMAS.allMAS("MASRequired.txt", "MASCS.txt", "MASARTS.txt");
      percentageResult[2] = mcdMAS.masMajor(userMASCourses); // calculates percentage completed of mas major
      if(percentageResult[0] != 100.0) { // if they have completed the major, next course isn't called
        nextCourses[2] = mcdMAS.nextCourseMAS();
      }
    
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file was not found.");
    }
  }
   
  /*
   * getter method for percentages
   * @return array containing the percent completed of each major
   */
   public double [] percentages() {
     return percentageResult;
   }
   
   /*
   * getter method for next course
   * @return array containing the next course for each major
   */
   public String [] nextCourse() {
     return nextCourses;
   }  
  }
