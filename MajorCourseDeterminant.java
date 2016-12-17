import java.util.*;
import javafoundations.*;
import java.io.*;

public class MajorCourseDeterminant {
  LinkedList<String> courses;
  public MajorCourseDeterminant(LinkedList coursesTaken){
    this.courses = coursesTaken;
  }
  
  public static boolean mathMajor(LinkedList<String> math) {
    selectionSort(math);
    System.out.println(math);
    return false;
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
    }
  
  public double csMajor(LinkedList<String> cs) {
    selectionSort(cs);
    
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
  }
  
  public static void selectionSort (LinkedList<String> data) {
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
  
  private static void swap (LinkedList<String> data, int index1, int index2) {
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
    
    LinkedList<String> math = new LinkedList<String>();
    math.add("MATH225");
    math.add("MATH116");
    math.add("MATH205");
    math.add("MATH115");
    
    System.out.println(mathMajor(math));
    

  }
  }
