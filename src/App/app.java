package App;

import Graph.Graph;

public class app {
	public static void main(String[] args){
		Graph g = new Graph();
		
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		g.addNode("d");
		g.addNode("e");
		g.addNode("f");
		g.addNode("g");
		
		g.addDirectedVertex("a", "b",5);
		g.addDirectedVertex("b", "d",1);
		g.addDirectedVertex("g", "f", 3);
		g.addDirectedVertex("c", "e",9);
		g.addDirectedVertex("d", "c",9);
		g.addDirectedVertex("b", "f",9);
		g.addDirectedVertex("g", "e",9);
		
		System.out.println(g.hasConnection("c", "e"));
		System.out.println(g.hasConnection("g", "f"));
		System.out.println(g.hasConnection("b", "d"));
		System.out.println(g.hasConnection("a", "b"));
		System.out.println(g.hasConnection("b", "a"));
		System.out.println(g.hasConnection("a", "d"));
		System.out.println(g.hasConnection("b", "f"));
		
		g.CreateGraphVizFile("D:\\", true,"TestGraph");
		
		System.out.println("------ BFS ------");
		System.out.println(g.BFSWalk("g"));
		System.out.println("------ BFS ------");
		
		System.out.println("------ DFS ------");
		System.out.println(g.DFSWalk("a"));
		System.out.println("------ DFS ------");
	}

}
