import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////

class Node {

    public int iData;              // data item (key)
    public Node leftChild;         // this node's left child
    public Node rightChild;        // this node's right child

    Node(int id) {
        iData = id;
    }

    public void displayNode() // display ourself
    {
        System.out.print('{');
        System.out.print(iData);
        System.out.print("} ");
    }
}  // end class Node
////////////////////////////////////////////////////////////////

class Tree {
	int x=0;
	int y=0;

    private Node root;             // first node of tree

// -------------------------------------------------------------
    public Tree() // constructor
    {
        root = null;
    }            // no nodes in tree yet

// -------------------------------------------------------------
    public void insert(int id) //without using recursion
    {
        Node newNode = new Node(id);    // make new node

        if (root == null) // no node in root
        {
            root = newNode;
        } else // root occupied
        {
            Node current = root;       // start at root
            Node parent;
            while (true) // (exits internally)
            {
                parent = current;
                if (id < current.iData) // go left?
                {
                    current = current.leftChild;
                    if (current == null) // if end of the tree,
                    {                 // insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                } // end if go left
                else // or go right?
                {
                    current = current.rightChild;
                    if (current == null) // if end of the tree
                    {                 // insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                }  // end else go right
            }  // end while
        }  // end else not root
    }  // end insert()

// -------------------------------------------------------------
    public void insert2(int id) {                 // recursive insert function
        if (root == null) {
            root = new Node(id);
        } else {
            insertRecursive(root, id);
        }
    }

    public void insertRecursive(Node current, int id) {
        if (id < current.iData) {
            if (current.leftChild == null) {
                current.leftChild = new Node(id);
            } else {
                insertRecursive(current.leftChild, id);
            }
        } else if (current.rightChild == null) {
            current.rightChild = new Node(id);
        } else {
            insertRecursive(current.rightChild, id);
        }
    }
    
//--------------------------------------------------------------    
    public int findHeight(Node t){
    	if(t==null)
    		return 0;
    	if(t.leftChild==null&&t.rightChild==null)
    		return 0;
    	return(1+Math.max(findHeight(t.leftChild),findHeight(t.rightChild)));
    }
//--------------------------------------------------------------    
    public int countPrime(Node t){	
    	int counter=0;
    	
    
    	if(t!=null)
    	{	
    
    	for(int i=2;i<t.iData;i++){
    		if(t.iData%i==0)
    			counter++;
    	}	
    		if(counter==0){
    			x++;
    			System.out.println(t.iData);
    		}
    	countPrime(t.leftChild);
    	countPrime(t.rightChild);
    	}	
    		return x;
    }
//--------------------------------------------------------------    
    public  int hasOneChild(Node t)
    {
    	if(t!=null){
    		 if(t.leftChild==null&&t.rightChild==null||t.leftChild!=null&&t.rightChild!=null);
    		 else
    		 y++;
    		 
    	 hasOneChild(t.leftChild);
    	 hasOneChild(t.rightChild);	
    	}
    		
    	return y;
    	
      /* if(t == null || numberOfChildren(t)==0){
           return 0;
       }
       if(numberOfChildren(t)==1){
           return 1+ hasOneChild(t.leftChild) + hasOneChild(t.rightChild);
       }
           if(numberOfChildren(t)==2 ){
           return hasOneChild(t.leftChild) + hasOneChild(t.rightChild);
      }
           return 0;*/
    }
//--------------------------------------------------------------
  public  int numberOfChildren (Node t){
      int count = 0;
      if(t.leftChild != null ) 
    	  count++;
      if(t.rightChild != null)
    	  count++;       
      return count;   
      }
    
// -------------------------------------------------------------
    public void delete (int x){
        delete(x,root);
    }
// -------------------------------------------------------------

    public Node delete(int x, Node t) {
        if (t == null) {
            return t; // Item not found; do nothing
        }
        if (x < t.iData) {
            t.leftChild = delete(x, t.leftChild);
        } else if (x>t.iData) {
            t.rightChild = delete(x, t.rightChild);
        } else if (t.leftChild != null && t.rightChild != null) // Two children
        {
            t.iData = findMin(t.rightChild).iData;
            t.rightChild = delete(t.iData, t.rightChild);
        } else if(t.leftChild != null) {
            t.iData = findMax(t.leftChild).iData;
            t.leftChild = delete(t.iData,t.leftChild);
        }else if (t.rightChild != null) {
            t.iData = findMin(t.rightChild).iData;
            t.rightChild = delete(t.iData, t.rightChild);
        }else {
            t = null;
        }
        
        return t;
    }
// -------------------------------------------------------------
    public Node findMin(Node cur){
        while(cur.leftChild!=null){
            cur = cur.leftChild;
        }
        return cur;
    }
// -------------------------------------------------------------
    public Node findMax(Node cur){
        while(cur.rightChild!=null){
            cur = cur.rightChild;
        }
        return cur;
    }
// -------------------------------------------------------------

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }
// -------------------------------------------------------------

    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
// -------------------------------------------------------------

    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }
// -------------------------------------------------------------

    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }
// -------------------------------------------------------------

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }

            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null
                            || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(' ');
                }
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        }  // end while isRowEmpty is false
        System.out.println(
                "......................................................");
        System.out.println("The Height of the tree is "+findHeight(root));
        System.out.println("prime numbers are :");
       System.out.println( "The number of prime numbers are "+countPrime(root));
        System.out.println("The number of one childed nodes "+hasOneChild(root));
    }  // end displayTree()
// -------------------------------------------------------------
}  // end class Tree
////////////////////////////////////////////////////////////////

class TreeApp {

    public static void main(String[] args) throws IOException {
        Tree theTree = new Tree();

        theTree.insert(50);
        theTree.insert(25);
        theTree.insert(75);
        theTree.insert(12);
        theTree.insert(37);
        theTree.insert2(43);
        theTree.insert2(30);
        theTree.insert2(33);
        theTree.insert2(87);
        theTree.insert2(93);
        theTree.insert2(97);
        theTree.insert2(18);
        theTree.insert2(23);
        theTree.insert2(51);
        theTree.insert2(28);
        theTree.insert2(3);
        theTree.insert2(80);
        

        theTree.displayTree();
        /*theTree.delete(33);
        theTree.displayTree();
        theTree.delete(50);
        theTree.displayTree();
        theTree.delete(51);
        theTree.displayTree();
        theTree.delete(43);
        theTree.displayTree();
        theTree.delete(37);
        theTree.displayTree();*/
    }
}
