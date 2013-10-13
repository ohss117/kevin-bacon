import java.util.*;


public class Dijkstra {
	private String source = ""; //Source actor
	private UndirectedGraph<String> g; // The undirected graph
	private LinkedList<String> vertexQueue; // An intermediate queue for labeling.
	private HashMap<String, Route<String>> distances; //A set of distances
	
	
	public Dijkstra(UndirectedGraph<String> graph, String kevin, String otherActor){
		long startTime = System.currentTimeMillis();
		//Initialize variables
		source = kevin;
		String findActor = otherActor;
		g = graph;
		
		//Initialize data structures
		Collection<String> my_vertices = graph.getVertices();
		distances = new HashMap<String, Route<String>>(my_vertices.size());
		vertexQueue = new LinkedList<String>();
		
		//distance from the source to itself is 0
		distances.put(source, new Route<String>("",0));
		vertexQueue.add(source);
		
		
		int tempDistance = 0;
		while(!vertexQueue.isEmpty()){
			tempDistance++;
			String removedVertex = vertexQueue.removeFirst();
			Collection<String> neighborVertex = g.getNeighbors(removedVertex);
			
			//Check to make sure that the vertex in question is in the unlabeled set before labeling them
			
			
			for(String iter : neighborVertex){
				if(!distances.containsKey(iter)){
					
					System.out.println("In the if statement");
					vertexQueue.add(iter);
					distances.put(iter, new Route<String>( removedVertex, tempDistance));		
				}
				
			}
				
			System.out.println(removedVertex);
			
		}
		System.out.println("End of the while loop\n");
		long endTime = System.currentTimeMillis();
		int distance = 0;
		while( findActor.length() > 0 ) {
			System.out.print( findActor );
			if( distances.get(findActor).getNumEdges() > 0 )
				System.out.print( " -> ");
				distance++;
			findActor = distances.get( findActor ).getPrev();
			
		}
		System.out.println("\n\n"+otherActor+ " has a Bacon Number of "  + Math.floor(distance/2)+ " from " +kevin+".");
		System.out.println("The Dijkstra algorithm ran for "+ (int)(endTime-startTime)+ " milliseconds.");
	}
	
		 
}
