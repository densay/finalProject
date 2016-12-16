


public class CreateCoursePaths {
  
  public static void main(String [] args) {
    
    // create graph object
    CourseTrajectories ct = new CourseTrajectories();
    
    // CS courses offered in Spring 2017
    // this list can be changed every semester to reflect the courses being offered that semester
    String[] CSCourses = {"CS111", "CS115", "CS125", "CS220", "CS230", "CS231", "CS235", "CS240", "CS249", "CS251", "CS304", "CS315", "CS349"};
    
    // Math courses offered in Spring 2017
    // this list can be changed every semester to reflect the courses being offered that semester
    String[] MathCourses = {"Math115", "Math116", "Math203", "Math205", "Math206", "Math210", "Math220", "Math221", 
                             "Math223", "Math225", "Math302", "Math305", "Math306","Math310", "Math340", "Math349"};
    
    //MAS courses offered in Spring 2017
    // this list can be changed every semester to reflect the courses being offered that semester
    String[] MASCourses = {"ARTH101", "CAMS101", "ARTS108", "ARTS109", "ARTS165", "ARTS208", "ARTS221", "ARTS255", "ARTS260", 
                           "ARTS265", "MUS275", "ARTS313", "ARTS317", "ARTS321", "ARTS322", "ARTS365", };
    
    // add CS courses to graph
    for(int i = 0; i < CSCourses.length; i++) {
      ct.addVertex(CSCourses[i]);
    }
    
    // add Math courses to graph
    for(int i = 0; i < MathCourses.length; i++) {
      ct.addVertex(MathCourses[i]);
    }
    
    // add MAS courses to graph
    for(int i = 0; i < MASCourses.length; i++) {
      ct.addVertex(MASCourse[i]);
    }
    
    
    // create CS course trajectories
    ct.addArc("CS111", "CS230");
    ct.addArc("CS111", "CS220");
    ct.addArc("CS111", "CS240");
    ct.addArc("CS111", "CS249");
    ct.addArc("CS115", "CS249");
    ct.addArc("CS230", "CS231");
    ct.addArc("CS230", "CS235");
    ct.addArc("CS230", "CS251");
    ct.addArc("CS230", "CS304");
    ct.addArc("CS230", "CS315");
    ct.addArc("CS230", "CS349");
    ct.addArc("CS230", "Math223");
    ct.addArc("CS230", "Math225");
    ct.addArc("CS231", "CS349");
    ct.addArc("Math225", "CS231");
    ct.addArc("Math225", "CS235");
    
    // create Math course trajectories
    ct.addArc("Math115", "Math116");
    ct.addArc("Math116", "Math203");
    ct.addArc("Math116", "Math205");
    ct.addArc("Math116", "Math223");
    ct.addArc("Math116", "Math225");
    ct.addArc("Math205", "Math206");
    ct.addArc("Math205", "Math210");
    ct.addArc("Math205", "Math220");
    ct.addArc("Math206", "Math302");
    ct.addArc("Math206", "Math305");
    ct.addArc("Math220", "Math221");
    ct.addArc("Math302", "Math310");
    ct.addArc("Math302", "Math340");
    ct.addArc("Math305", "Math306");
    ct.addArc("Math305", "Math340");
    ct.addArc("Math302", "Math349");
    
    // create MAS course trajectories
    ct.addArc("ARTS108", "ARTS221");
    ct.addArc("ARTS109", "ARTS221");
    ct.addArc("ARTS165", "ARTS221");
    
  
    
    
    System.out.println(ct);
    

  }
  
}