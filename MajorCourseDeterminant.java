import java.util.*;
  import java.io.*;
  import java.util.Scanner;
  import javafoundations.*;
  import java.lang.*;
  /*
  * this class contains the important  methods that are used
  * to calculate the next course and percentage of completion based off of 
  * the input
  */
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
  
  // constructor for class 
    /* sets up an instance of the class based off of variables that will be used through out 
    * the rest of the class
    *@param major takes in the graph of the major
    *takes in the completed courses in that major field
    *
    */
  public MajorCourseDeterminant(CourseTrajectories<String> major, LinkedList<String> completedCoursesByMajor) {
    this.major = major;
    userCourses = completedCoursesByMajor;
    requiredCourses = new LinkedList<String>();
    requiredMASIntroCourses = new LinkedList<String>();
    requiredMASArtCourses = new LinkedList<String>();
    requiredMASCSCourses = new LinkedList<String>();
  }
  
  /*
  * populates the linked list of required courses based on req for major
  *@param requiredCoursesFile takes in name of course containing 
  *requirements for that major
  * not return only setting
  */
  public void allCSMath(String requiredCoursesFile) {

    // fills requiredCourses
    try {
      Scanner scan = new Scanner(new File(requiredCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(":");
        requiredCourses.add(nameArray[0]);
      }
      selectionSort(requiredCourses); // sorts requiredCourses by number
    }
      catch (FileNotFoundException e) {
      System.out.println ("The file " + requiredCoursesFile + " was not found.");
    }
    }
  
  /*
  * populates the  linked list based off of 3 distinct requirements
  *@param introCoursesFile all are contains requirements pertaining to specified label
  *@param csCourses
  *@param artCourses
  */
  public void allMAS(String introCoursesFile, String csCourses, String artCourses) {
    
    // fills introMASCourses
    try {
      Scanner scan = new Scanner(new File(introCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(":");
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
        String [] nameArray = name.split(":");
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
          String[] nameArray = name.split(":");
          requiredMASArtCourses.add(nameArray[0]);
        }
        selectionSort(requiredMASArtCourses);
      }
      catch (FileNotFoundException e) {
        System.out.println ("The file " + artCourses + " was not found.");
      }
      
    }
 /*
 * method calculates percentage of cs major 
 * @return double based off of cs major requirements
 *
 */
   public double csMajor(){
     
     //allCSMath("CSRequired.txt");

    double count = 0;

    double twoLevel= 0;
    double threeLevel = 0;
    double math = 0;
    double reqUpLvl = 3;
    System.out.println(userCourses.toString());
    if(!userCourses.contains("CS 111") && userCourses.contains("CS 230")){
       reqUpLvl = 4;
    }
    
    System.out.println("req courses" + requiredCourses.toString());
    for(int i = 0; i< requiredCourses.size(); i++){

      if(userCourses.contains(requiredCourses.get(i))){
       count++;
      }
    }
    for(int i = 0; i< userCourses.size(); i++){
      if(threeLevel + twoLevel< reqUpLvl && !requiredCourses.contains(userCourses.get(i))){
        if (userCourses.get(i).contains("CS 3")){
          threeLevel++;
          count++;
        }else if((userCourses.get(i)).contains("CS 2")&& twoLevel< reqUpLvl - 2){
        twoLevel++; 
        count++;
        }
      }else if(math<1){
        math++;
        count++;
       }
      }
   
      return (count/numCSCoursesForMajor);
    }
  
   /*
 * method calculates percentage of math major 
 * @return double based off of math major requirements
 *
 */
  public double mathMajor(LinkedList<String> math) {
    

    //allCSMath("MathRequired.txt");
    
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
   /*
 * method calculates percentage of masMajor major 
 * @return double based off of cs major requirements
 *
 */
   public double masMajor(LinkedList<String> mas) {

    //allMAS("MASRequired.txt", "MASCS.txt", "MASARTS.txt");
    
    selectionSort(mas); // sorts the courses in ascending order
   
    
    int intro = 0; // maximum is 4
    int artCourses = 0; 
    int csCourses = 0; 
    int num300s = 0;
    int required300s = 2; // the number of required 300s the user must take
    
    for(int i = 0; i < mas.size(); i++) {
      if(requiredMASIntroCourses.contains(mas.get(i)) && intro < 4) {
        intro++;
      }
      else if(requiredMASCSCourses.contains(mas.get(i)) && csCourses + artCourses < numMASCoursesForMajor - intro - required300s) {
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
  
  /*
  * method sorts incoming data in ascending order
  * @param linkedlist containing course names
  */
    
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
  
  /* 
  * help method for selectionSort
  * @param takes in linkedlist of courses
  * @param takes in 2 indices that need to switched
  */
    
  public void swap (LinkedList<String> data, int index1, int index2) {
       String index1Data = data.get(index1);
       String index2Data = data.get(index2);
       data.remove(index1);
       data.add(index1, index2Data);
       data.remove(index2);
       data.add(index2, index1Data);
   }
  
  /*
  *method that goes through the selected/separated courses and determines what courses are left to take 
  * then it returns the courses left to take, giving priority to those that fulfill requirements
  *@return string subject and number of course
  */
   public String nextCourseMathCS() {

    CourseTrajectories<String> temp = major;
    LinkedList<String> req = requiredCourses;//both temp
    LinkedList<String> selected = userCourses;//both temp
    String course= "";
    
    System.out.print("req");
    System.out.println(req);
    System.out.println(selected);
    req = adjReqMet();//updates to check if any courses selected were skipped
//     //handles accordingly
//    temp = major;
    
    while(!selected.isEmpty()){
     String current = selected.removeFirst();
     
     if(req.contains(current)){
      req.remove(current); 
     }
     
     temp.removeVertex(current);
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
     course = temp.allSources().pop();
    }
    
    
   

    return course;
  }
/*
* method that return linked list which adjust requirement based off special cases where student skips courses that are
*prereq and major req
*@return update linked list of requirements that are left
*/
   public LinkedList<String> adjReqMet(){
    LinkedList<String> adjustedReq = requiredCourses;
    for(int i = 0; i<userCourses.size(); i++){
      LinkedList<String> preReq = (major.getPredecessors(userCourses.get(i)));//gets predecessors of requirement if it's skipped(for special cases)
      if(!preReq.isEmpty()){
        for(int j =0; j<preReq.size(); j++){
          if(requiredCourses.contains(preReq.get(j)) && !userCourses.contains(preReq.get(j))){
           major.removeVertex(preReq.get(j));
           requiredCourses.remove(preReq.get(j));
          }
            
        }
      }
    }
    return adjustedReq;
  }
    /*
    *method that handles mas differently because of different course requirements
    *@return next course as string
    */
    public String nextCourseMAS() {   CourseTrajectories<String> temp = major;

    LinkedList<String> selected = userCourses;//both temp
    String course= "";

    while(!selected.isEmpty()){
     String current = selected.removeFirst();
     
     temp.removeVertex(current);
    }
    
  
     LinkedList<String> canTake = temp.allSources();
     System.out.println(canTake.toString());
     course = canTake.pop();
    
    
     
     
    return course;
  }

//CourseTrajectories
  /*
 *
 *TEST CASES**********************
 */
  public static void main(String [] args) throws FileNotFoundException, GraphCycleException {
    
    LinkedList<String> userMathCourses = new LinkedList<String>();
    LinkedList<String> userMASCourses = new LinkedList<String>();
    LinkedList<String> userCSCourses = new LinkedList<String>();
    
    CourseTrajectories csCT = new CourseTrajectories("csCourses.tgf");
    CourseTrajectories mathCT = new CourseTrajectories("mathCourses.tgf");
    CourseTrajectories masCT = new CourseTrajectories("masCourses.tgf");
    
    MajorCourseDeterminant mcdCS = new MajorCourseDeterminant(csCT, userCSCourses);
    MajorCourseDeterminant mcdMath = new MajorCourseDeterminant(mathCT, userMathCourses);
    MajorCourseDeterminant mcdMAS = new MajorCourseDeterminant(masCT, userMASCourses);
    
    
    mcdCS.allCSMath("CSRequired.txt");
    mcdMath.allCSMath("MathRequired.txt");
    mcdMAS.allMAS("MASRequired.txt", "MASCS.txt", "MASARTS.txt");
    
    userCSCourses.add("CS 230");
    userMathCourses.add("MATH 205");
    userMASCourses.add("ARTH 101");
    
   System.out.println(mcdCS.csMajor());
   System.out.println(mcdCS.nextCourseMathCS());
   System.out.println(mcdMath.nextCourseMathCS());
   System.out.println(mcdMAS.nextCourseMathCS());
  }
  }
