import java.util.*;

public class SortedCourses{
  
  CourseTrajectories<String> graph;

  LinkedList<String> selected;
  public SortedCourses(CourseTrajectories<String> g1, LinkedList<String> selected){
    graph = g1;
    this.selected = selected;
  }
  
  public LinkedList<String> majorCompletedCourses(){
    LinkedList<String> result = new LinkedList<String>();
    for(int i = 0; i<selected.size(); i++){
     if(graph.containsCourse(selected.get(i))){
       result.add(selected.get(i));
     }
    }
    return result;
    
    
  }
  
}