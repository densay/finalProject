/*
 * Final Project
 * 
 */

import java.io.*;
import java.util.*;
import java.util.Scanner;


public class CourseTrajectories<T> implements Graph<T> {
  
  // instance variables
  private int n; //counter
  private T[] vertices;
  private boolean [][] arcs;
  private final int DEFAULT_CAPACITY = 1;
  
  // constructor without parameter
  public CourseTrajectories() {
   n = 0;
   this.arcs = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
   this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
  }
    
   /** Returns true if this graph is empty, false otherwise. */
  public boolean isEmpty(){
   return n==0; 
  }
   
   /** Returns the number of vertices in this graph. */
  public int n() {
    return n;
  }

   /** Returns the number of arcs in this graph. */
  public int m() {
    int counter = 0;
    for (int i = 0; i < arcs.length; i++) { // loops through rows of arc matrix
      for (int j = 0; j < arcs.length; j++) { // loops through columns of arc matrix
        if (arcs[i][j] == true) // if arc exists between 2 vertices
          counter ++;
      }
    }
    return counter;
  }
   
   /** Returns true iff a directed edge exists b/w given vertices */
  public boolean isArc (T vertex1, T vertex2) {
    // local variables 
    int indexVertex1 = -1;
    int indexVertex2 = -1;
    for(int i = 0; i < vertices.length; i++) { // loops through vertices array looking for given vertices
      if(vertices[i] == vertex1) {
        indexVertex1 = i;
      }
      if(vertices[i] == vertex2) {
        indexVertex2 = i;
      }
    }
    if((indexVertex1 != -1) && (indexVertex2 != -1)) { // checks to make sure both given vertices are in the array
      if ((arcs[indexVertex1][indexVertex2] == true)) {
        return true;
      }
    }
    return false;
  }

   /** Returns true iff an edge exists between two given vertices
   * which means that two corresponding arcs exist in the graph */
  public boolean isEdge (T vertex1, T vertex2){
    int indexVertex1 = -1;
    int indexVertex2 = -1;
    for(int i = 0; i < vertices.length; i++) { // loops through vertices array looking for given vertices
      if(vertices[i] == vertex1) {
        indexVertex1 = i;
      }
      if(vertices[i] == vertex2) {
        indexVertex2 = i;
      }
    }
    if((indexVertex1 != -1) && (indexVertex2 != -1)) { // checks to make sure both given vertices are in the array
      if ((arcs[indexVertex1][indexVertex2] == true) && (arcs[indexVertex2][indexVertex1] == true)) {
        return true;
      }
    }
    return false;
  }

   /** Returns true IFF the graph is undirected, that is, for every
   * pair of nodes i,j for which there is an arc, the opposite arc
   * is also present in the graph.  */
  public boolean isUndirected() {
    for (int i = 0; i < vertices.length; i++) {
      for (int j = 0; j <vertices.length; j++) {
        if(i != j) { // makes sure that 2 different nodes are being looked at
          if(isEdge(vertices[i], vertices[j]) == false)
            return false;
        }
      }
    }
    return true;
  }
     
   /** Adds a vertex to this graph, associating object with vertex.
   * If the vertex already exists, nothing is inserted. */
  public void addVertex (T vertex) {
    for(int i = 0; i < vertices.length; i++) {
      if(vertices[i] == vertex) { // if vertex is already in the array breaks out of this method
        return;
      }
    }
    this.expandArray();
    this.expandArcs();
    vertices[n] = vertex;
    n++; // increase the count of vertices 
    }

   /** Removes a single vertex with the given value from this graph.
   * If the vertex does not exist, it does not change the graph. */
  public void removeVertex (T vertex) {
    int index = -1;
    for(int i = 0; i < vertices.length; i++) { // checks to see if vertex is in the array
      if (vertices[i] == vertex) {
        System.out.println("check");
        index = i;
        System.out.println(index);
      }
    }
    // if vertex is in the array
    if(index != -1) {
      //shifts all elements in the vertices array after removedVertex over to the left by 1
      for(int i = index+1; i < vertices.length; i++) {
        vertices[i-1] = vertices[i];
      }
      // shifts all elements in the arcs matrix
      for(int j = index+1; j < vertices.length; j++) { // loops through all rows after index
        for(int k = 0; k < vertices.length; k++) {
          arcs[j-1][k] = arcs[j][k];
        }
      }
      for(int j = 0; j < vertices.length; j++) { // loops through all the columns after index
        for(int k = index+1; k < vertices.length; k++) {
          arcs[j][k-1] = arcs[j][k];
        }
      }

      this.shrinkArray(); // shrink array
      this.shrinkArcs(); // shrink matrix
      n--; // decreases the count of the vertices
  }
  }

   /** Inserts an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
  public void addArc (T vertex1, T vertex2) {
    int indexVertex1 = -1;
    int indexVertex2 = -1;
    for(int i = 0; i < vertices.length; i++) { // checks if both vertices are in array
      if(vertices[i] == vertex1) {
        indexVertex1 = i;
      }
      if(vertices[i] == vertex2) {
        indexVertex2 = i;
      }
    }
    // if vertices exists adds the arc
    if((indexVertex1 != -1) && (indexVertex2 != -1)) {
      arcs[indexVertex1][indexVertex2] = true; // from Vertex1 to Vertex2
    }
  }

   /** Removes an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
   public void removeArc (T vertex1, T vertex2){
    int indexVertex1 = -1;
    int indexVertex2 = -1;
    for(int i = 0; i < vertices.length; i++) { // checks if vertices are in matrix
      if(vertices[i] == vertex1) {
        indexVertex1 = i;
      }
      if(vertices[i] == vertex2) {
        indexVertex2 = i;
      }
    }
    // if vertices exists remove the arc
    if((indexVertex1 != -1) && (indexVertex2 != -1)) {
      arcs[indexVertex1][indexVertex2] = false;
    }
  }
   
   /** Inserts an edge between two vertices of this graph,
   * if the vertices exist. Else does not change the graph. */
   public void addEdge (T vertex1, T vertex2) {
    int indexVertex1 = -1;
    int indexVertex2 = -1;
    for(int i = 0; i < vertices.length; i++) { // checks to see if vertices are in the array
      if(vertices[i] == vertex1) {
        indexVertex1 = i;
      }
      if(vertices[i] == vertex2) {
        indexVertex2 = i;
      }
    }
    // if vertices exists add edge
    if((indexVertex1 != -1) && (indexVertex2 != -1)) {
      arcs[indexVertex1][indexVertex2] = true; // adds arc from vertex2 to vertex1
      arcs[indexVertex2][indexVertex1] = true; // adds arc from vertex1 to vertex2
    }
  }

   /** Removes an edge between two vertices of this graph,
   if the vertices exist, else does not change the graph. */
   public void removeEdge (T vertex1, T vertex2) {
    int indexVertex1 = -1;
    int indexVertex2 = -1;
    for(int i = 0; i < vertices.length; i++) { // checks to see if vertices are in array
      if(vertices[i] == vertex1) {
        indexVertex1 = i;
      }
      if(vertices[i] == vertex2) {
        indexVertex2 = i;
      }
    }
    // if vertices exists remove edge
    if((indexVertex1 != -1) && (indexVertex2 != -1)) {
      arcs[indexVertex1][indexVertex2] = false; // removes edge from vertex2 to vertex1
      arcs[indexVertex2][indexVertex1] = false;
    }
  }
     
     
   /** Retrieve from a graph the vertices adjacent to vertex v.
   Assume that the vertex is in the graph */
   public LinkedList<T> getSuccessors(T vertex) {
     LinkedList<T> successors = new LinkedList<T>();
     int adj1;
     int adj2;
     for(int i = 0; i < vertices.length; i++) {
       if(vertices[i] == vertex) {
         if(i > 0) {
           adj1 = i-1;
           successors.add(vertices[adj1]);
         }
         if(i < (vertices.length-1)) {
           adj2 = i+1;
           successors.add(vertices[adj2]);
         }
       }
     }
     return successors;
   }
        

  /** Retrieve from a graph the vertices x preceding vertex v (x->v)
 and returns them onto a linked list */
   public LinkedList<T> getPredecessors(T vertex) {
     LinkedList<T> predecessors = new LinkedList<T>();
     for(int i = 0; i < vertices.length; i++) {
       if(vertices[i] == vertex) {
         for(int j = 0; j < i; j++) { // goes through all the elements before the given vertex
           predecessors.add(vertices[j]);
         }
       }
     }
     return predecessors;
   }
   
// helper methods
   
   /*expand array by 1 element*/
  public void expandArray() {
    T[] vertices2 = (T[])(new Object[n+1]); // creates new array 1 element longer than the previous array
   for (int i = 0; i< vertices.length; i++) { // copies elements from old array into new array
    vertices2[i] = vertices[i]; 
   }
   vertices = vertices2; //alias
  }
  
  /*shrinks array by 1 elment*/
  public void shrinkArray() {
    T[] vertices2 = (T[])(new Object[n-1]); // creates new array 1 element shorter than the previous array
    for (int i = 0; i< vertices2.length; i++) { // copies elements from old array into new array
      vertices2[i] = vertices[i]; 
    }
    vertices = vertices2; //alias
  }
  
  /*expands arcs matrix by 1 row and 1 column*/
  public void expandArcs() {
    boolean [][] arcs2 = new boolean[n+1][n+1]; // creates new matrix with 1 more row and column than previous matrix
    for (int i =0; i<arcs.length; i++) {
      for (int j =0; j<arcs.length; j++) {
        arcs2[i][j] = arcs[i][j]; // copies elements from old matrix into new matrix
      }
    }
    arcs = arcs2; //alias
  }
  
  /*shrinks matrix by 1 row and 1 column*/
  public void shrinkArcs() {
    boolean [][] arcs2 = new boolean[n-1][n-1]; // creates new matrix with 1 less row and column than previous matrix
    for (int i =0; i<arcs2.length; i++) {
      for (int j =0; j<arcs2.length; j++) {
        arcs2[i][j] = arcs[i][j]; // copies alements from old matrix into new matrix
      }
    }
    arcs = arcs2; //alias
  }
 
   /** Returns a string representation of the adjacency matrix. */
  public String toString() {
    String results = "";
    
    results += "There are " + n + " vertices: ";
    for (int i = 0; i<vertices.length; i++) {
     results += "\n" + vertices[i];
    }
    results += "\n\nThis is the arcs matrix: \n       ";
    for (int i = 0; i<vertices.length; i++) {
      results += vertices[i] + " ";
    }
    results += "\n";
    for (int i =0; i<arcs.length; i++) {
      results += vertices[i] +  " ";
      for (int j =0; j<arcs.length; j++) {
        if(arcs[i][j] == true) {
          results += "1     ";
        }
        else {
          results += "0     ";
        }
      }
      results += "\n";
    }
    
    return results;
          
  }
   
}