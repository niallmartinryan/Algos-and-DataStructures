import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 12/10/15 20:44:01
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 * Niall's a gee-bag?
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    private int size =0;
    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList(){
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     * Niall's a gee-bag ;)												WHO IN GODS NAME PUT THIS HERE!!!!!!!!!!!!!!!!!!1
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
	// if all elements are empty? or there are no nodes besides head and tail	; I used the no available nodes approach, dont know which is correct
    public boolean isEmpty(){										// could also just check if size == 0
		if(head.equals( null) && tail.equals( null)){
			return true;
		}
		return false;
	}
    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void insertBefore( int pos, T data ){ 							// Might start using method getLength() to make code more readable, but will take up efficiency I believe
   	DLLNode nodeToInsert = new DLLNode(data,null,null);
 		if(size ==0){
 			System.out.println("nothing here, so add anywhere");
 			head = nodeToInsert;
 			tail = nodeToInsert;
 			nodeToInsert.next = null;
 			nodeToInsert.prev = null;
 			size++;
 		}
 		else if(pos<=0){
 			System.out.println("Pos less than zero");
 			DLLNode tempHead = head;
 			head = nodeToInsert;
 			nodeToInsert.next = tempHead;
 			size++;
 			//prev is already set to be null
 		}
 		else if(pos>=size){
 			System.out.println("Pos more than size");
 			DLLNode tempTail = tail;
 			tail = nodeToInsert;
 			nodeToInsert.prev = tempTail;
 			tempTail.next = nodeToInsert;
 			size++;
 		}
 		else{
 			System.out.println("just gonna put it here");
 			int i =0;
 			DLLNode currentNode = head;
 			while(i<pos){
 				currentNode = currentNode.next;
 				i++;	
 			}
 			DLLNode prevTemp = currentNode.prev;
 			currentNode.prev = nodeToInsert;
 			nodeToInsert.next = currentNode;
 			nodeToInsert.prev = prevTemp;
 			prevTemp.next = nodeToInsert;
 			size++;
 		}
 	 }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     *
     * Worst-case precise runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T get(int pos){
    	if(pos<0){
    		return null;
    	}
    	else if(pos>=size){
    		return null;
    	}
    	int i =0;
    	DLLNode currentNode = head;
    	while(i<size){
    		if (i==pos){
    			return currentNode.data;
    		} 
    		currentNode = currentNode.next;
    		i++;
    	}
      return null;					// shouldnt get here anyway.
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public boolean deleteAt(int pos) {	
    	DLLNode currentNode = head;
    	DLLNode nextNode;
    	DLLNode prevNode;
    	int i =0;
    	while(i<pos){
    		if(i>=size){
    			System.out.print("Position is out of bounds");
    			return false;
    		}
    		currentNode = currentNode.next;
    		i++;
    	}
    	if(currentNode.equals(tail)){
    		System.out.println("last element");
    		prevNode = currentNode.prev;
    		tail = prevNode;
    		prevNode.next = null;
    	}
    	else{
    		nextNode = currentNode.next;										//Assigning Nodes to prev and next
    		prevNode = currentNode.prev;
    	
    		nextNode.prev = prevNode;											//change pointers of prev and next to remove currentNode
    		prevNode.next = nextNode;												
    	}																		// I might have to make it current(deleted)Node = null, so it knows its garbage?? ^^
      	size--;																		// will have to check all conditions for returning a true or false value
      	return true;	
    }

	// IN THE STACK, I PROSUME THE HEAD OF THE STACK IS THE FRONT AND THUS THE NEXT ELEMENT OF THE HEAD IS ALWAYS NULL
    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void push(T item){
    	DLLNode toAdd = new DLLNode(item, null, null);
    	if(head.equals(null)){
    		head = toAdd;
    		tail = toAdd;
    	}
    	else{
    		toAdd.next = head;
    		head.prev = toAdd;
    		head = toAdd;
    	}
    	size++;
      //TODO
    }

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T pop(){
   		DLLNode toPop;
   		if(isEmpty()){
   			return null;
   		}
    	toPop = head;
    	DLLNode newTop = toPop.next;
    	head = newTop;
    	newTop.prev = null;
    	size--;
    	return toPop.data;
    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void enqueue(T item){
    	DLLNode toQueue = new DLLNode(item , null ,null);
    	if(isEmpty()){
    		head= toQueue;
    		tail= toQueue;
    		toQueue.next = null;								// Dont know if i have to implement this because its a queue structure
    		toQueue.prev = null;	
    	}
    	else{
    		DLLNode precedingNode = head;
    		head = toQueue;
    		toQueue.next = precedingNode;							// Not sure I need to waste time creating the pointer to the prev Node with these methods
    		toQueue.prev = null;
    		precedingNode.prev = toQueue	;						// as the queue structure doesnt require using prev pointers
    	}
    	size++;												
      //TODO
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T dequeue(){
    	if(isEmpty()){
    		return null;
    	}
    	DLLNode dequeue = tail;
    	DLLNode newTail = tail.prev;
    	tail = newTail;
    	newTail.next = null;
    	
      //TODO
      	size--;
      return dequeue.data;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString(){
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }
    // My methods will be placed down here..		// dont need this now..
    public int getListLength(){
    	int count = 0;
    	DLLNode currentNode = head;
    	if(currentNode ==null){
    		return count;
    	}
    	while(currentNode.next != null){
    		currentNode = currentNode.next;
    		count++;	
    	}
    	return count; 
    }
    public void insertNode(T data){
    	DLLNode newTail = new DLLNode(data,null,null);
    	DLLNode oldTail = tail;
    	oldTail.next = newTail;
    	newTail.prev = oldTail;
    	tail = newTail;
    	size++;
    }
    public static void main(String[] args){								//tests on all methods
	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    	 
    	list.insertBefore(0 , 5);
    	list.insertBefore(0 , 2);
	   	list.insertBefore(4 , 6);
	   	list.insertNode(10);
	   	System.out.println(list.toString());
	   	list.push(22);
	   	System.out.println(list.toString());
	   	list.enqueue(36);
	   	System.out.println(list.toString());
	   	list.enqueue(45);
	   	System.out.println(list.toString());
	   	list.dequeue();
	   	System.out.println(list.toString());
	   	System.out.println(list.pop());
	   	System.out.println(list.toString());
	   	list.deleteAt(2);
	   	System.out.println(list.toString());
	   	list.deleteAt(3);
	   	System.out.println(list.toString());
	   	System.out.println(list.get(0));
	   	System.out.println(list.get(1));
	   	System.out.println(list.get(2));
	   	System.out.println(list.get(3));
	   	System.out.println(list.get(4));  	
    
    }
}



