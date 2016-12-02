/* Xochitl Say
 * hw7/lab 8 
 * cs 230
 * 11/7/16
*/

import java.util.*;
import java.io.*;

public class AdjMatGraph<T> implements Graph <T>{
  private final int DEFAULT_CAPACITY = 1;
  
  private int n;//keeps track of number of vertices
  private boolean[][] arcs;
  private T[] vertices;
  
  public AdjMatGraph(){
    n = 0;
    this.arcs = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
  }
  
  //second constructor
  public AdjMatGraph(String fileName){
    try{
      Scanner reader = new Scanner (new File (fileName));
      String fileLines = "";
      String linesFromFile = "";
      
      while(reader.hasNext()){//takes in all of tgf file and puts it into string 
        linesFromFile+= reader.next();
      }
      String[] array = linesFromFile.split("#");
      String vertexString = array[0];//selects what's to the left of # which is vertex label and number
      String[] vArray = vertexString.split("");//puts those into array
      
      this.vertices = (T[])(new Object[(vArray.length-1)/2]);//sets size up based off of the size of vArray neglecting item 0 a space
      
      int i=0;
      int a =2;
      while(a<vArray.length){//populates vertices array with labels from tgf
        vertices[i]= (T)vArray[a];
        i++;
        a+=2;
      }
      n = i;
      //sets up arcs array 
      this.arcs = new boolean[i][i];
      char[] arcsArray= array[1].toCharArray();//takes items in the split array and turns into char Array
      
      int c=0;//index of item in arcArray 
      while(c<arcsArray.length-1){//supplies arcs adj matrix w info to keep track of arcs that are connected
        int v1 = (Character.getNumericValue(arcsArray[c]))-1;
        int v2 = (Character.getNumericValue(arcsArray[c+1]))-1;//the number next to int in tgf file
        
        arcs[v1][v2]= true;//use above values to corresponding space in array 
        
        c+=2;
      }
      reader.close();
    }catch(FileNotFoundException e){
      System.out.println(e);
    }
    
  }
  
  
  //saveTGF method
  public void saveTGF(String fileName){
    try{
      PrintWriter writer = new PrintWriter(new File(fileName));
      
      for (int i = 0; i<n; i++){//goes through vertices array and sets up in tgf format
        writer.println((i+1)+" "+vertices[i]); 
      }
      
      writer.println("#");//adds #
      
      for (int i = 0; i< arcs.length; i++){//goes through arcs and takes out those that are true and prints coresponding values to file
        for (int j= 0; j< arcs.length;j++){
          if(arcs[i][j]){
            writer.println((i+1)+" "+ (j+1)); 
          }
        }
      }
      
      writer.close();
    }
    catch (IOException e){
      System.out.println(e); 
    }
  }
  
  //expand capacity if vertices array is full 
  private void expandCapacity(){
    T[] vertices2 = (T[]) (new Object[vertices.length *2]);
    
    for (int i=0; i< vertices.length ; i++){
      vertices2[i] = vertices[i]; 
    }
    
    vertices = vertices2;
    
    boolean[][] arcs2 = new boolean[arcs.length*2][arcs.length*2];
    
    for (int i = 0; i< arcs.length; i++){//also expands arcs 
      for (int j= 0; j< arcs.length;j++){
        arcs2[i][j]= arcs[i][j];
      }
    }
    
    arcs = arcs2;
  }
  
  //if n is > 0 returns false
  public boolean isEmpty(){
    return (n==0);
  }
  
  /** Returns the number of vertices in this graph. */
  public int n(){
    System.out.println("Number of Vertices: "+ n);
    return n;
  }
  
  /** Returns the number of arcs in this graph. */
  public int m(){
    int m = 0;
    for (int i = 0; i< arcs.length; i++){
      for (int j= 0; j< arcs.length;j++){
        if(arcs[i][j]){
          m++; 
        }
      }
    }
    System.out.println("Number of Arcs: "+ m);
    return m;
  }
  
  /** Returns true iff a directed edge exists b/w given vertices */
  public boolean isArc (T vertex1, T vertex2){
    int i1= getIndex(vertex1);
    int i2 = getIndex(vertex2);
    return (arcs[i1][i2]);
  }
  
  /** Returns true iff an edge exists between two given vertices
    * which means that two corresponding arcs exist in the graph */
  public boolean isEdge (T vertex1, T vertex2){
    int i1= getIndex(vertex1);
    int i2 = getIndex(vertex2);
    
    return (arcs[i1][i2] && arcs[i2][i1]);
  }
  
  /** Returns true IFF the graph is undirected, that is, for every
    * pair of nodes i,j for which there is an arc, the opposite arc
    * is also present in the graph.  */
  public boolean isUndirected(){
    boolean undir = false;
    
    //goes through the each of the graph's vertices and compares the predecessors and successors
    //if they match for all vertices then it means the graph is undirected
    for(int i= 0; i<n;i++){
      LinkedList<T> p = getPredecessors(vertices[i]);
      LinkedList<T> s = getSuccessors(vertices[i]);
      
      if(p.size()==s.size()){
        while(!p.isEmpty() ){
          if(p.pop()==s.pop()){
            undir = true;
          }else{
            undir = false;
          }
        }
      }else{
        undir = false; 
      }
    }
    
    return undir;
  }
  
  /** Adds a vertex to this graph, associating object with vertex.
    * If the vertex already exists, nothing is inserted. */
  public void addVertex (T vertex){
    if (vertices[vertices.length-1]==null){
      vertices[n]= vertex;
      n++;
    }else{
      expandCapacity(); 
      vertices[n] = vertex;
      n++;
    }
  }
  
  
  
  
  
// helper function
  public int getIndex(T vertex){
    int index=-1; //returns -1 if vertex is not found
    
    for (int i =0; i<n; i++){
      if(vertices[i]==vertex){
        index= i;
        
      }
    }
    return index;
    
  }
  
  /** Removes a single vertex with the given value from this graph.
    * If the vertex does not exist, it does not change the graph. */
  public void removeVertex (T vertex){
    
    int index = getIndex(vertex);
    System.out.println(index);
    if (index!=-1){
      
      //removes vertex from vertices array
      for (int i = index; i<n-1; i++){
        vertices[i]=vertices[i+1]; 
        System.out.println(vertices[i]);
      }
      
      //removes connections from arcs array
      //shifts everything up to a different column
      for (int i = 0; i<n; i++){//row
        for (int j = index; j< n; j++){
          arcs[i][j]=arcs[i][j+1];
        }
      }
      
      //shifts everything into a new row
      for(int i =index; i<n; i++){//row
        for(int j = 0; j<n; j++){
          arcs[i][j]=arcs[i+1][j]; 
        }
      }
    }
    n--;//decreases vertices count by one
    
  }
  
  /** Inserts an arc between two vertices of this graph,
    * if the vertices exist. Else it does not change the graph. */
  public void addArc (T vertex1, T vertex2){
    //gets index of both vertices
    int i1= getIndex(vertex1);
    int i2 = getIndex(vertex2);
    
    if (i1!=-1 && i2!=-1){
      arcs[i1][i2]=true;//sets it up if both vertices are found
    }
  }
  
  /** Removes an arc between two vertices of this graph,
    * if the vertices exist. Else it does not change the graph. */
  public void removeArc (T vertex1, T vertex2){
    int i1= getIndex(vertex1);
    int i2 = getIndex(vertex2);
    if (i1!=-1 && i2!=-1){
      arcs[i1][i2] = false;
    }
  }
  
  /** Inserts an edge between two vertices of this graph,
    * if the vertices exist. Else does not change the graph. */
  public void addEdge (T vertex1, T vertex2){
    int i1= getIndex(vertex1);
    int i2 = getIndex(vertex2);
    if (i1!=-1 && i2!=-1){//if both vertices are found sets up edge in adj mat
      arcs[i1][i2] = true;
      arcs[i2][i1]= true;
    }
  }
  
  /** Removes an edge between two vertices of this graph,
    if the vertices exist, else does not change the graph. */
  public void removeEdge (T vertex1, T vertex2){
    int i1= getIndex(vertex1);
    int i2 = getIndex(vertex2);
    if (i1!=-1 && i2!=-1){
      arcs[i1][i2] = false;//sets back to false for both 
      arcs[i2][i1]= false;
    }
  }
  
  /** Retrieve from a graph the vertices adjacent to vertex v.
    Assume that the vertex is in the graph */
  public LinkedList<T> getSuccessors(T vertex){
    LinkedList<T> l = new LinkedList<T>();
    int index = getIndex(vertex);//determines row/index it's checking
    
    for (int i = 0; i<n; i++){//checks by staying in same row just moving across different columns
      if(arcs[index][i]){//moves across columns to check what it points to 
        l.add(vertices[i]);//if true, gets added to linked list
      }
    }
    return l;
    
  }
  
  /** Retrieve from a graph the vertices x preceding vertex v (x->v)
    and returns them onto a linked list */
  public LinkedList<T> getPredecessors(T vertex){
    LinkedList<T> l = new LinkedList<T>();
    int index = getIndex(vertex);
    //iterates through adj matrix array, staying in the same column but moving through each row
    for (int i = 0; i<n; i++){
      if(arcs[i][index]){//stays in same column but switches row to see which vertices point to the vertex
        l.add(vertices[i]); 
      }
    }
    return l;
  }
  
  /** Returns a string representation of the adjacency matrix. */
  public String toString(){
    String s = "**************"+"\nARCS"+"\n**************"+"\ni";
    for (int i = 0; i<n; i++){
      s+= " "+ vertices[i]; 
    }
    
    for (int i = 0; i< n; i++){//row 
      s+= "\n"+ vertices[i]+" ";
      for (int j= 0; j< n;j++){//column
        if(arcs[i][j]){
          s+= 1 +" "; 
        }else{
          s+="- "; 
        }
      }
    }
    return s;
  }
  
  
  
  
  public static void main (String[] args){
    
    AdjMatGraph<String> a = new AdjMatGraph<String>();
    a.addVertex("a");
    a.addVertex("b");
    a.n();
    a.m();
    System.out.println(a.isEmpty());
    a.addEdge("a","b");
    System.out.println(a.isEdge("a", "b"));
    System.out.println(a.isArc("a", "b"));
    a.addVertex("c");
    a.addEdge("a","c");
    a.addEdge("b","c");
    System.out.println(a.isEdge("c", "b"));
    System.out.println(a.isEdge("a", "b"));
    System.out.println(a.getSuccessors("a"));
    System.out.println(a.getPredecessors("a"));
    System.out.println(a.isUndirected());
    System.out.println(a);
    a.removeVertex("b");
    System.out.println(a);
    a.saveTGF("test.tgf");
    
    AdjMatGraph<String> g1 = new AdjMatGraph<String>("test3.tgf");
    System.out.println(g1.isEmpty());
    System.out.println(g1);
    System.out.println(g1.isUndirected());
    
    
    
  } 
}