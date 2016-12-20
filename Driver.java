import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Driver {
  
  public static LinkedList<String> userCourses(String fileName) {
    LinkedList<String> result = new LinkedList<String>();
    try {
      Scanner scan = new Scanner(new File(fileName));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        if(!name.isEmpty()) {
          result.add(name);
        }
      }
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + fileName + " was not found.");
    }
    return result;
  }
  
  public static void main(String [] args) throws FileNotFoundException, GraphCycleException{
    
  
//  // calls the GUI
//    AllCourses allCourses = new AllCourses("ALL100.txt", "ALL200.txt", "ALL300.txt"); 
//    JFrame frame = new JFrame("Choose your Major");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.getContentPane().add(allCourses);
//    frame.pack();
//    frame.setSize(1200, 600);
//    frame.setVisible(true);
    
    // creates a linked list containing all the courses the use has taken
    LinkedList<String> selected = userCourses("SelectedCourses.txt");
    
    System.out.println(selected);
    
    // creates course trajectories objects for each major
    CourseTrajectories csCT = new CourseTrajectories("csCourses.tgf");
    CourseTrajectories mathCT = new CourseTrajectories("mathCourses.tgf");
    CourseTrajectories masCT = new CourseTrajectories("masCourses.tgf");
    
    // creates sorted course objects 
    SortedCourses cs = new SortedCourses(csCT, selected);
    SortedCourses math = new SortedCourses(mathCT, selected);
    SortedCourses mas = new SortedCourses(masCT, selected);
    
    // sorts course based on major
    LinkedList<String> userCSCourses = cs.majorCompletedCourses();
    LinkedList<String> userMathCourses = math.majorCompletedCourses();
    LinkedList<String> userMASCourses = mas.majorCompletedCourses();
    
    System.out.println(userCSCourses);
    System.out.println(userMathCourses);
    System.out.println(userMASCourses);
      
    MajorCourseDeterminant mcdCS = new MajorCourseDeterminant(csCT, userCSCourses);
    MajorCourseDeterminant mcdMath = new MajorCourseDeterminant(mathCT, userMathCourses);
    MajorCourseDeterminant mcdMAS = new MajorCourseDeterminant(masCT, userMASCourses);
    
    mcdCS.allCSMath("CSRequired.txt");
    mcdMath.allCSMath("MathRequired.txt");
    mcdCS.allMAS("MASRequired.txt", "MASCS.txt", "MASARTS.txt");
    
   //System.out.println(mcdCS.CSMajor());
   System.out.println(mcdMath.mathMajor(userMathCourses));
   System.out.println(mcdMath.masMajor(userMASCourses));   
   
   System.out.println(mcdCS.nextCourse());
   System.out.println(mcdMath.nextCourse());
   System.out.println(mcdMAS.nextCourse());
    
      
  }
}