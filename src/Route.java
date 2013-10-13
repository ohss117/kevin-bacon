
public class Route<V> {
	private V prev_node; //The previous node in the breadth-first traversal
	private int num_jumps;  //the number of edges from the root node to this one
	
	public Route( V prev, int distance ) {
		this.prev_node = prev;
		this.num_jumps = distance;
	}
	
	public V getPrev() { return prev_node; }
	
	public int getNumEdges() { return num_jumps; }
	
}
