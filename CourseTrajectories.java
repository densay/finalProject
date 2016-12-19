import java.io.*;
import java.util.*;
import java.util.Scanner;
import javafoundations.*;

public class CourseTrajectories<T> {
  AdjMatGraphPlus<String> graph;
  String major;
  public CourseTrajectories(String major) {
   graph = new AdjMatGraphPlus<String>();
   this.major = major;
  }
  
  public CourseTrajectories(String tgfFile, String major) throws FileNotFoundException {
    graph = new AdjMatGraphPlus<String>();
    //tgfFile = (T)tgfFile;
    graph = graph.fromTGF(tgfFile);
    this.major = major;
  }
    
   /** Returns true if this graph is empty, false otherwise. */
  public boolean isEmpty(){
   return graph.isEmpty(); 
  }
   
   /** Returns the number of vertices in this graph. */
  public int numberOfCourses() {
    return graph.n();
  }

   /** Returns the number of arcs in this graph. */
  public int numOfDependecies() {
    return graph.m();
  }
   
   /** Returns true iff a directed edge exists b/w given vertices */
  public boolean isArc(String vertex1, String vertex2) {
    return graph.isArc(vertex1, vertex2);
  }
     
   /** Adds a vertex to this graph, associating object with vertex.
   * If the vertex already exists, nothing is inserted. */
  public void addVertex (String vertex) {
    graph.addVertex(vertex);
  }

   /** Removes a single vertex with the given value from this graph.
   * If the vertex does not exist, it does not change the graph. */
  public void removeVertex (String vertex) {
    graph.removeVertex(vertex);
  }

   /** Inserts an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
  public void addArc (String vertex1, String vertex2) {
    graph.addArc(vertex1, vertex2);
  }

   /** Removes an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
   public void removeArc (String vertex1, String vertex2){
    graph.removeArc(vertex1, vertex2);
  }
   
   /** Inserts an edge between two vertices of this graph,
   * if the vertices exist. Else does not change the graph. */
   public void addEdge (String vertex1, String vertex2) {
    graph.addEdge(vertex1, vertex2);
  }

   /** Removes an edge between two vertices of this graph,
   if the vertices exist, else does not change the graph. */
   public void removeEdge (String vertex1, String vertex2) {
    graph.removeEdge(vertex1, vertex2);
  }
     
     
   /** Retrieve from a graph the vertices adjacent to vertex v.
   Assume that the vertex is in the graph */
   public LinkedList<String> getSuccessors(String vertex) {
     return graph.getSuccessors(vertex);
   }
        

  /** Retrieve from a graph the vertices x preceding vertex v (x->v)
 and returns them onto a linked list */
   public LinkedList<String> getPredecessors(String vertex) {
     return graph.getPredecessors(vertex);
   }
   
   public LinkedList<String> listByPriority() throws GraphCycleException{
    return graph.listByPriority(); 
   }
   public LinkedList<String> dfsTraversal(String startVertex){
    return graph.dfsTraversal(startVertex); 
   }
   public LinkedList<String> bfsTraversal(String startVertex){
    return graph.bfsTraversal(startVertex); 
   }
   
   public AdjMatGraphPlus<String> fromTGF(String tgfFile) throws FileNotFoundException {
     return graph.fromTGF(tgfFile);
   }

   public boolean containsCourse(String vertex) {
     return graph.containsVertex(vertex);
   }
   public String getMajorName(){
    return major; 
   }
   //ALL SOURCES
  public LinkedList<String> allSources() {
    return graph.allSources();
  }
   /** Returns a string representation of the adjacency matrix. */
  public String toString() {
    
    return graph.toString();
          
  }
  
  public boolean isSource(String vertex) {
   return graph.isSource(vertex); 
  }
