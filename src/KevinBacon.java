import java.io.*;
import java.util.*;
import javax.swing.*;

public class KevinBacon extends JFrame  {

	private static UndirectedGraph<String> g;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Icon graph = new ImageIcon("graph.jpg");
		Icon drive = new ImageIcon("drive.png");
		Icon fact = new ImageIcon("fact.gif");
		
		KevinBacon my_app = new KevinBacon();
		String[] files = my_app.searchFile(".");
		String fileLocation = (String)JOptionPane.showInputDialog(my_app, "Please choose a file: ", "File", JOptionPane.INFORMATION_MESSAGE, drive, files, null);
		if(fileLocation == null){
			System.out.println("Please choose a file.");
			return;
		}
		
		
		Parser parser = new Parser(fileLocation);
		g = parser.getGraph();
		
		
		
		String sourceActor = (String) JOptionPane.showInputDialog(my_app, "Who do you want to start with?:", "Bacon Number", JOptionPane.INFORMATION_MESSAGE, graph, null, null);
		String actor = (String) JOptionPane.showInputDialog(my_app, "Enter actor/actress:", "Bacon Number", JOptionPane.INFORMATION_MESSAGE, fact, null, null);
		if(!g.containsVertex(actor)){
			System.out.println("The actor " + actor +" does not exist. Please choose another.");
			return;
		}
		System.out.println(actor);
		
		
		Collection<String> movies = g.getNeighbors(actor); 
		Collection<String> movies2 = g.getNeighbors(sourceActor);
		
		
		Dijkstra dijkstra = new Dijkstra(g, sourceActor, actor);
		System.out.println(actor + " was in:"+ movies);
		System.out.println(sourceActor + " was in:"+ movies2);
		System.exit(0);
		
	}
	
	private String[] searchFile(String dirName){
		ArrayList<String> files = new ArrayList<String>();
		File directory = new File(dirName);
		for(File iter:directory.listFiles()){
			if(iter.getName().endsWith(".txt")){
				files.add(iter.getName());
			}
		}
		String[] fileArray = new String[files.size()];
		for(int idx = 0; idx < files.size(); idx++){
			fileArray[idx] = files.get(idx);
		}
		
		return fileArray;
	}

	
	
	
}




