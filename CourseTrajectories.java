/*
 * Final Project
 * 
 */

import java.io.*;
import java.util.*;
import java.util.Scanner;
import javafoundations.*;

public class CourseTrajectories<T> {
  AdjMatGraphPlus<String> graph;
  
  public CourseTrajectories() {
   graph = new AdjMatGraphPlus<String>();
  }
  
  public CourseTrajectories(String tgfFile) throws FileNotFoundException {
    graph = new AdjMatGraphPlus<String>();
    //tgfFile = (T)tgfFile;
    graph = graph.fromTGF(tgfFile);
  }
    
   /** Returns true if this graph is empty, false otherwise. */
  public boolean isEmpty(){
   return graph.isEmpty(); 
  }
   
   /** Returns the number of vertices in this graph. */
  public int n() {
    return graph.n();
  }

   /** Returns the number of arcs in this graph. */
  public int m() {
    return graph.m();
  }
   
   /** Returns true iff a directed edge exists b/w given vertices */
  public boolean isArc (String vertex1, String vertex2) {
    return graph.isArc(vertex1, vertex2);
  }

   /** Returns true iff an edge exists between two given vertices
   * which means that two corresponding arcs exist in the graph */
  public boolean isEdge (String vertex1, String vertex2){
    return graph.isEdge(vertex1, vertex2);
  }

   /** Returns true IFF the graph is undirected, that is, for every
   * pair of nodes i,j for which there is an arc, the opposite arc
   * is also present in the graph.  */
  public boolean isUndirected() {
    return graph.isUndirected();
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


   /** Returns a string representation of the adjacency matrix. */
  public String toString() {
    
    return graph.toString();
          
  }
   
}