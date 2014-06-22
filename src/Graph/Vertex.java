package Graph;

class Vertex {
	protected int weight;
	protected Node node;
	
	Vertex(Node n, int weight){
		this.node = n;
		this.weight = weight;		
	}
}
