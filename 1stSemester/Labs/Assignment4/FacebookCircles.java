/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author
 *
 * @version 01/12/15 02:03:28
 */
import java.util.*;
import java.util.Iterator;
import java.util.ArrayList;

public class FacebookCircles {
	// this should be a small V
	private final int Vertices;
	private Element[] edges;
	
	public class Element{
		private final int ID;
		private boolean visited; 
		private ArrayList<Element> arrayElems;
		public Element(int ID){
			this.ID = ID+1;
			visited = false;
			arrayElems = new ArrayList<Element>();
		}
	}
	
	
  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  	public FacebookCircles(int numberOfFacebookUsers) {
	    	Vertices = numberOfFacebookUsers;
	    	edges = new Element[Vertices];
	    	for(int i=0; i< Vertices; i++){
	    		edges[i] = new Element(i);
	    	}
	}

  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  	public void friends( int user1, int user2 ) {
		edges[user1].arrayElems.add(edges[user2]);
		edges[user2].arrayElems.add(edges[user1]);
		 
   	 // TODO
 	 }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  	public int numberOfCircles() {
  		int circles=0;
  		for(int j =0; j < Vertices; j++){
  			Element currentElement = edges[j];
  			if(currentElement.visited ==false){
  				countInnerLoop(currentElement,0);
  				circles++;
  			}
  		}
   	 	clearElementVisits();
   	 	return circles;
 	 }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
   // works
  	public int sizeOfLargestCircle() {
  		int largestValue =0;
  		int count = 0;
  		int circleCount =0;
  		boolean stopInnerCheck = false;
  		if(Vertices ==0){
  			return largestValue;
  		}
 	   	 for(int j =0; j < Vertices; j++){
  			Element currentElement = edges[j];
  			if(currentElement.visited ==false){
  				circleCount++;
  				count = countInnerLoop(currentElement,0);
  				if(count>largestValue){
  					largestValue = count;
  				}
  			}  	  		
		}
		clearElementVisits();
		return largestValue;
  	}
  	// how dafuuuu do i get this working iteratively..hmm
  	public int countInnerLoop(Element currentElement, int count){

  		if(currentElement.visited == false){
  			count++;
  			currentElement.visited = true;
  		}
  		else{
  			return count;
  		}
  		for(Element e : edges[currentElement.ID-1].arrayElems){
  			if(e.visited == false){
  				count = countInnerLoop(e, count);
  			}
  			else{
  				// dont think this needs to do anything for the moment.	
  		  		}
  		}
  		return count;
  	}
 /* 	public int countInnerLoopIterative(Element currentElement){
  		int count =0;
  		for(){
  		
  		
  		}
  	
  	
  	
  	
  	
  	
  	}
  	*/

  /**
   * @return the size of the median circle in the data already loaded.
   */
 	 public int sizeOfAverageCircle() {
 	 	// probably a better way of doing this...
 	 	int total=0;
    		int count = 0;
  		boolean stopInnerCheck = false;
  		if(Vertices ==0){
  			return 0;
  		}
 	   	  for(int j =0; j < Vertices; j++){
  			Element currentElement = edges[j];
  			if(currentElement.visited ==false){
  				total += countInnerLoop(currentElement,0);
  			}
  		}
  		clearElementVisits();
   	 	return total/numberOfCircles() ;
  	}

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
 	 public int sizeOfSmallestCircle() {
 	 /*
 	 	if the currentCount is greater than the smallest circle so far.. can i just end the loop and move on?
 	 	may be problems with not visiting everyNode in the list.. as in the arent checked off.
 	 	not sure.. ANYWAY
 	 
 	 
 	 */
 	 	int smallestValue =0;
  		int count = 0;
  		int circleCount =0;
  		boolean firstValue = true;
  		boolean stopInnerCheck = false;
  		if(Vertices ==0){
  			return smallestValue;
  		}
 	   	for(int j =0; j < Vertices; j++){
  			Element currentElement = edges[j];
  			if(currentElement.visited ==false){
  				circleCount++;
  				count = countInnerLoop(currentElement,0);
  				if(count<smallestValue || firstValue == true){
  					smallestValue = count;
  					firstValue=false;
  				}
  			}
  		}
	clearElementVisits();
    	return smallestValue;
  	}
	public void printEdges(){
		for(int i=0; i< Vertices;i++){
			System.out.print("Vertex : " + (i+1) + "--");
			for(Element e : edges[i].arrayElems){
				System.out.println("ID : " + e.ID);
			}
		}
	
	
	} 
	public void clearElementVisits(){
		for(int i=0; i< edges.length; i++){
			edges[i].visited = false;
		}
	}
	public static void main(String [] args){
		FacebookCircles myCircles = new FacebookCircles(21);
		myCircles.friends(1,2);
		myCircles.friends(1,3);
		myCircles.friends(4,5);
		myCircles.friends(2,4);
		myCircles.friends(2,3);
		myCircles.friends(3,6);
		myCircles.friends(2,14);
		myCircles.friends(3,11);
		myCircles.friends(11,12);
		myCircles.friends(7,8);
		myCircles.friends(7,13);
		myCircles.friends(9,10);
		myCircles.friends(15,18);
		myCircles.friends(15,16);
		myCircles.friends(15,17);
		myCircles.friends(19,20);
		System.out.println("Number of circles : "+ myCircles.numberOfCircles());
		System.out.println("Size of largest Circle : " + myCircles.sizeOfLargestCircle());
		
		System.out.println("Median size Circle : " + myCircles.sizeOfAverageCircle());
		System.out.println("Size of smallest Circle : " + myCircles.sizeOfSmallestCircle());
	
	}

}
