package Graph;
import java.util.LinkedList;

class Node {
	protected int data;
	protected String label;
	protected short mark;
	protected Node Father = null;
	protected int DistanceToMe = 0;
	protected boolean printMark;
	protected LinkedList<Vertex> neighbors = new LinkedList<Vertex>();
	
	Node(int data, String label){
		this.data = data;
		this.label = label;
	}
}
