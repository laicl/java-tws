/** 
 * Graph represents create graph.
 * option1 represents calculating the distance of the given route.
 * option2 represents calculating the number of trips within a certain number of stops.
 * option3 represents calculating the number of trips, which the number of stops is fixed.
 * option4 represents calculating the shortest route.
 * option5 represents calculating the number of different routes within a certain distance.
 * 
*/
package trains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class client {
	/** 
	Read text file.
	@param file,      the file path. 
	@param inputs,    graph info and option. 
	*/
	public static void readTxtFile(String file, List<String> inputs) {
		try {
			FileReader fileReader=new FileReader(file);
			BufferedReader bufReader=new BufferedReader(fileReader);
			String graphInfo;
			while((graphInfo = bufReader.readLine()) != null){
				graphInfo = graphInfo.replaceAll(" ", "");
				inputs.add(graphInfo);
			}
			bufReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		String file = "src/trains/input.txt";
		List<String> inputs = new ArrayList<String>();
		readTxtFile(file, inputs);
		Graph graph = new Graph();
		int caseNum = 1;
		Iterator<String> iter = inputs.iterator();
		while (iter.hasNext()) {
			String[] operation = iter.next().split(":");
			if (operation[0].equals("Graph")) {
				graph.createGraph(operation[1]);
			}else if (operation[0].equals("option")) {
				String[] tmpOpInfo = operation[1].split(",");
				String result = graph.GraphOperation(tmpOpInfo[0], tmpOpInfo[1]);
				System.out.println("Output #"+ (caseNum++) + ": "+ result);
			}
		}
		
	}
}
