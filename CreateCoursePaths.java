
import java.util.*;
import java.io.*;

public class CreateCoursePaths {
  
  public static void main(String [] args) throws FileNotFoundException{
    
    // create graph objects
    CourseTrajectories ct = new CourseTrajectories();
    CourseTrajectories csCT = new CourseTrajectories("csCourses.tgf");
    CourseTrajectories mathCT = new CourseTrajectories();
    CourseTrajectories masCT = new CourseTrajectories();
    
    //csCT.fromTGF("csCourses.tgf");
    System.out.println(csCT);
    
    
    
//    // CS courses offered
//    String[] CSCourses = {"CS111", "CS112", "CS114" ,"CS115", "CS117", "CS125", "CS215", "CS220", "CS230", "CS231",
//                          "CS232", "CS235", "CS240", "CS242", "CS24901", "CS24902", "CS251", "CS301", "CS304", 
//                          "CS307", "CS310", "CS313", "CS315", "CS320", "CS332", "CS342", "CS34901", "CS34902"};
//    
//    // Math courses offered
//    String[] MathCourses = {"Math101", "Math115", "Math116", "Math120", "Math203", "Math205", "Math206", "Math210", "Math214",
//                            "Math215", "Math220", "Math221", 
//                            "Math223", "Math225", "Math249", "Math302", "Math303",  "Math305", "Math306", "Math307", "Math309",
//                            "Math310", "Math312", "Math322", "Math325", "Math326", "Math340", "Math349"};
//    
//    //MAS courses offered in Spring 2017
//    // this list can be changed every semester to reflect the courses being offered that semester
//    String[] MASCourses = {"ARTH101", "CAMS101", "ARTS108", "ARTS109", "ARTS165", "ARTS208", "ARTS221", "ARTS255", "ARTS260", 
//                           "ARTS265", "MUS275", "ARTS313", "ARTS317", "ARTS321", "ARTS322", "ARTS365", };
//    
//    // add CS courses to graph
//    for(int i = 0; i < CSCourses.length; i++) {
//      ct.addVertex(CSCourses[i]);
//    }
//    
//    // add Math courses to graph
//    for(int i = 0; i < MathCourses.length; i++) {
//      ct.addVertex(MathCourses[i]);
//    }
//    
//    // add MAS courses to graph
//    for(int i = 0; i < MASCourses.length; i++) {
//      ct.addVertex(MASCourses[i]);
//    }
//    
//    
//    // create CS course trajectories
//    
//    // Prerequisites for CS215
//    ct.addArc("CS111", "CS215");
//    ct.addArc("ARTS105", "CS215");
//    ct.addArc("ARTS108", "CS215");
//    ct.addArc("ARTS109", "CS215");
//    
//    // Prerequisites for CS220
//    ct.addArc("CS111", "CS220");
//    ct.addArc("CS112", "CS220");
//    ct.addArc("CS117", "CS220");
//    
//    // Prerequisites for C2330
//    ct.addArc("CS111", "CS230");
//    
//    // Prerequisites for CS231
//    ct.addArc("CS230", "CS231");
//    ct.addArc("Math225", "CS231");
//    
//    // Prerequisites for CS232
//    ct.addArc("CS230", "CS232");
//    
//    // Prerequisites for CS235
//    ct.addArc("CS230", "CS235");
//    ct.addArc("Math225", "CS235");
//    
//    // Prerequisites for CS240
//    ct.addArc("CS111", "CS240");
//    ct.addArc("CS112", "CS240");
//    
//    // Prerequisites for CS242
//    ct.addArc("CS230", "CS242");
//    
//    // Prerequisites for CS24901
//    ct.addArc("CS230", "CS24901");
//    
//    // Prerequisites for CS24902
//    ct.addArc("CS111", "CS24902");
//    ct.addArc("CS114", "CS24902");
//    
//    // Prerequisites for CS251
//    ct.addArc("CS230", "CS251");
//    
//    // Prerequisites for CS301
//    ct.addArc("CS230", "CS301");
//    ct.addArc("CS240", "CS301"); 
//    ct.addArc("CS251", "CS301");
//    
//    // Prerequisites for CS304
//    ct.addArc("CS230", "CS304");
//    
//    // Prerequisites for CS307
//    ct.addArc("CS230", "CS307");
//    
//    // Prerequisites for CS310
//    ct.addArc("CS231", "CS310");
//    ct.addArc("CS235", "CS310");
//    
//    // Prerequisites for CS313
//    ct.addArc("CS230", "CS313");
//    
//    // Prerequisites for CS315
//    ct.addArc("CS230", "CS315");
//    
//    // Prerequisites for CS320
//    ct.addArc("CS115", "CS320");
//    ct.addArc("CS220", "CS320");
//    ct.addArc("CS230", "CS320");
//    
//    // Prerequisites for CS332
//    ct.addArc("CS112", "CS332");
//    ct.addArc("CS230", "CS332");
//    
//    // Prerequisites for CS342
//    ct.addArc("CS230", "CS342");
//    ct.addArc("CS240", "CS342");
//    
//    // Prerequisites for CS34901
//    ct.addArc("CS231", "CS34901");
//    ct.addArc("CS242", "CS34901");
//    
//    // Prerequisites for CS34902
//    ct.addArc("CS230", "CS34902");
//    ct.addArc("Math206", "CS34902");
//    ct.addArc("Math220", "CS34902");
//    ct.addArc("Math225", "CS34902");
//
//    
//    // create Math course trajectories
//    
//    // Prerequisites for Math116
//    ct.addArc("Math115", "Math116");
//    
//    // Prerequisites for Math205
//    ct.addArc("Math116", "Math203");
//    
//    // Prerequisites for Math205
//    ct.addArc("Math116", "Math205");
//    ct.addArc("Math120", "Math205");
//    
//    // Prerequisites for Math206
//    ct.addArc("Math205", "Math206");
//    
//    // Prerequisites for Math210
//    ct.addArc("Math205", "Math210");
//    
//    // Prerequisites for Math214
//    ct.addArc("Math205", "Math214");
//    
//    // Prerequisites for Math215
//    ct.addArc("Math116", "Math215");
//    ct.addArc("Math120", "Math215");
//    
//    // Prerequisites for Math220
//    ct.addArc("Math116", "Math220");
//    ct.addArc("Math120", "Math220");
//    ct.addArc("Math205", "Math220");
//    
//    // Prerequisites for Math223
//    ct.addArc("Math116", "Math223");
//    ct.addArc("Math120", "Math223");
//    ct.addArc("CS230", "Math223");
//    
//    // Prerequisites for Math225
//    ct.addArc("Math116", "Math225");
//    ct.addArc("Math120", "Math225");
//    ct.addArc("CS230", "Math225");
//    
//    // Prerequisites for Math249
//    ct.addArc("Math206", "Math249");
//    ct.addArc("Math214", "Math249");
//    ct.addArc("Math223", "Math249");
//    ct.addArc("Math225", "Math249");
//    
//    // Prerequisites for Math302
//    ct.addArc("Math205", "Math302");
//    ct.addArc("Math206", "Math302");
//    
//    // Prerequisites for Math305
//    ct.addArc("Math206", "Math305");
//    
//    // Prerequisites for Math306
//    ct.addArc("Math305", "Math306");
//    
//    // Prerequisites for Math307
//    ct.addArc("Math302", "Math307");
//    
//    // Prerequisites for Math309
//    ct.addArc("Math302", "Math309");
//    ct.addArc("Math305", "Math309");
//    
//    // Prerequisites for Math310
//    ct.addArc("Math302", "Math310");
//    
//    // Prerequisites for Math310
//    ct.addArc("Math302", "Math310");
//    ct.addArc("Math302", "Math340");
//    ct.addArc("Math305", "Math306");
//    ct.addArc("Math305", "Math340");
//    ct.addArc("Math302", "Math349");
//    
//    // create MAS course trajectories
//    ct.addArc("ARTS108", "ARTS221");
//    ct.addArc("ARTS109", "ARTS221");
//    ct.addArc("ARTS165", "ARTS221");
//    
//  
//    
//    
//    System.out.println(ct);
//    
//
//  }
  
}
}