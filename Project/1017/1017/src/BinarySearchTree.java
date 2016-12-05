// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate
import java.util.*;
/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 * @Modified by Lizhong Zhang
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }


    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    public String toString()
    {
        if( isEmpty( ) )
            return "Empty tree";
        else
            return "in-order: " + inOrderPrintTreetoString(root) + "\n" + "pre-order: " + preOrderPrintTreetoString(root);

    }
    private String inOrderPrintTreetoString( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            return inOrderPrintTreetoString( t.left ) + " " + t.element.toString() + " " + inOrderPrintTreetoString( t.right );
        }
        return "";
    }
    private String preOrderPrintTreetoString( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            return t.element.toString() + " " + preOrderPrintTreetoString( t.left ) + " " + preOrderPrintTreetoString( t.right );
        }
        return "";
    }


    public int nodeCount()
    {
        return nodeCount(root);
    }
    private int nodeCount( BinaryNode<AnyType> t )
    {
        if(t == null)
            return 0;
        else
            return 1 + nodeCount(t.left) + nodeCount(t.right);
    }

    public boolean isFull()
    {
        return isFull(root);
    }
    private boolean isFull(BinaryNode<AnyType> t)
    {
        if(t.left == null && t.right == null)
            return true;
        else if(t.left != null && t.right != null)
            return isFull(t.left) && isFull(t.right);
        else
            return false;
    }

    public boolean compareStructure(BinarySearchTree<AnyType> s)
    {
        if(this.root == null && s.root == null)
            return true;
        else
            return compareStructure(this.root, s.root);
    }
    private boolean compareStructure(BinaryNode<AnyType> t, BinaryNode<AnyType> s)
    {
        if(t == null && s == null)
            return true;
        else if(t != null && s != null)
            return (compareStructure(t.left, s.left) && compareStructure(t.right, s.right));
        else
            return false;
    }

    public boolean equals(BinarySearchTree<AnyType> s)
    {
        if(this.root == null && s.root == null)
            return true;
        else
            return equals(this.root, s.root);
    }
    private boolean equals(BinaryNode<AnyType> t, BinaryNode<AnyType> s)
    {
        if(t == null && s == null)
            return true;
        else if(t != null && s != null)
            return (t.element == s.element && equals(t.left, s.left) && equals(t.right, s.right));
        else
            return false;
    }

    public BinarySearchTree<AnyType> copy()
    {
        BinarySearchTree<AnyType> s = new BinarySearchTree<>();
        s.root = copy(this.root);
        return s;
    }
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t)
    {
        if(t == null)
            return null;
        else
            return new BinaryNode<AnyType>(t.element, copy(t.left), copy(t.right));
    }

    public BinarySearchTree<AnyType> mirror()
    {
        BinarySearchTree<AnyType> s = new BinarySearchTree<>();
        s.root = mirror(this.root);
        return s;
    }
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t)
    {
        if(t == null)
            return null;
        else
            return new BinaryNode<AnyType>(t.element, mirror(t.right), mirror(t.left));
    }

    public boolean isMirror(BinarySearchTree<AnyType> s)
    {
        if(this.root == null && s.root == null)
            return true;
        else
            return isMirror(this.root, s.root);
    }
    private boolean isMirror(BinaryNode<AnyType> t, BinaryNode<AnyType> s)
    {
        if(t == null && s == null)
            return true;
        else if(t != null && s != null)
            return (isMirror(t.left, s.right) && isMirror(t.right, s.left));
        else
            return false;
    }

    public void rotateRight(AnyType x)
    {
    	if(search(x, this.root))
    		System.out.println(this);
    	else
    		System.out.println("there is no rotation");
    }
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t)
    {
    	BinaryNode<AnyType> s = t.left;
    	t.left = s.right;
    	s.right = t;
    	return s;
    }
    private boolean search(AnyType x, BinaryNode<AnyType> t)
    {
    	if (t == null)
    		return false;
		int compareResult = x.compareTo( t.element );
		if (compareResult == 0)
		{
				root = rotateRight(root);
				return true;
		}

		if (compareResult > 0)
		{
			compareResult = x.compareTo( t.right.element);
        	if( compareResult == 0 )
        	{
        		t.right = rotateRight(t.right);
        		return true;
        	}
        	else //if( compareResult > 0 )
        		return search(x, t.right);
		}
		else // if (compareResult < 0)
		{
			compareResult = x.compareTo( t.left.element);
        	if( compareResult == 0 )
        	{
        		t.left = rotateRight(t.left);
        		return true;
        	}
        	else //if( compareResult > 0 )
        		return search(x, t.left);
		}

    }

    public void rotateLeft(AnyType x)
    {
        if(search2(x, this.root))
            System.out.println(this);
        else
            System.out.println("there is no rotation");
    }
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t)
    {
        BinaryNode<AnyType> s = t.right;
        t.right = s.left;
        s.left = t;
        return s;
    }
    private boolean search2(AnyType x, BinaryNode<AnyType> t)
    {
        if (t == null)
            return false;
        int compareResult = x.compareTo( t.element );
        if (compareResult == 0)
        {
                root = rotateLeft(root);
                return true;
        }

        if (compareResult > 0)
        {
            compareResult = x.compareTo( t.right.element);
            if( compareResult == 0 )
            {
                t.right = rotateLeft(t.right);
                return true;
            }
            else //if( compareResult > 0 )
                return search2(x, t.right);
        }
        else // if (compareResult < 0)
        {
            compareResult = x.compareTo( t.left.element);
            if( compareResult == 0 )
            {
                t.left = rotateLeft(t.left);
                return true;
            }
            else //if( compareResult > 0 )
                return search2(x, t.left);
        }

    }

    public void printLevels( )
    {
    	Queue<BinaryNode<AnyType>> queue = new LinkedList<BinaryNode<AnyType>>();
    	queue.add(root);
    	while(!queue.isEmpty())
    	{
    		BinaryNode<AnyType> node = queue.poll();
    		System.out.print(node.element+ " ");
    		if(node.left != null)
    			queue.add(node.left);
    		if(node.right != null)
    			queue.add(node.right);
    	}

    }
    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt)
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }



      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        BinarySearchTree<Integer> s = new BinarySearchTree<>( );
        BinarySearchTree<Integer> w = new BinarySearchTree<>( );
        BinarySearchTree<Integer> n = new BinarySearchTree<>( );
        final int NUMS = 50;
        final int GAP  = 5;
        final int []array = {33, 20, 30, 40, 50, 41, 31,  21, 11};

       // System.out.println( "Checking... (no more output means success)" );

        for( int i = 0; i < 9; i++ )
        {
            t.insert( array[i] );
            w.insert( array[i] );
        }

        /*
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        {
            t.insert( i );
            w.insert( i );
        }*/
        for( int i = 10; i != 0; i = ( i + 10 ) % NUMS )
        {
            s.insert( i );
        }

        n = t.mirror();

        System.out.println(t);
        System.out.println(s);
        System.out.println(t.equals(s));
        System.out.println(t.isFull());
        System.out.println(t.nodeCount());
        System.out.println(t.compareStructure(w));
        System.out.println(t.compareStructure(s));
        System.out.println(t.copy());
        System.out.println(s.copy());
        System.out.println(t.mirror());
        System.out.println(t.isMirror(n));
        System.out.println(s.isMirror(n));
        t.rotateRight(20);
        t.rotateLeft(20);
        t.printLevels();

        /*for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );*/

        /*if( NUMS < 40 )
            t.printTree( );
            System.out.println(t.printTree().toString);
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }*/
    }
}
