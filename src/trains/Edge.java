package trains;

public class Edge {
	private char startPoint;
	private char endPoint;
	private int length;

	public Edge(char start, char end, int length) {
		this.startPoint = start;
		this.endPoint = end;
		this.length = length;
	}
	
	public Edge(String edge) {
		char[] charArray = edge.toCharArray();
		this.startPoint = charArray[0];
		this.endPoint = charArray[1];
		int len = edge.length();
		String distNum = edge.substring(2,len);
		this.length = Integer.valueOf(distNum).intValue();
	}
	
	public char getStartPoint() {
		return this.startPoint;
	}
	
	public char getEndPoint() {
		return this.endPoint;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public void updateLength(int newLength) {
		this.length = newLength;
	}
}
