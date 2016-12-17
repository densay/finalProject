import java.util.*;
import java.io.*;
import java.util.Scanner;
import javafoundations.*;
import java.lang.*;

public class MajorCourseDeterminant {
  
  // instance variables
  private LinkedList<String> allCSCourses;
  private LinkedList<String> allMathCourses;
  private LinkedList<String> allMASCourses;
  private LinkedList<String> requiredCSCourses;
  private LinkedList<String> requiredMathCourses;
  private LinkedList<String> requiredMASCourses;
  private final int numCSCoursesForMajor = 11;
  private final int numMathCoursesForMajor = 10;
  private final int numMASCoursesForMajor = 12;
  
  // constructor
  public MajorCourseDeterminant() {
    allCSCourses = new LinkedList<String>();
    allMathCourses = new LinkedList<String>();
    allMASCourses = new LinkedList<String>();
    requiredCSCourses = new LinkedList<String>();
    requiredMathCourses = new LinkedList<String>();
    requiredMASCourses = new LinkedList<String>();
  }
  
  // getter methods
  public LinkedList<String> getAllCSCourses() {
    return allCSCourses;
  }
  
  public LinkedList<String> getAllMathCourses() {
    return allMathCourses;
  }
  
  public LinkedList<String> getAllMASCourses() {
    return allMASCourses;
  }
  
  public LinkedList<String> getRequiredCSCourses() {
    return requiredCSCourses;
  }
  
  public LinkedList<String> getRequiredMathCourses() {
    return requiredMathCourses;
  }
  
  public LinkedList<String> getRequiredMASCourses() {
    return requiredMASCourses;
  }
  
  // fills CS linkedlists from written text files
  public void allCS(String coursesFile, String requiredCoursesFile) {
   
    // fills allCSCourses
    try {
      Scanner scan = new Scanner(new File(coursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        allCSCourses.add(name);
      }
      selectionSort(allCSCourses); // sorts CSCourses by number
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + coursesFile + " was not found.");
    }
    
    // fills requiredCSCourses
    try {
      Scanner scan = new Scanner(new File(requiredCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        requiredCSCourses.add(name);
      }
      selectionSort(requiredCSCourses); // sorts requiredCSCourses by number
    }
      catch (FileNotFoundException e) {
      System.out.println ("The file " + requiredCoursesFile + " was not found.");
    }
    }
  
  // fills Math linkedlists from written text files
  public void allMath(String coursesFile, String requiredCoursesFile) {
    
    // fills allMathCourses
    try {
      Scanner scan = new Scanner(new File(coursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(":");
        allMathCourses.add(nameArray[0]);
      }
      selectionSort(allMathCourses); // sorts mathCourses by number
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + coursesFile + " was not found.");
    }
    
    // fills requiredMathCourses
    try {
      Scanner scan = new Scanner(new File(requiredCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(":");
        requiredMathCourses.add(nameArray[0]);
      }
      selectionSort(requiredMathCourses); // sorts requiredMathCourses by number
    }
      catch (FileNotFoundException e) {
      System.out.println ("The file " + requiredCoursesFile + " was not found.");
    }
    }
  
  // fills MAS linkedlists from written text files
  public void allMAS(String coursesFile, String requiredCoursesFile) {
    
    // fills allMASCourses
    try {
      Scanner scan = new Scanner(new File(coursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        allMASCourses.add(name);
      }
      selectionSort(allMASCourses); // sorts courses by number
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + coursesFile + " was not found.");
    }
    
    // fills requiredMASCourses
    try {
      Scanner scan = new Scanner(new File(requiredCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        requiredMASCourses.add(name);
      }
      selectionSort(requiredMASCourses);
    }
      catch (FileNotFoundException e) {
      System.out.println ("The file " + requiredCoursesFile + " was not found.");
    }
    }
 
  
 public double mathMajor(LinkedList<String> math) {
    selectionSort(math);
    
    int startingIndex = -1; 
    int numOfRequiredCourses = 0;
    int numOfAdditional300s = 2;
    int counter = 0;
    
    // find the first required courses the user has taken
    // this take in account if the user skipped required intro classes
    for(int i = 0; i < math.size(); i++) {
      System.out.println(math.get(i));
      if(requiredMathCourses.contains(math.get(i))) {
        startingIndex = requiredMathCourses.indexOf(math.get(i)); // index of course in required list
        break;
      }
    }
    
    System.out.println(startingIndex);
    numOfRequiredCourses = requiredMathCourses.size() - startingIndex; // calculates the number of required courses
    
    // counts the number of required courses that the user has complete
    for(int i = requiredMathCourses.size()-1; i >= startingIndex; i--) {
        if(math.contains(requiredMathCourses.get(i))) {
          counter++; // counts the number of required courses completed 
          math.remove(requiredMathCourses.get(i)); // removes the required courses the user has completed
        }
      }
    
    System.out.println(counter);
    // counts additional electives taken
    double maxOfEachLevel = numMathCoursesForMajor - numOfRequiredCourses - numOfAdditional300s; //maximum of number of 200/300 courses a user can take that will count towards the major
    System.out.println(maxOfEachLevel);
    LinkedStack<String> num200s = new LinkedStack<String>();
    LinkedStack<String> num300s = new LinkedStack<String>();
    for(int i = 0; i < math.size(); i++) {
      if(math.get(i).contains("MATH 2") && num200s.size() < maxOfEachLevel) {
        num200s.push(math.get(i));
      }
      else if(math.get(i).contains("MATH 3") && num300s.size() < maxOfEachLevel) {
        num300s.push(math.get(i));
      }  
    }
    System.out.println(counter + num200s.size() + num300s.size());
    return (counter + num200s.size() + num300s.size())/numMathCoursesForMajor;
  }
    
    


//    int numOf100s = 0;
//    int numOf200s = 0;
//    int numOf300s = 0;
//    for(int i = 0; i < math.size(); i++) {
//      if(math.get(i).equals("MATH115")) {
//        numOf100s++;
//      }
//      else if(math.get(i).equals("MATH116")) {
//        numOf100s++;
//      }
//      else if(math.get(i).equals("MATH205")) {
//        numOf200s++;
//      }
//      else if(math.get(i).equals("MATH206")) {
//        numOf200s++;
//      }
//      else if(math.get(i).equals("MATH3202")) {
//        numOf300s++;
//      }
//      else if(math.get(i).equals("MATH305")) {
//        numOf300s++;
//      }
//      else if(math.get(i).substring("MATH2")) {
//        numOf200s++;
//      }
//      else if(math.get(i).substring("MATH3")) {
//        numOf300s++;
//      }
//    }
//      // the maximum number of 100 level math courses needed to complete a major are 2
//      // if a student has completed more than 2 that is irrevelent in terms of completion of the major
//      if(numOf100s > 2) {
//      numOf100s = 2;
//      }
//      // the maximum number of 200 level math courses needed to complete a major are 4
//      // if a student has completed more than 4 that is irrevelent in terms of completion of the major
//      if(numOf200s > 4) {
//      numOf100s = 4;
//      }
//      // the maximum number of 300 level math courses needed to complete a major are 6
//      // if a student has completed more than 6 that is irrevelent in terms of completion of the major
//      if(numOf300s > 6) {
//      numOf300s = 6;
//      }
//      double numOfCompletedCourses = (numOf100s + numOf200s + numOf300s)/10.0;
//      return numOfCompletedCourses;
    //}
  
  //public double csMajor(LinkedList<String> cs) {
  //  selectionSort(cs);
    
//    int numOf200s = 0;
//    int numOfMathCourses = 0;
//    int numOf300s = 0;
//    for(int i = 0; i < cs.size(); i++) {
//      if(cs.get(i).equals("CS111")) {
//        numOf200s++;
//      }
//      else if(cs.get(i).equals("CS230")) {
//        numOf200s++;
//      }
//      else if(cs.get(i).equals("CS231")) {
//        numOf200s++;
//      }
//      else if(cs.get(i).equals("CS235")) {
//        numOf200s++;
//      }
//      else if(cs.get(i).equals("CS240")) {
//        numOf200s++;
//      }
//      else if(cs.get(i).equals("CS251")) {
//        numOf200s++;
//      }
//      else if(cs.get(i).substring(0,3).equals("CS3")) {
//        numOf300s++;
//      }
//      // checking for addtional CS course
//      else if(cs.get(i).substring(0,3).equals("CS2")) {
//        numOf200s++;
//      }
//      else if(cs.get(i).equals("MATH225")) {
//        numOfMathCourses++;
//      }
//      else if(cs.get(i).substring(0,5).equals("MATH2") || cs.get(i).substring(0,5).equals("MATH3")) {
//        numOfMathCourses++;
//      }
//    }
//    // the maximum number of 200s needed to complete a major are 7
//    // if a student has completed more than 7 that is irrevelent in terms of completion of the major
//    if(numOf200s > 7) {
//      numOf200s = 7;
//    }
//    // the maximum number of 200s needed to complete a major are 3
//    // if a student has completed more than 3 that is irrevelent in terms of completion of the major
//    if(numOf300s > 3) {
//      numOf300s = 3;
//    }
//    // the maximum number of math courses needed to complete a major are 2
//    // if a student has completed more than 2 that is irrevelent in terms of completion of the major
//    if(numOfMathCourses > 2) {
//      numOfMathCourses = 2;
//    }
//    double numOfCompletedCourses = (numOf200s + numOf300s + numOfMathCourses)/11.0;
//    return numOfCompletedCourses;
  //}
  
  public void selectionSort (LinkedList<String> data) {
      int min;
      for (int index = 0; index < data.size()-1; index++) {
         min = index;
         for (int scan = index+1; scan < data.size(); scan++) {
           if (data.get(scan).compareTo(data.get(min)) < 0) {
               min = scan;
           }
         }
         swap (data, min, index);
      }
  }
  
  public void swap (LinkedList<String> data, int index1, int index2) {
       String index1Data = data.get(index1);
       String index2Data = data.get(index2);
       data.remove(index1);
       data.add(index1, index2Data);
       data.remove(index2);
       data.add(index2, index1Data);
   }
  
  public String nextCourse(CourseTrajectories<String> major)throws GraphCycleException{
    CourseTrajectories<String> temp = major;

    LinkedList <String> graphByP = temp.listByPriority();
    int i = graphByP.indexOf(courses.getLast());
    System.out.println(graphByP.toString());
    
   return graphByP.get(i+1);
}
  
  public static void main(String [] args) {
    
    MajorCourseDeterminant mcd = new MajorCourseDeterminant();
    
    mcd.allCS("CS.txt", "CSRequired.txt");
    mcd.allMath("Math.txt", "MathRequired.txt");
    mcd.allMAS("MAS.txt", "MASRequired.txt");
    
    LinkedList<String> test = new LinkedList<String>();
    test.add("MATH 225");
    test.add("MATH 206");
    test.add("MATH 205");
    test.add("MATH 223");
    test.add("MATH 210");
    
    System.out.println(mcd.mathMajor(test));
//    System.out.println(mcd.getAllCSCourses());
//    System.out.println(mcd.getRequiredCSCourses());
//    System.out.println(mcd.getAllMathCourses());
//    System.out.println(mcd.getRequiredMathCourses());
//    System.out.println(mcd.getAllMASCourses());
//    System.out.println(mcd.getRequiredMASCourses());
//    
    

  }
  }
