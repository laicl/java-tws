===DESCRIPTION===
A. Graph.java
1. Graph graph = new Graph();
2. Create a new graph by graph.createGraph("AB3,CD4,EF5").
3. Calculate the distance of given route by graph.CalGivenRouteDis("A-B-C"), 
   which corresponds "option 1" in input.txt.
4. Calculate the number of trips within a certain number of stops 
   by graph.FindRouteswithMaxNum('A','B',3), which corresponds "option 2"
   in input.txt.
5. Calculate the number of trips, which the number of stops is fixed,
   by graph.FindRouteswithExactNum('A','B',3), which corresponds "option 3"
   in input.txt.
6. Calculate the shortest route by graph.FindShortRoute('A','B'), which 
   corresponds "option 4" in input.txt.
7. Calculate the number of different routes within a certain distance 
   by graph.FindRoutesWithCost('A','B',30), which corresponds 
   "option 5" in input.txt.

B. Client.java
The main entry point for the program is in the Client.java. 

C. input.txt
Input data via this text file.
1. Please input the Graph representation by the specified format, 
   which starts with 'Graph:' and separates two edges by a comma. 
   Take, for example, the simple input:'Graph:AB3,CD4,EF5'.
2. You can choose the graph operation by 'option:'.
   Take, for example, the simple input:'option:1,A-B-C'.
   
D. TestGraph.java
Test the graph operation by JUNIT.








