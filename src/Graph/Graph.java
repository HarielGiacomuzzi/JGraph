package Graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedList;

public class Graph {
	private LinkedList<Node> nodes = new LinkedList<Node>();
	
	public Graph(){}
	 
	 /**
	  * Adds a new node in the graph, without any connection to it.
	  * 
	  * @param label String Node Label
	  * @param data Int Node Data
	  */
	 public void addNode(String label, int data)
     {
         nodes.addLast(new Node(data,label));
     }
	 /**
	  * Adds a new node in the graph, without any connection to it.
	  * 
	  * @param label String Node Label
	  */
     public void addNode(String label)
     {
         addNode(label,0);
     }
	 /**
	  * Create a non directional vertex from node u to node v with weight 0.
	  *         
	  * @param u String node name.
	  * @param v String node name.
	  */
     public void addNonDirectedVertex(String u, String v)
     {
         addNonDirectedVertex(u, v, 0);
     }
     /**
      * Create a non directed vertex from node u to node v with the specified weight.
      * 
      * @param u String node name.
      * @param v String node name.
      * @param weigth Int connection weight.
      */
     public void addNonDirectedVertex(String u, String v,int weigth)
     {
         Node x = null, y = null;
         for (Node a : nodes)
         {
             if (a.label.equals(u))
             {
                 x = a;
             }
             if (a.label.equals(v))
             {
                 y = a;
             }
         }
         if (x == null || y == null)
         {
             return;
         }
         x.neighbors.addLast(new Vertex(y, weigth));
         y.neighbors.addLast(new Vertex(x, weigth));
     }
     /**
      * Returns a string with all the labels of the neighbors of the node u.
      * 
      * @param u String node name
      * @return String
      */
     public String vizinhos(String u) {
         StringBuilder result = new StringBuilder();
         for (Node a : nodes) {
             if (a.label.equals(u)) {
                 for (Vertex b : a.neighbors)
                 {
                     result.append(" " + b.node.label);
                 }
                 return result.toString();
             }            
         }            
         return result.toString();
     }
     /**
      * Create a directed vertex from node u to node v with weight 0.
      * 
      * @param u String node name.
      * @param v String node name.
      */
     public void addDirectedVertex(String u, String v)
     {
         addDirectedVertex(u, v, 0);
     }
     /**
      * Creates a directed connection from node u to node v with the specified weight.
      * 
      * @param u String node name.
      * @param v String node name.
      * @param weigth Int vertex weight.
      */
     public void addDirectedVertex(String u, String v,int weigth)
     {
         Node x = null, y = null;
         for (Node a : nodes)
         {
             if (a.label.equals(u))
             {
                 x = a;
             }
             if (a.label.equals(v))
             {
                 y = a;
             }
         }
         if (x == null || y == null)
         {
             return;
         }
         x.neighbors.addLast(new Vertex(y, weigth));
     }
     /**
      * Returns true if there's a way of going from node u to node v.
      * 
      * @param u String node name.
      * @param v String node name.
      * @return Boolean
      */
     public boolean hasConnection(String u, String v) {
    	 if (hasDirectConection(u, v)) return true;
         for (Node a : nodes) {
             if (a.label.equals(u)) { 
                 for(Vertex b : a.neighbors){
                     if (hasDirectConection(b.node.label, v)) return true;
                 }
                 break;
             }
         }

         return false;
     }
     /**
      * Returns true if v is a neighbor of u, else returns false.
      * 
      * @param u String node name.
      * @param v String node name.
      * @return Boolean
      */
     private boolean hasDirectConection(String u, String v)
     {
         for (Node a : nodes) {
             if (a.label.equals(u))
             {
                 for (Vertex b : a.neighbors) {
                     if (b.node.label.equals(v)) {
                         return true;                    
                     }
                 }
                 break;
             }
         }
         return false;
     }
     
     /**
      * Clear all the marks from all nodes on the Graph.
      */
     private void clearAllMarks(){
    	 for (Node a : nodes){
    		 a.mark = 0;
    	 }    	 
     }
     /**
      * Return a string with the result of a Depth-first search (DFS). Return a empty string if the starting Node is invalid.
      * 
      * @param u String starting node name
      * @return String 
      */
     public String DFSWalk(String u){
    	 clearAllMarks();
    	 Node start = getNodeByName(u);
    	 if (start == null){
    		 return "";
    	 }
    	 return DFSWalk(start, new StringBuilder()).toString();
     }
     /**
      * Return a stringBuilder with the result of a Depth-first search (DFS).
      * 
      * @param u Node starting node.
      * @param aux StringBuilder for append the results.
      * @return StringBuilder 
      */
     private StringBuilder DFSWalk(Node u, StringBuilder aux){
    	 aux.append(u.label);
    	 u.mark = 1;
    	 for(Vertex a : u.neighbors){
    		 if(a.node.mark == 0){
    			 aux = (DFSWalk(a.node, aux));
    		 }
    	 }    	 
    	 return aux;
     }     
     /**
      * Return a string with the result of a breadth-first search (BFS). Return a empty string if the starting node is invalid.
      * 
      * @param u String Starting node name.
      * @return String
      */
     public String BFSWalk(String u){
    	 clearAllMarks();
    	 // setting up some things
    	 StringBuilder aux = new StringBuilder();    	 
    	 LinkedList<Node> list = new LinkedList<Node>();
    	 Node start = getNodeByName(u);
    	 // checking if the node isn't null
    	 if (start == null ){
    		 return "";
    	 }
    	 //start the walking
    	 aux.append(start.label);
    	 start.mark = 1;
    	 list.addLast(start);
    	 while(!list.isEmpty()){
    		 Node v = list.removeFirst();
    		 for(Vertex a : v.neighbors){
    			 if (a.node.mark == 0){
    				 aux.append(" "+a.node.label);
    				 a.node.mark = 1;
    				 list.addLast(a.node);
    			 }    			 
    		 }
    	 }
    	 return aux.toString();
     }
     
     /**
      * Returns the Node with the lowest distance and unprocessed.
      * 
      * @param l LinkedList<Node> list with all nodes.
      * @return Node with the lower distance unprocessed.
      */
     private Node getLower(LinkedList<Node> l){
    	 Node lower = null;
    	 int lowest = 999999999; 
    	 for(Node a : l){
    		 if (a.DistanceToMe < lowest && a.mark == 0){
    			 lower = a;
    		 }		 
    	 }
    	 return lower;
     }
     /**
      * Relaxes a vertex between two nodes.
      * 
      * @param u Node u
      * @param v Node v
      * @param weight Vertex Weight
      */
     private void relax(Node u, Node v, int weight){
    	 if (v.DistanceToMe > u.DistanceToMe + weight ){
    		v.DistanceToMe = u.DistanceToMe + weight;
    		v.Father = u;
    	 }    	 
     }
     /**
      * Sets the distance to all nodes equals "infinite" except by the starting node, also clears the marks for processing.
      * @param start Node Start node.
      */
     private void InitializePathFind(Node start){
    	 for(Node a: nodes){
    		 if (a == start){
    			a.DistanceToMe = 0;
    			a.Father = null;
    			a.mark = 0;
    		 }else{
    			a.DistanceToMe = 999999999;
    			a.mark = 0;
    		 }    		 
    	 }    	 
     }
     /**
      * Returns a String with the lowest weight Path between two nodes U and V. 
      * @param u Node Name Start
      * @param v Node Name Finish
      * @return String with the Path and the Path Weight.
      */
     public String Djikstra(String u, String v){
    	 Node start = getNodeByName(u);
    	 Node finish = getNodeByName(v);
    	 InitializePathFind(start);
    	 Node x = null;
    	 while(true){
    		 x = getLower(nodes);
    		 if (x == null){
    			 break;
    		 } 
    		 x.mark = 1;
    		 for(Vertex a : x.neighbors){
    			 relax(x,a.node,a.weight);
    		 }
    	 }
    	 
    	 String aux = "";
    	 int pathWeight = finish.DistanceToMe;
    	 while(finish != null){
    		 aux = " ->"+finish.label+" "+aux;
    		 finish = finish.Father;
    	 }
    	 return aux+" Total Path Weight: "+ pathWeight;
     }
     
     /**
      * Returns a reference to a Node by providing it's label. Return null if the node does not exists.
      * 
      * @param u String Node Name
      * @return Node
      */
     private Node getNodeByName(String u){
    	 for(Node a : nodes){
    		if (a.label.equals(u)){
    			return a;
    		} 
    	 }
    	 return null;
     }     
     /**
      * Creates a text file with the proper format for read on GraphViz
      * 
      * @param where String Path for save the file.
      * @param directed Boolean true if the graph is directed.
      */
     public void CreateGraphVizFile(String where, boolean directed)
     {
         CreateGraphVizFile(where, directed, "MyGraph");
     }
     /**
      * Creates a text file with the proper format for read on GraphViz
      * 
      * @param where String Path for save the file.
      * @param directed Boolean true if the graph is directed.
      * @param name String the Name of the Graph.
      */
     public void CreateGraphVizFile(String where, boolean directed, String name)
     {
    	 try{
         BufferedWriter  writer = new BufferedWriter(new FileWriter(where+"\\result.txt"));
         
         if (directed){
             writer.write("digraph "+name+" {\nrankdir = LR;\nnode [\nshape = ellipse,\nstyle = filled,\ncolor = \"#000000\",\nfillcolor = \"#FFFFFF\"];\n");
             for (Node a : nodes)
             {
                 writer.write(("\""+a.label+"\"")  + "[label=\"Node: " + a.label + "\\nValor: " + a.data + "\"]\n");
                 for (Vertex b : a.neighbors)
                 {
                     writer.write(("\""+a.label+"\"")  + " -> " + ("\""+b.node.label+"\"") +" [label=\""+b.weight+"\"];\n");
                 }
             }

             writer.write("}\n");
             
         }

         else
         {
             writer.write("graph "+name+" {\nrankdir = LR;\nnode [\nshape = ellipse,\nstyle = filled,\ncolor = \"#000000\",\nfillcolor = \"#FFFFFF\"];\n");
             for (Node a : nodes)
             {
                 writer.write(a.label + "[label=\"Node: " + a.label + "\\nValor: " + a.data + "\"]\n");
                 for (Vertex b : a.neighbors)
                 {
                     if (!b.node.printMark)
                     {
                         writer.write(a.label + " -- " + b.node.label+" [label=\""+b.weight+"\"];\n");
                     }
                 }
                 a.printMark = true;
             }

             writer.write("}\n");
         }
         
         writer.close();
     }
     catch(Exception e){
    	 System.out.println(e.toString());
     }
 }
}
