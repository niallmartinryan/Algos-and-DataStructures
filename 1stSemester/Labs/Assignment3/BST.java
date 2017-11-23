/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author TODO
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtre

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { deleteVasi(key); return; }
        root = put(root, key, val);
    }
    // recursive
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    public void updateNode(Node x){						// make this private
    	 x.N = 1 + size(x.left) + size(x.right);
    	 System.out.println("updated : " + x.key);
    }
    public void updateAllNodes(Node node){
   		updateAllNodesPriv(node);
   		//prosuming this works for the moment.
    }
    private void updateAllNodesPriv(Node node){
    	if(node==null){
    		return;
    	}
    	updateAllNodesPriv( node.left);
    	updateNode(node);
    	updateAllNodesPriv(node.right);
    }
    

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: TODO
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
      return height(root);
    }
    private int height(Node n){
        if(n == null){
            return -1;
        }
        return 1 + findMax(height(n.left) , height(n.right)); // Dont think this will work.. might have to use Math.max
    }

    private int findMax(int first,int second){
        return (first>second)?first:second; 
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {                               // inorder traversal of the tree and stop at the (n+1)/2 node.
        if (isEmpty()) return null;
        // In order traversal of the tree. stop at n+1/2
        return selectKey(root, (size()+1)/2);
      //TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.
    }
    /*
    private Key medianTraverse(Node root){
	
       return newMedianActual(root);


    }
    */
    private Key selectKey(Node node, int pos){
		if(node==null || pos<0){
			return null;
		}
		int size = size(node.left);
		
		if(size+1==pos){
			return node.key;
		}
		if(size>= pos && node.left != null){
			return selectKey(node.left, pos);
		}
		else if(size< pos && node.right != null){
			return selectKey(node.right, pos - size-1);
		}
		else{
			return node.key;
		}
    }


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
      if (isEmpty()) return "()";
      
      System.out.print("\n");
      return "("+alternatePrintString(root)+ ")";
    }
  /*
    private void printInOrderKey(Node currentRoot){
        if(currentRoot ==null){
            return;
        } 
        printInOrderKey(currentRoot.left);
        System.out.println(currentRoot.key);     
        printInOrderKey(currentRoot.right);

    }
    private String printInOrderKeysActual(Node currentRoot){
        String result = "";
        return traverseInOrderString(currentRoot, result);
    }
*/
    private String alternatePrintString(Node node){
        if(node == null){
            return "";
        }
        return "("+alternatePrintString(node.left)+ ")" + node.key + "(" + alternatePrintString(node.right) + ")";
    }

/*	
    */
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
		if(root == null){
			return "-null";
		}
		return prettyPrintKeysPriv(root,0, "", false);
		
    }
    private String prettyPrintKeysPriv(Node node, int count,String result, boolean right){
    
    	if(node == null){
    		return result + "-null\n";
  	}

    	return	result + "0-"+ node.key + "\n" + prettyPrintKeysPriv(node.left, count, result + "1", right) + prettyPrintKeysPriv(node.right,count, result + "  \n",true) ;

	//    	prettyPrintKeysPriv(node.right, count, result, right);
    }
    
    
    public Node searchKeyParent(Key  key){
    	return searchKeyParentPriv(root, key, null);
    	
    }
    private Node searchKeyParentPriv(Node node, Key key, Node found){
         if(node==null){
            return found;
        }
        found = searchKeyParentPriv(node.left, key, found);
        if(node.left == null || node.right == null){
        	
        }
       else if (node.left.key == key || node.right.key == key){
        	if(node.left == root || node.right == key){
        		System.out.print("no, parent");
        		return null;
        	}
        	else{
        		System.out.println("Search Parent : "  +node.key);
        		return node;
        	}
        }
        found = searchKeyParentPriv(node.right, key, found);
		return found;
    }
    
    
    
    public Node searchKey(Key key){
    	return searchKeyPriv(root, key, null);
    }
	
	private Node searchKeyPriv(Node node, Key key, Node found){
        if(node==null){
            return found;
        }
        found = searchKeyPriv(node.left, key, found);
        if(node.key == key){
        	System.out.println("Search Node : "  +node.key);
        	return node;
        }
        found = searchKeyPriv(node.right, key, found);
		return found;
	}

	
    /**
     * Deteles a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
     //WORKS
     // THIS DELETION USES THE SUCCESSOR INSTEAD OF THE PREDECESSOR ALTHOUGH THIS LOOKS MORE PAINFUL
     // uses predecessor now.. ^^
     /*
     public void reDelete(Key key){
     
     	// if root
     	Node toDelete = searchKey(key);
     	if(toDelete == null){
     		System.out.println("non-existant key");
     		return;
     	}
     	if(toDelete.key == root.key){
     		// no child nodes..
     		if(toDelete.right == null && toDelete.left == null){
     			root =null;
     			return;
     		}
     		// if it has 2 nodes..replace with predecessor.. aka.. go left then max..
     		else if(toDelete.right != null && toDelete.left != null){
     			Node replace = findMax(toDelete.left);
     			if(deleteMax(toDelete.left)== false){
				
					replace.right = toDelete.right;
					root = replace;     			
					updateAllNodes(root);
					return;
     			}
     			else{
     				replace.left = toDelete.left;
     				replace.right = toDelete.right;
     				root = replace;
     				updateAllNodes(root);
     				return;
     			}
     		}
     		// if it has 1 node.. replace with that node..
			else if(toDelete.right != null || toDelete.left != null ){
				if(toDelete.right! =null){
					root = toDelete.right;
				}
				else{
					root = toDelete.left;
				}
				updateAllNodes(root);
				return;
			}   
     	}        	     	
     	
     	// no child nodes..
     	if(toDelete.left ==null && toDelete.right ==null){
     	
     	
     	}

     	// 2 child nodes..
     	else if(toDelete.left!= null && toDelete.left != null){
     	
     	
     	}
     	// 1 child nodes..
     	else if(toDelete.left != null || toDelete.right != null){
     	
     	
     	}
     	
     }
 */
    public void deleteVasi(Key key){
  		root = deleteVasi(root, key);
    }
    private Node deleteVasi(Node x, Key key){
   		if (x == null) return null;
    	int cmp = key.compareTo(x.key);
    	if      (cmp < 0) x.left  = deleteVasi(x.left,  key);
    	else if (cmp > 0) x.right = deleteVasi(x.right, key);
    	else { 
    	   if (x.right == null) return x.left;
    	   if (x.left  == null) return x.right;
    	   Node t = x;
    	   x = findMax(t.left);
    	   x.left = deleteMaxVasi(t.left);
    	   x.right = t.right;
    	} 
    	x.N = size(x.left) + size(x.right) + 1;
    	return x;
    }
    public void deleteMinVasi(){
    	deleteMinVasi(root);
    	return;
    }
    private Node deleteMinVasi(Node x){
    	if (x.left == null) return x.right;
    		x.left = deleteMinVasi(x.left);
    		x.N = 1 + size(x.left) + size(x.right);
    		return x;
    }
    private void deleteMaxVasi(){
    	deleteMaxVasi(root);
    }
    private Node deleteMaxVasi(Node x){
    	if (x.right == null) return x.right;
    		x.right = deleteMaxVasi(x.right);
    		x.N = 1 + size(x.left) + size(x.right);
    		return x;
    }
    public void delete(Key key) {						// try deleting stuff where its only one node and no subtree to that node.
		Node toDelete = searchKey(key);	
		if(toDelete== null){
			System.out.println("node not found buddy");
			return;	
		}
		System.out.println("key to delete : "+ toDelete.key);
		// need delete min?? ^^
		
		Node minNode = findMin(toDelete.right);
		if(minNode == null){
			System.out.println("min was null");
			// trying to delete a leaf..
			Node parent = searchKeyParent(key);
			if(parent != null & toDelete.right == null){
				parent.right = null;
			}
			else if(parent != null & toDelete.right != null){
				parent.right = toDelete.right;
			}

			return;
		}
		else{
			System.out.println("minFound : "+ minNode.key);
		}
		//***** shouldnt be checking this twice but for the moment.....
/*		if(minNode == toDelete.right){			// this condition means that the node to delete is the smallest node that isnt to the left of itself
			
			Node parent = searchKeyParent(key);
			parent.right = toDelete.right;
		}*/
//		else{
			System.out.println("minNode == "+ minNode.key);
			// need to delete min to the right of node to delete
			// if this returns null, then 
			if(deleteMin(toDelete.right)==false){
				Node parent = searchKeyParent(key);
				if(parent!=null){
					parent.right = null;
				}
				else{
					toDelete.left = root;
				}
			}
/*			else if(){
			
			
			
			}
*/			else{
				System.out.println(printKeysInOrder());
			
				minNode.left = toDelete.left;
				minNode.right = toDelete.right;
				// update links
				Node parent = searchKeyParent(key);
				if(parent == null && minNode != null){
					root = minNode ;
				
				}
			
			else{
				int difference = parent.key.compareTo(toDelete.key);
				// dont need this meow..
				System.out.println("Difference : "+  difference);
				// could easily make a function that finds out if a Node is in its leftSubtree or right.. but im going to just check if its less
				if(difference>0){
					parent.left = minNode;
			
				}
				else{
					parent.right = minNode;
				}
	//		}
			}
		}
		
		if(toDelete.key == root.key){
			System.out.println("changed root");
			root = minNode;
			System.out.println("new root : "+ root.key);
		}
		updateAllNodes(root);
//		System.out.println(findMax(root).key);
      
    }
    public void deleteActual(Key key){		// think its the same.. but the predecessor is the rightmost node in the left subtree of the node to delete ie.. findMax(toDelete.left)
    	Node toDelete = searchKey(key);
    	if(toDelete == null){
    		System.out.println("node not found buddy");
    		return;
    	}
    	System.out.println("Node to Delete : "+ toDelete.key);
    	Node predecessor = findMax(toDelete.left);
    	System.out.println("Predecessor to nodeToDelete : " + predecessor);
    
    }
    
    public boolean deleteMin(Node node){
    	if(node == null){
    		System.out.print("null");
    	}
    	return deleteMinPriv(node);
    }
    // might not have to be a boolean value here..
    private boolean deleteMinPriv(Node node){
    
    	if(node == null){
    		System.out.print("Empty subtree\n");		
			return false; 
   	 	}
   	 	//
   	 	if(node == root){
   	 		root = node.right;
   	 		return true;
   	 	}
   	 	/*
   	 	else if(node == null){
   	 		Node parent = searchKeyParent(node.key);
   	 		
   	 		// think i need a condition here
   	 		parent.right
   	 		
   	 		
   	 	}
   	 */
   	 	else if(node.left == null){
  	 		Node parent = searchKeyParent(node.key);
  	 		if(parent != null){
  	 			parent.right = node.right;
  	 			updateAllNodes(root);				// might  not have to do this.. as i do it at the end anyway
  	 		}
 		
  	 	}	
   	 	else{
			Node currentNode = node;
			Node prevNode = null;
			while(currentNode.left != null){
				prevNode = currentNode;
				currentNode = currentNode.left;
			}// HEEEEEERRRRREEEEEE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if(prevNode == null){			// this means the currentNode is the smallest in the subtree
				
			}
			prevNode.left = currentNode.right;			// might get null pointer.. not to sure + garbage collector will get unpointed to node.
			// currentNode is equal to node to delete?
			// now I need to update ranks of the nodes..
    	}
  		updateAllNodes(root);
  		return true;
    }
    public boolean deleteMax(Node node){
    	if(node == null){
    		System.out.print("Empty subtree\n");
    				
			return false; 
   	 	}
   	 	//wrong...
   	 	if(node == root){
   	 		root = node.left;
   	 		return true;
   	 	}// dont need the left bit here..
   	 	else if(node.right== null){
   	 		return false;
   	 	}
   	 	/*
   	 	else if(node == null){
   	 		Node parent = searchKeyParent(node.key);
   	 		
   	 		// think i need a condition here
   	 		parent.right
   	 		
   	 		
   	 	}
   	 */
   	 	else{
			Node currentNode = node;
			Node prevNode = null;
			while(currentNode.left != null){
				prevNode = currentNode;
				currentNode = currentNode.left;
			}// HEEEEEERRRRREEEEEE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if(prevNode == null){			// this means the currentNode is the smallest in the subtree
				
			}
			prevNode.left = currentNode.right;			// might get null pointer.. not to sure + garbage collector will get unpointed to node.
			// currentNode is equal to node to delete?
			// now I need to update ranks of the nodes..
    	}
  		updateAllNodes(root);
  		return true;
    
    
    
    
    
    }
    public Node findMin(Node node){
    	if(node==null){
    		System.out.println("Null in Find min");
    		return null;
    	}
    	if(node.left == null){
    		return node;
    	}
    	return findMin(node.left);    
    }
    private Node findMax(Node node){
    	if(node.right == null){
    		return node;
    	}
    	return findMax(node.right);  
    }
    
    public void printOutRanks(Node node ){
    	if(node==null){
    		return ;
    	}
    	printOutRanks(node.left);
    	System.out.println("Key : " +node.key+ " Rank : "+ node.N + "\n" );
    	printOutRanks(node.right);
    }
   	
    public static void main(String []args){
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
      
        System.out.println(bst.prettyPrintKeys());
  
    /*
        BST<Character, Integer> bst = new BST<Character, Integer>();
        bst.put('S', 4);
        bst.put('X', 3);
        bst.put('E', 6);
        bst.put('A', 8);
        bst.put('C', 8);
        bst.put('R', 8);
        bst.put('H', 8);
        bst.put('M', 8);
         System.out.print("Hi\n");
        System.out.println("search for S: " +bst.searchKey('S').key);
        bst.printOutRanks(bst.root);
        System.out.println("Median result : " +bst.median());
        System.out.println(bst.printKeysInOrder());
//     bst.deleteMin(bst.root);
//     System.out.println(bst.printKeysInOrder());
//     bst.printOutRanks(bst.root);
        								// dont know if this is correct implementation
       	System.out.print("Hi\n");
        bst.delete('E');
        System.out.println("Afer delete E");
        bst.delete('S');
        System.out.println("Afer delete S");
        bst.delete('X');
        
      
        System.out.println(bst.printKeysInOrder());
        bst.printOutRanks(bst.root);
        */
    }
}
