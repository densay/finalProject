import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import javafoundations.*;
import java.lang.*;

public class MajorCourseDeterminant {
  
  // instance variables
  
  private LinkedList<String> requiredCourses; // for math and cs
  private CourseTrajectories<String> major;
  private LinkedList<String> requiredMASIntroCourses;
  private LinkedList<String> requiredMASArtCourses;
  private LinkedList<String> requiredMASCSCourses;
  
  private LinkedList<String> userCourses;
  
  private final double numCSCoursesForMajor = 11.0;
  private final double numMathCoursesForMajor = 10.0;
  private final double numMASCoursesForMajor = 12.0;
  
  // constructor for math and cs
  public MajorCourseDeterminant(CourseTrajectories<String> major, LinkedList<String> completedCoursesByMajor) {
    this.major = major;
    userCourses = completedCoursesByMajor;
    requiredCourses = new LinkedList<String>();
    requiredMASIntroCourses = new LinkedList<String>();
    requiredMASArtCourses = new LinkedList<String>();
    requiredMASCSCourses = new LinkedList<String>();
  }
  
  
  // fills CS or Math linkedlists from written text files
  public void allCSMath(String requiredCoursesFile) {

    // fills requiredCourses
    try {
      Scanner scan = new Scanner(new File(requiredCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        requiredCourses.add(name);
      }
      selectionSort(requiredCourses); // sorts requiredCourses by number
    }
      catch (FileNotFoundException e) {
      System.out.println ("The file " + requiredCoursesFile + " was not found.");
    }
    }
  
  
  // fills MAS linkedlists from written text files
  public void allMAS(String introCoursesFile, String csCourses, String artCourses) {
    
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
 
   public double CSMajor (){
    double count = 0;

    int twoLevel= 0;
    int threeLevel = 0;
    int math = 0;
    int reqUpLvl = 3;
    
    if(!userCourses.contains("CS 111") && userCourses.contains("CS 230")){
       reqUpLvl = 4;
    }
    for(int i = 0; i< cs.size(); i++){

      if(requiredCourses.contains(cs.get(i))){
       count++;
      }else if(threeLevel + twoLevel< reqUpLvl){
        if (cs.get(i).contains("CS 3")){
          threeLevel++;
          count++;
        }
                        
       }else if(cs.get(i).contains("CS 2") && !requiredCourses.contains(cs.get(i)) && twoLevel< reqUpLvl - 2){
        twoLevel++; 
        count++;
       }else if(math<1){
        math++;
        count++;
       }
      }
      return (count/numCSCoursesForMajor);
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
      if(requiredCourses.contains(math.get(i))) {
        startingIndex = requiredCourses.indexOf(math.get(i)); // index of course in required list
        break;
      }
    }
    
    numOfRequiredCourses = requiredCourses.size() - startingIndex; // calculates the number of required courses
                                                                       // that the user must take for the major
    
    // counts the number of required courses that the user has completed
    for(int i = requiredCourses.size()-1; i >= startingIndex; i--) {
        if(math.contains(requiredCourses.get(i))) {
          counter++; 
          math.remove(requiredCourses.get(i)); // removes the required courses the user has completed
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
  
  
   public String nextCourse()throws GraphCycleException{
   
    LinkedList<String> next = new LinkedList<String>();
    String highReq = "";
    
    LinkedList<String> req = requiredCourses;//both temp
    LinkedList<String> selected = userCourses;//both temp
    String course= "";
    
     req = adjReqMet(req, selected);//updates to check if any courses selected were skipped
     //handles accordingly
    
    for(!selected.isEmpty()){
     String current = selected.removeFirst();
     
     if(req.contains(current)){
      req.remove(current); 
     }
     
     major.removeVertex(current);
    }
    
    if(!req.isEmpty()){
     LinkedList<String> canTake = major.allSources();
     for(int i = 0; i< canTake.size(); i++){
       if(req.contains(canTake.get(i))){
        course = canTake.get(i);
        break;
       }
     }
     
    }else{
     course = major.allSources().pop();
    }
    return course;
  }

  public LinkedList<String> adjReqMet(LinkedList<String> req, LinkedList<String> selected){
    LinkedList<String> adjustedReq = req;
    for(int i = 0; i<completedCourses.size(); i++){
      LinkedList<String> preReq = (major.getPredecessors(completedCourses.get(i)));//gets predecessors of requirement if it's skipped(for special cases)
      if(!preReq.isEmpty()){
        for(int i =0; i<requiredCourses.size(); i++){
          if(requiredCourses.contains(preReq.get(i)) && !selected.contains(preReq.get(i))){
           major.removeVertex(preReq.get(i));
           requiredCourses.remove(preReq.get(i));
          }
            
        }
      }
    }
    return adjustedReq;
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


  }
  }
