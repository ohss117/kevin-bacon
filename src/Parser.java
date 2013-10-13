import java.io.*;
import java.util.*;


public class Parser {
	private File source;
	private String actor;
	private String movie;
	private UndirectedGraph<String> g = new UndirectedGraph<String>();

	public Parser(String aFileName) throws FileNotFoundException{
		source = new File(aFileName);
	    readFile();
	  }
	
	private void readFile() throws FileNotFoundException{
		Scanner scanner = new Scanner(new FileReader(source));
		try{
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				processLine(line);
			}	
		}
		finally{
			scanner.close();
		}

	}
	
	private void processLine(String line){
		String l = line;
		String[] results = l.split("\\|");
		actor = results[0];
		movie = results[1];
		if( !g.containsVertex(actor)){
			g.addVertex(actor);
		}
		if( !g.containsVertex(movie)){
			g.addVertex(movie);
		}
		if( !g.containsEdge(actor,movie)){
			g.addEdge(actor, movie);
		}
	}
	
	public UndirectedGraph<String> getGraph(){
		return g;
	}
	

	
	
}
