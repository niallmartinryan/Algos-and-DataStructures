/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author
 *
 * @version 01/12/15 02:03:28
 */
public class FacebookCircles {
	private int N;
	private int[] id;
 	private int[] size;
 	private boolean[] checked;
  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  public FacebookCircles(int numberOfFacebookUsers) {
  	N = numberOfFacebookUsers;
     id = new int[numberOfFacebookUsers];
     size = new int[numberOfFacebookUsers];
     for(int i=0; i< size.length;i++){
      	size[i] = 1;
     }
     checked = new boolean[numberOfFacebookUsers];
     // could use the default function..
     for(int i =0; i< checked.length;i++){
     		checked[i] = false;
     }
     for (int i = 0; i < N; i++) id[i] = i;
  }

  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends( int p, int q ) {
	int i = find(p);
 	int j = find(q);
	if (i == j) return;
 	if  (size[i] < size[j]) { id[i] = j; size[j] += size[i]; } 
 	else                { id[j] = i; size[i] += size[j]; } 
  }
  /**	
  
  
  **/
  public int find(int i)
   {
      while (i != id[i])
   	{
     		id[i] = id[id[i]];
      	i = id[i];
   	}
   	return i;
   }
   
   // keep going to the parent, until it points to itself
  
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() {
    resetChecks();
    int count =0;
    for(int i=0 ;i< id.length;i++){
    	int index = find(i);
    	if(checked[index]== false){
    		checked[index] = true;
    		count++;
    	}
    }
    resetChecks();
    return count;
  }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
    // TODO
    int highest =0;
    for(int i=0; i< id.length;i++){
    	if(size[i]> highest){
    		highest = size[i];
    	}
    }
    return highest;
  }

  /**
   * @return the size of the median circle in the data already loaded.  S1+S2..Sn / #circles;
   */
  public int sizeOfAverageCircle() {
    // TODO
    int avg;
    int total = 0;
    for(int i =0; i< id.length; i++){
    	int root = find(i);
    	if(checked[root]==false){
    		checked[root] = true;
    		total += size[root];
    	}
    }
    avg = total/ numberOfCircles();
    return avg;
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
    int smallest = size[find(0)];
    for(int i=0; i< N ; i++){
    	int root = find(i);
    	if(size[root]< smallest){
    		smallest = size[root];
    	}
    }
    return smallest;
  }
  public void resetChecks(){
  	for(int i=0; i<N ; i++){
  		checked[i] = false;
  	}
  }
}
