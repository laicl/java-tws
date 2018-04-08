package trains;

import org.junit.Assert;
import org.junit.Test;

public class TestGraph {

	@Test
	public void testQuestion1to5() {
		Graph graph = new Graph();
		graph.createGraph("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7");
		//String givenRoute = "A-B-C"; //9
		//String givenRoute = "A-D";  //5
		//String givenRoute = "A-D-C";  //13
		//String givenRoute = "A-E-B-C-D";  //22
		String givenRoute = "A-E-D";  //NO SUCH ROUTE
		final String except = "NO SUCH ROUTE";
		Assert.assertEquals("CalDistance is wrong", except, graph.CalGivenRouteDis(givenRoute));
	}
	
	@Test
	public void testQuestion6() {
		Graph graph = new Graph();
		graph.createGraph("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7");
		//input : "CC3";
		//CDC, CEBC
		final String except = "2";
		Assert.assertEquals("Number of trips is wrong", except, graph.FindRouteswithMaxNum('C','C',3));
	}
	
	@Test
	public void testQuestion7() {
		Graph graph = new Graph();
		graph.createGraph("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7");
		//input : "AC4";
		//ABCDC, ADCDC, ADEBC
		final String except = "3";
		Assert.assertEquals("Number of trips is wrong", except, graph.FindRouteswithExactNum('A','C',4));
	}
	
	@Test
	public void testQuestion8to9() {
		Graph graph = new Graph();
		graph.createGraph("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7");
		//input : "AC"; //9
		//input : "BB" //9
		final String except = "9";
		Assert.assertEquals("shortest route is wrong", except, graph.FindShortRoute('A','C'));
	}
	
	@Test
	public void testQuestion10() {
		Graph graph = new Graph();
		graph.createGraph("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7");
		//input : "CC30"; //7
		// CDC, CDCEBC, CDEBC, CEBC, CEBCDC, CEBCEBC, CEBCEBCEBC
		final String except = "7";
		Assert.assertEquals("distance is wrong", except, graph.FindRoutesWithCost('C','C',30));
	}
}
