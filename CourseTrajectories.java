/*
 * Final Project
 * 
 */

import java.io.*;
import java.util.*;
import java.util.Scanner;
import javafoundations.*;

public class CourseTrajectories<T> {
  AdjMatGraphPlus<T> graph;
  
  public CourseTrajectories() {
   graph = new AdjMatGraphPlus<T>();
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
  public boolean isArc (T vertex1, T vertex2) {
    return graph.isArc(vertex1, vertex2);
  }

   /** Returns true iff an edge exists between two given vertices
   * which means that two corresponding arcs exist in the graph */
  public boolean isEdge (T vertex1, T vertex2){
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
  public void addVertex (T vertex) {
    graph.addVertex(vertex);
  }

   /** Removes a single vertex with the given value from this graph.
   * If the vertex does not exist, it does not change the graph. */
  public void removeVertex (T vertex) {
    graph.removeVertex(vertex);
  }

   /** Inserts an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
  public void addArc (T vertex1, T vertex2) {
    graph.addArc(vertex1, vertex2);
  }

   /** Removes an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
   public void removeArc (T vertex1, T vertex2){
    graph.removeArc(vertex1, vertex2);
  }
   
   /** Inserts an edge between two vertices of this graph,
   * if the vertices exist. Else does not change the graph. */
   public void addEdge (T vertex1, T vertex2) {
    graph.addEdge(vertex1, vertex2);
  }

   /** Removes an edge between two vertices of this graph,
   if the vertices exist, else does not change the graph. */
   public void removeEdge (T vertex1, T vertex2) {
    graph.removeEdge(vertex1, vertex2);
  }
     
     
   /** Retrieve from a graph the vertices adjacent to vertex v.
   Assume that the vertex is in the graph */
   public LinkedList<T> getSuccessors(T vertex) {
     return graph.getSuccessors(vertex);
   }
        

  /** Retrieve from a graph the vertices x preceding vertex v (x->v)
 and returns them onto a linked list */
   public LinkedList<T> getPredecessors(T vertex) {
     return graph.getPredecessors(vertex);
   }
   
   public LinkedList<T> listByPriority() throws GraphCycleException{
    return graph.listByPriority(); 
   }
   public LinkedList<T> dfsTraversal(T startVertex){
    return graph.dfsTraversal(startVertex); 
   }
   public LinkedList<T> bfsTraversal(T startVertex){
    return graph.bfsTraversal(startVertex); 
   }


   /** Returns a string representation of the adjacency matrix. */
  public String toString() {
    
    return graph.toString();
          
  }
   
}