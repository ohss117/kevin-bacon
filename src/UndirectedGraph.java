import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Collection;

public class UndirectedGraph<V> {
	private ArrayList<V> vertices;
	private HashMap<V,LinkedHashSet<V>> neighbors;
	private HashMap<V,Set<V>> neighborsView;
	private int edgeCount;

	// Constructs an empty undirected graph
	public UndirectedGraph() {
		vertices = new ArrayList<V>();
		neighbors = new HashMap<V,LinkedHashSet<V>>();
		neighborsView = new HashMap<V,Set<V>>();
	}

	// Adds the specified vertex, initially with no adjacent edges.
	public void addVertex(V v_id) {
		vertices.add(v_id);
		LinkedHashSet<V> v_neighbors = new LinkedHashSet<V>();
		neighbors.put(v_id, v_neighbors);
		neighborsView.put(v_id, Collections.unmodifiableSet(v_neighbors));
	}

	// Removes the specified vertex with all incident edges. 
	public void removeVertex(V v_id) {
		if(vertices.remove(v_id)) {
			LinkedHashSet<V> v_neighbors = neighbors.remove(v_id);
			for(V u_id : v_neighbors) {
				LinkedHashSet<V> u_neighbors = neighbors.get(u_id);
				u_neighbors.remove(v_id);
				--edgeCount;
			}
			neighborsView.remove(v_id);
		} else {
			throw new NoSuchElementException("no such vertex");
		}
	}

	/* Adds an undirected edge between the two vertices. Throws
	   NoSuchElementException if either vertex isn't part of graph. */
	public void addEdge(V u_id, V v_id) {
		LinkedHashSet<V> u_neighbors = neighbors.get(u_id);
		LinkedHashSet<V> v_neighbors = neighbors.get(v_id);
		if(u_neighbors == null) throw new NoSuchElementException("first argument not in graph");
		if(v_neighbors == null) throw new NoSuchElementException("second argument not in graph");
		if(u_neighbors.add(v_id) && v_neighbors.add(u_id)) {
			++edgeCount;
		}
	}

	/* Removes an undirected edge between the two vertices. Throws
	   NoSuchElementException if either vertex isn't part of graph or
	   there is no edge between them. */
	public void removeEdge(V u_id, V v_id) {
		LinkedHashSet<V> u_neighbors = neighbors.get(u_id);
		LinkedHashSet<V> v_neighbors = neighbors.get(v_id);
		if(u_neighbors == null) throw new NoSuchElementException("first argument not in graph");
		if(v_neighbors == null) throw new NoSuchElementException("second argument not in graph");
		if(u_neighbors.remove(v_id) && v_neighbors.remove(u_id)) {
			--edgeCount;
		} else {
			throw new NoSuchElementException("no edge between vertices");
		}
	}

	// Returns the number of vertices in this graph.
	public int getVertexCount() {
		return vertices.size();
	}

	// Returns the number of edges in this graph.
	public int getEdgeCount() {
		return edgeCount;
	}

	// Returns the vertex at the specified index of this graph.
	public V getVertex(int index) {
		return vertices.get(index);
	}
	
	//Returns a read-only view of the vertex set
	public Collection<V> getVertices() {
		return Collections.unmodifiableCollection(vertices);
	}

	// Returns the number of vertices of the specified vertex.
	public int getNeighborCount(V v_id) {
		LinkedHashSet<V> v_neighbors = neighbors.get(v_id);
		if(v_neighbors == null) throw new NoSuchElementException("no such vertex");
		return v_neighbors == null ? 0 : v_neighbors.size();
	}

	/* Returns the set of vertices that are adjacent to the
	   specified vertex. */
	public Set<V> getNeighbors(V v_id) {
		Set<V> v_neighbors = neighborsView.get(v_id);
		if(v_neighbors == null) throw new NoSuchElementException("no such vertex");
		return v_neighbors;
	}

	/* Returns true if the two specified vertices have an edge
	   between them. */
	public boolean containsEdge(V u_id, V v_id) {
		LinkedHashSet<V> u_neighbors = neighbors.get(u_id);
		if(u_neighbors == null) throw new NoSuchElementException("first argument not in graph");
		if(!neighbors.containsKey(v_id)) throw new NoSuchElementException("second argument not in graph");
		return u_neighbors.contains(v_id);
	}
	
	// Returns whether the specified vertex is in the graph
	public boolean containsVertex( V u_id ) {
		return vertices.contains(u_id);
	}
	
	
	
	public static void main( String[] args ) {
		UndirectedGraph<String> graph = new UndirectedGraph<String>();
		graph.addVertex("Kevin Bacon" );
		graph.addVertex( "John Wayne" );
		graph.addVertex( "Some Movie" );
		graph.addEdge( "Kevin Bacon", "Some Movie");
		graph.addEdge( "John Wayne", "Some Movie" );
		Set<String> neighbors = graph.getNeighbors("Some Movie");
		System.out.println( "\nActors in Some Movie via an iterator - " );
		Iterator<String> iter = neighbors.iterator();
		while( iter.hasNext() ) {
			System.out.println( iter.next() );
		}
		System.out.println( "\nActors in Some Movie via an enhanced for loop - " );
		for( String n : neighbors ) 
			System.out.println( n );
		
		if( graph.containsEdge("Kevin Bacon", "Some Movie"))
			System.out.println( "\nKevin Bacon is adjacent to Some Movie\n");
		System.out.println( "Graph Vertices - " );
		Collection<String> my_vertices = graph.getVertices();
		for( String v : my_vertices )
			System.out.println( v );
		System.out.println();
		
		HashMap<String, Route<String>> distances = new HashMap<String, Route<String>>( my_vertices.size());
		distances.put( "Kevin Bacon", new Route<String>( "", 0));
		distances.put( "Some Movie", new Route<String>( "Kevin Bacon", 1));
		distances.put( "John Wayne", new Route<String>( "Some Movie", 2 ));
		String node = "John Wayne";
		while( node.length() > 0 ) {
			System.out.print( node );
			if( distances.get(node).getNumEdges() > 0 )
				System.out.print( " -> ");
			node = distances.get( node ).getPrev();
		}
	}
}



