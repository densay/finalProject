import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import javafoundations.*;
import java.lang.*;

public class MajorCourseDeterminant {
  
  // instance variables
  private LinkedList<String> allCSCourses;
  private LinkedList<String> allMathCourses;
  private LinkedList<String> allMASCourses;
  private LinkedList<String> requiredCSCourses;
  private LinkedList<String> requiredMathCourses;
  private LinkedList<String> requiredMASIntroCourses;
  private LinkedList<String> requiredMASArtCourses;
  private LinkedList<String> requiredMASCSCourses;
  private final double numCSCoursesForMajor = 11.0;
  private final double numMathCoursesForMajor = 10.0;
  private final double numMASCoursesForMajor = 12.0;
  
  // constructor
  public MajorCourseDeterminant() {
    // intializing linkedlists
    allCSCourses = new LinkedList<String>();
    allMathCourses = new LinkedList<String>();
    allMASCourses = new LinkedList<String>();
    requiredCSCourses = new LinkedList<String>();
    requiredMathCourses = new LinkedList<String>();
    requiredMASIntroCourses = new LinkedList<String>();
    requiredMASArtCourses = new LinkedList<String>();
    requiredMASCSCourses = new LinkedList<String>();

    
    // filling linkedlist
    allCS("CS.txt", "CSRequired.txt");
    allMath("Math.txt", "MathRequired.txt");
    allMAS("MAS.txt", "MASRequired.txt", "MASCS.txt", "MASARTS.txt");
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
  public void allMAS(String coursesFile, String introCoursesFile, String csCourses, String artCourses) {
    
    // fills allMASCourses
    try {
      Scanner scan = new Scanner(new File(coursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(":");
        allMASCourses.add(nameArray[0]);
      }
      selectionSort(allMASCourses); // sorts courses by number
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + coursesFile + " was not found.");
    }
    
    // fills introMASCourses
    try {
      Scanner scan = new Scanner(new File(introCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(": ");
        requiredMASIntroCourses.add(nameArray[0]);
      }
      selectionSort(requiredMASIntroCourses);
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + introCoursesFile + " was not found.");
    }
      
      // fills CS courses for MAS major
      try {
      Scanner scan = new Scanner(new File(csCourses));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String [] nameArray = name.split(": ");
        requiredMASCSCourses.add(nameArray[0]);
      }
      selectionSort(requiredMASCSCourses);
    }
      catch (FileNotFoundException e) {
      System.out.println ("The file " + csCourses + " was not found.");
    }
      
      // fills Arts courses for MAS major
      try {
        Scanner scan = new Scanner(new File(artCourses));
        while(scan.hasNext()) {
          String name = scan.nextLine();
          String[] nameArray = name.split(": ");
          requiredMASArtCourses.add(nameArray[0]);
        }
        selectionSort(requiredMASArtCourses);
      }
      catch (FileNotFoundException e) {
        System.out.println ("The file " + artCourses + " was not found.");
      }
      
    }
 
  
  public double mathMajor(LinkedList<String> math) {
    
    selectionSort(math); // sorts the courses in ascending order
    
    // local variables
    int startingIndex = -1; 
    int numOfRequiredCourses = 0;
    int numOfAdditional300s = 2;
    int counter = 0;
    LinkedStack<String> num200s = new LinkedStack<String>();
    LinkedStack<String> num300s = new LinkedStack<String>();
    
    // find the first required courses the user has taken
    // this take in account if the user skipped required intro classes;
    for(int i = 0; i < math.size(); i++) {
      if(requiredMathCourses.contains(math.get(i))) {
        startingIndex = requiredMathCourses.indexOf(math.get(i)); // index of course in required list
        break;
      }
    }
    
    numOfRequiredCourses = requiredMathCourses.size() - startingIndex; // calculates the number of required courses
                                                                       // that the user must take for the major
    
    // counts the number of required courses that the user has completed
    for(int i = requiredMathCourses.size()-1; i >= startingIndex; i--) {
        if(math.contains(requiredMathCourses.get(i))) {
          counter++; 
          math.remove(requiredMathCourses.get(i)); // removes the required courses the user has completed
        }
      }
    
    // counts additional electives taken
    double maxOfEachLevel = numMathCoursesForMajor - numOfRequiredCourses - numOfAdditional300s; //maximum of number of 200/300 courses a 
                                                                                                //user can take that will count towards the major
    // goes throught the remaining courses
    for(int i = 0; i < math.size(); i++) {
      if(math.get(i).contains("MATH 2") && num200s.size() < maxOfEachLevel) {
        num200s.push(math.get(i));
      }
      else if(math.get(i).contains("MATH 3") && num300s.size() < maxOfEachLevel) {
        num300s.push(math.get(i));
      }  
    }
    return (counter + num200s.size() + num300s.size())/numMathCoursesForMajor;
  }
  
   public double masMajor(LinkedList<String> mas) {
    
    selectionSort(mas); // sorts the courses in ascending order
    
    System.out.println(requiredMASCSCourses);
    System.out.println(requiredMASIntroCourses);
    
    int intro = 0; // maximum is 4
    int artCourses = 0; 
    int csCourses = 0; 
    int num300s = 0;
    int required300s = 2; // the number of required 300s the user must take
    
    for(int i = 0; i < mas.size(); i++) {
      if(requiredMASIntroCourses.contains(mas.get(i)) && intro < 4) {
        intro++;
        System.out.println(mas.get(i));
      }
      else if(requiredMASCSCourses.contains(mas.get(i)) && csCourses + artCourses < numMASCoursesForMajor - intro - required300s) {
        System.out.println(mas.get(i));
        if(mas.get(i).contains(" 3")) {
          num300s++;
        }
        else {
          csCourses++;
          System.out.println(mas.get(i));
        }
      }
      else if(requiredMASArtCourses.contains(mas.get(i)) && csCourses + artCourses < numMASCoursesForMajor - intro - required300s) {
        if(mas.get(i).contains(" 3")) {
          num300s++;
        }
        else {
          artCourses++;
        }
      }
    }
    return (intro + num300s + csCourses + artCourses)/numMASCoursesForMajor;
   }
//    
//    if((intro + artCourses + csCourses > numMASCoursesForMajor)
//    
//    // local variables
//    int numArt100 = 0; // maximum is 2
//    int numArtHistory100 = 0; // maximum is 2
//    int numCS100 = 0; // maximum is 2
//    int numArt = 0; // maximum is 5
//    int numArtHistory = 0; // maxiumum is 2
//    int numCS = 0; // maximum is 5
//    int num300s = 0;
//
//    
//    for(int i = 0; i < mas.size(); i++) {
//      String course = math.get(i);
//      if(course.contains("ARTH 1") || course.contains("CAMS 1")) {
//        numArtHistory100++;
//      }
//      else if(course.contains("ARTS")) {
//        if(course.contains(" 1") && numArt < 2) {
//          numArt100++;
//        }
//        else if(course.contains(" 3")) {
//          num300s++;
//        }
//        else {
//          numArt++;
//        }
//      }
//        else if(course.contains("CS")) {
//        if(course.contains(" 1") && numCS < 2) {
//          numCS100++;
//        }
//        else if(course.contains(" 3")) {
//          num300s++;
//        }
//        else {
//          numCS++;
//        }
//      }
//    }
//    
//    int totalCoursesTaken =  numArt100 + numArtHistory100 + numCS100 + numArt + numArtHistory + numCS + num300s;
//    if(totalCoursesTaken > 12
//    
//    
//    // local variables
//    int startingIndex = -1; 
//    int numOfRequiredCourses = 0;
//    int numOfAdditional300s = 2;
//    int numOfRequiredElective = 3;
//    int counter = 0;
//    LinkedList<String> numArts = new LinkedList<String>();
//    LinkedList<String> numCS = new LinkedList<String>();
//    LinkedList<String> numArtHistory = new LinkedList<String>();
//    LinkedStack<String> num300s = new LinkedStack<String>();
//    
//    for(int i = 0; i < mas.size(); i++) {
//      if(mas.get(i).contains("CS")) {
//        numCS.add(mas.get(i));
//      }
//      else if(mas.get(i).contains("ARTS")) {
//        numArts.add(mas.get(i));
//      }
//      else if(mas.get(i).contains("ARTH") || mas.get(i).contains("CAM")) {
//        numArtHistory.add(mas.get(i));
//      }
//    }
//    
//    
//    
//    
//      // checks if the user has taken an inductory art classes
//      // at most 2 intro arts can count towards the major
//      for(int i = 0; i < requiredMASArtCourses.size(); i++) {
//        if(math.contains(requiredMASArtCourses.get(i)) && num100s.size() < 2) {
//          num100s.push(requiredMASArtCourses.get(i));
//          math.remove(requiredMASArtCourses.get(i));
//        }
//      }
//      
//      // checks if the user has taken an inductory art history/cams classes
//      for(int i = 0; i < requiredMASArtHistoryCourses.size(); i++) {
//        if(math.contains(requiredMASArtHistoryCourses.get(i))) {
//          num100s.push(requiredMASArtHistoryCourses.get(i));
//          math.remove(requiredMASArtHistoryCourses.get(i));
//        }
//      }
//      
//      // checks if the user has taken an inductory cs classes
//      for(int i = 0; i < requiredMASCSCourses.size(); i++) {
//        if(math.contains(requiredMASCSCourses.get(i)) && num100s.size() < 4) {
//          num100s.push(requiredMASCSCourses.get(i));
//          math.remove(requiredMASCSCourses.get(i));
//        }
//      }
//      
//      numOfRequiredCourses = numMASCoursesForMajor - num100s.size() - numOfAdditional300s
//      // checks for taken electives
//      for(int i = 0; i < mas.size(); i++) {
//        if(mas.get(i).contains("CS 1") && {
//          numCS.add(mas.get(i));
//        }
//      }
//  
  
  
    
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
    int i = graphByP.indexOf(allCSCourses.getLast());
    System.out.println(graphByP.toString());
    
   return graphByP.get(i+1);
}
  
  public static void main(String [] args) {
    
    MajorCourseDeterminant mcd = new MajorCourseDeterminant();
    
    LinkedList<String> test = new LinkedList<String>();
    test.add("CS 111");
    test.add("CS 114");
    test.add("CS 115");
    test.add("CS 220");
    test.add("CS 320");
    test.add("ARTH 101");

    
    System.out.println(mcd.masMajor(test));
//    System.out.println(mcd.getAllCSCourses());
//    System.out.println(mcd.getRequiredCSCourses());
//    System.out.println(mcd.getAllMathCourses());
//    System.out.println(mcd.getRequiredMathCourses());
//    System.out.println(mcd.getAllMASCourses());
//    System.out.println(mcd.getRequiredMASCourses());
//    
    

  }
  }