package trains;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node {
	private char name;
	private List<Edge> edges;
	private final int INF = 0x3f3f3f3f;
	
	public Node(char nodeName) {
		this.name = nodeName;
		this.edges = new ArrayList<Edge>();
	}
	
	public void addEdge(char edgeEnd, int edgeLength) {
		this.edges.add(new Edge(this.name, edgeEnd, edgeLength));
	}
	
	public int getDistance(char edgeEnd) {
		Iterator<Edge> iter = this.edges.iterator();
		while (iter.hasNext()) {
			Edge tmpEdge = iter.next();
			if(tmpEdge.getEndPoint() == edgeEnd) {
				return tmpEdge.getLength();
			}
		}
		return INF;
	}
	
	public char getName() {
		return this.name;
	}
	
	public int isAdjoin(Node node) {
		Iterator<Edge> iter = this.edges.iterator();
		while (iter.hasNext()) {
			Edge tmpEdge = iter.next();
			if(tmpEdge.getEndPoint() == node.getName()) {
				return tmpEdge.getLength();
			}
		}
		return INF;
	}
	
	public int isAdjoin(char adjoinNodeName) {
		Iterator<Edge> iter = this.edges.iterator();
		while (iter.hasNext()) {
			Edge tmpEdge = iter.next();
			if(tmpEdge.getEndPoint() == adjoinNodeName) {
				return tmpEdge.getLength();
			}
		}
		return INF;
	}
	
	public char getAdjoinNodeName(int index) {
		return this.edges.get(index).getEndPoint();
	}
	
	public int getEdgesNum() {
		return this.edges.size();
	}
}
