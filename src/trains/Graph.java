package trains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Graph {
	private Set<Node> nodes;
	private final int INF = 0x3f3f3f3f;
	
	public Graph() {
		this.nodes = new HashSet<Node>();
	}
	
	public void createGraph(String input) {
		String[] graphInfo = input.split(",");
		for(int num=0; num<graphInfo.length; num++) {
			char[] charArray = graphInfo[num].toCharArray();
			String distNum = graphInfo[num].substring(2, graphInfo[num].length());
			Node tmpNode = getNodebyName(charArray[0]);
			if(tmpNode == null) {
				Node newNode = new Node(charArray[0]);
				newNode.addEdge(charArray[1], Integer.valueOf(distNum).intValue());
				this.addNode(newNode);
			}else {
				tmpNode.addEdge(charArray[1], Integer.valueOf(distNum).intValue());
			}
		}
	}
	
	public void addNode(Node node) {
		this.nodes.add(node);
	}
	
	public int getNodesDis(char startNodeName, char endNodeName) {
		Iterator<Node> iter = this.nodes.iterator();
		while (iter.hasNext()) {
			Node tmpNode = iter.next();
			if(tmpNode.getName() == startNodeName) {
				return tmpNode.getDistance(endNodeName);
			}
		}
		return INF;
	}
	
	public int CalDistance(String route) {
		String[] towns = route.split("-");
		int totalDis = 0;
		for(int num=0; num<towns.length - 1; num++) {
			char startNodeName = towns[num].charAt(0);
			char endNodeName = towns[num+1].charAt(0);
			int dis = getNodesDis(startNodeName, endNodeName);
			if (dis == INF) {
				totalDis = INF;
				break;
			}else {
				totalDis += dis;
			}
		}
		return totalDis;
	}
	
	public Node getNodebyName(char NodeName) {
		Iterator<Node> iter = this.nodes.iterator();
		Node tmpNode = null;
		while (iter.hasNext()) {
			tmpNode = iter.next();
			if(tmpNode.getName() == NodeName) {
				return tmpNode;
			}
		}
		return null;
	}
	
	public void DFS(char curNodeName, char endNodeName, String route, int spareStopNum, List<String> routes) {
		if (curNodeName == endNodeName && route.length()>0 && spareStopNum == 0) {
			route = route + String.valueOf(curNodeName);
			//System.out.println("==final route:=="+route);
			routes.add(route);
			route = route.substring(0, route.length() - 1);
			return;
		}else if(spareStopNum <= 0) {
			return;
		}
		
		Node curNode = getNodebyName(curNodeName);
		for (int adNodeId = 0; adNodeId < curNode.getEdgesNum(); adNodeId++) {
			char nextNodename = curNode.getAdjoinNodeName(adNodeId);
			route = route + String.valueOf(curNodeName);
			DFS(nextNodename, endNodeName, route, spareStopNum - 1, routes);
			route = route.substring(0, route.length() - 1);
		}
	}
		

	public int Dijkstrate(Node startNode, Node endNode) {
		List<Character> noVisitNodeName = new ArrayList<Character>();
		List<Edge> dist = new ArrayList<Edge>();
		int minDis = INF;
		Node nextNode = null;
		//init and find one of the shortest route start from startNode.
		Iterator<Node> iter = this.nodes.iterator();
		while (iter.hasNext()) {
			Node tmpNode = iter.next();
			int curDis = startNode.isAdjoin(tmpNode);
			if (minDis > curDis) {
				nextNode = tmpNode;
				minDis = curDis;
			}
			noVisitNodeName.add(tmpNode.getName());	
			dist.add(new Edge(startNode.getName(), tmpNode.getName(), curDis));
		}

		while (nextNode.getName() != endNode.getName() && !noVisitNodeName.isEmpty()) {
			noVisitNodeName.remove(new Character(nextNode.getName()));
			Iterator<Edge> edgeIter = dist.iterator();
			int tmpMinDis = INF;
			Node tmpNextNode = nextNode;
			while (edgeIter.hasNext()) {
				Edge tmpEdge = edgeIter.next();
				if (noVisitNodeName.contains(tmpEdge.getEndPoint())) {
					int tmplength = minDis + nextNode.isAdjoin(tmpEdge.getEndPoint());
					if(tmplength < tmpEdge.getLength()) {
						tmpEdge.updateLength(tmplength);
						
					}
					if (tmpEdge.getLength() < tmpMinDis) {
						tmpMinDis = tmpEdge.getLength();
						tmpNextNode = getNodebyName(tmpEdge.getEndPoint());
					}
					
				}
			}	
			if (nextNode.getName() == tmpNextNode.getName()) {
				System.out.println("CANNOT GET THE ROUTE");
				minDis = INF;
				break;
			}
			nextNode = tmpNextNode;
			minDis = tmpMinDis;
		}
		return minDis;
	}
	
	
	public void DFSWithCost(char curNodeName, char endNodeName, String route, int spareCost, List<String> routes) {
		if(spareCost <= 0) {
			return;
		} else if (curNodeName == endNodeName && route.length()>0 && spareCost >= 0) {
			route = route + String.valueOf(curNodeName);
			//System.out.println("==final route:=="+route);
			routes.add(route);
			route = route.substring(0, route.length() - 1);
		}
		
		Node curNode = getNodebyName(curNodeName);
		for (int adNodeId = 0; adNodeId < curNode.getEdgesNum(); adNodeId++) {
			char nextNodename = curNode.getAdjoinNodeName(adNodeId);
			route = route + String.valueOf(curNodeName);
			DFSWithCost(nextNodename, endNodeName, route, spareCost - curNode.getDistance(nextNodename), routes);
			route = route.substring(0, route.length() - 1);
		}
	}	
	
	/** 
	Q1-5: Calculate the distance of the given route.
	@param startNodeName,  the starting point. 
	@param endNodeName,    the ending point. 
	@param stopNum,        the stop number. 
	@return ,the length of the shortest route. 
	*/
	public String CalGivenRouteDis(String route) {
		int distance = CalDistance(route);
		if (distance != INF) {
			return (String.valueOf(distance));
		}else {
			return ("NO SUCH ROUTE");
		}
	}

	/** 
	Q6: Calculate the number of trips within a certain number of stops.
	@param startNodeName,  the starting point. 
	@param endNodeName,    the ending point. 
	@param maxStopNum,     the maximum number of stops.
	@return ,the number of trips. 
	*/	
	public String FindRouteswithMaxNum(char startNodeName, char endNodeName, int maxStopNum) {
		List<String> routes = new ArrayList<String>();
		String route = "";
		for (int stopNum = 1; stopNum <= maxStopNum; stopNum++) {
			DFS(startNodeName, endNodeName, route, stopNum, routes);
		}
		return String.valueOf(routes.size());
	}
	
	/** 
	Q7: Calculate the number of trips, which the number of stops is fixed.
	@param startNodeName,  the starting point. 
	@param endNodeName,    the ending point. 
	@param stopNum,        the stop number. 
	@return ,the number of trips. 
	*/
	public String FindRouteswithExactNum(char startNodeName, char endNodeName, int stopNum) {
		List<String> routes = new ArrayList<String>();
		String route = "";
		DFS(startNodeName, endNodeName, route, stopNum, routes);
		return String.valueOf(routes.size());
	}
	
	/** 
	Q8&9: Calculate the shortest route.
	@param startNodeName,  the starting point. 
	@param endNodeName,    the ending point. 
	@return ,the length of the shortest route. 
	*/
	public String FindShortRoute(char startNodeName, char endNodeName) {
		Node startNode = null;
		Node endNode = null;
		Iterator<Node> iter = this.nodes.iterator();
		while (iter.hasNext()) {
			Node tmpNode = iter.next();
			if(tmpNode.getName() == startNodeName) {
				startNode = tmpNode;
			}
			if(tmpNode.getName() == endNodeName) {
				endNode = tmpNode;
			}
			
			if(startNode != null && endNode != null) {
				break;
			}
		}
		int distance = Dijkstrate(startNode, endNode);
		if (distance != INF) {
			return (String.valueOf(distance));
		}else {
			return ("NO SUCH ROUTE");
		}
	}
	
	/** 
	Q10: Calculate the number of different routes within a certain distance.  
	@param startNodeName,  the starting point. 
	@param endNodeName,    the ending point. 
	@param maxCost,        the maximum distance. 
	@return ,the number of different routes. 
	*/
	public String FindRoutesWithCost(char startNodeName, char endNodeName, int maxCost) {
		List<String> routes = new ArrayList<String>();
		String route = "";
		DFSWithCost(startNodeName, endNodeName, route, maxCost, routes);
		return String.valueOf(routes.size());
	}

	public String GraphOperation(String option, String fullRoute) {
		String result = "";
		char begining = 0;
		char ending = 0;
		int cost = 0;
		
		if (!option.equals("1")) {
			/*A-B-10 --> A B 10*/
			String[] route = fullRoute.split("-");
			begining = route[0].charAt(0);
			ending = route[1].charAt(0);
			if (route.length == 3) {
				String distNum = route[2].substring(0, route[2].length());
				cost = Integer.valueOf(distNum).intValue();
			}
		}

		switch (option) {
		case "1":
			result = CalGivenRouteDis(fullRoute);
			break;
		case "2":
			result = FindRouteswithMaxNum(begining, ending, cost);
			break;
		case "3":
			result = FindRouteswithExactNum(begining, ending, cost);
			break;
		case "4":
			result = FindShortRoute(begining, ending);
			break;
		case "5":
			result = FindRoutesWithCost(begining, ending, cost);
			break;
		default:
			result = "UNKNOWN OPERATION";
			break;
		}
		return result;
	}
	
}
