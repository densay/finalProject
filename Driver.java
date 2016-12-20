import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class InformationDriver {
  
  LinkedList<String> selected; 
  
  public InformationDriver(String fileName) {
    selected = userCourses(fileName);
  }
  
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
  
  public void setUpGraph() {
    
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
    
    // creates major course determinants
    MajorCourseDeterminant mcdCS = new MajorCourseDeterminant(csCT, userCSCourses);
    MajorCourseDeterminant mcdMath = new MajorCourseDeterminant(mathCT, userMathCourses);
    MajorCourseDeterminant mcdMAS = new MajorCourseDeterminant(masCT, userMASCourses);
    
    mcdCS.allCSMath("CSRequired.txt");
    mcdMath.allCSMath("MathRequired.txt");
    mcdMAS.allMAS("MASRequired.txt", "MASCS.txt", "MASARTS.txt");

  }
 
   public double [] percentages() {
     double [] results = new double [2];
     results[0] = mcdCS.csMajor();
     results[1] = mcdMath.mathMajor(userMathCourses);
     results[2] = mcdMath.masMajor(userMASCourses);
     return results;
   }
   
   public String [] nextCourse() {
     double temp = percentages();
     String [] results = new String [2];
     if(temp[0] != 1.0) {
       results[0] = mcdCS.nextCourseMathCS();
     }
     if(temp[0] != 1.0) {
       results[1] = mcdMath.nextCourseMathCS();
     }
     if(temp[0] != 1.0) {
       results[2] = mcdMAS.nextCourseMAS();
     }
     return results;
   }

      
  }
