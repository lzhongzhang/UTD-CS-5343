/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }

    private void clear( )
    {
        doClear( );
    }

    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }

    public boolean isEmpty( )
    {
        return size( ) == 0;
    }

    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );
        return true;
    }

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }

    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;

        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );

        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }

    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    public void swap(int idx1, int idx2)
    {
        Node<AnyType> m = getNode(idx1);
        Node<AnyType> n = getNode(idx2);
        /*Node<AnyType> mPrev = getNode(idx1).prev;
        Node<AnyType> mNext = getNode(idx1).next;*/
        Node<AnyType> nPrev = n.prev;
        Node<AnyType> nNext = n.next;
        m.prev.next = n;
        m.next.prev = n;
        n.prev = m.prev;
        n.next = m.next;

        nPrev.next = m;
        nNext.prev = m;
        m.prev = nPrev;
        m.next = nNext;
        modCount++;
        return;
    }

    private void shift_backward() // backward
    {
        Node<AnyType> p = endMarker.prev;
        p.prev.next = endMarker;
        endMarker.prev = p.prev;
        p.prev = beginMarker;
        p.next = beginMarker.next;
        beginMarker.next = p;
        p.next.prev = p;
        return;
    }

    private void shift_forward() // forward
    {
        Node<AnyType> p = beginMarker.next;
        p.next.prev = beginMarker;
        beginMarker.next = p.next;
        p.prev = endMarker.prev;
        p.next = endMarker;
        endMarker.prev = p;
        p.prev.next = p;
        return;
    }

    public void shift(int i)
    {
        if (i > 0)
        {
            for(int n = 0; n < i ; n++)
            {
                shift_forward();
                modCount++;
            }
            return;
        }
        else if (i < 0)
        {
            for(int n = 0; n > i; n--)
            {
                shift_backward();
                modCount++;
            }
            return;
        }
        else
            return;
    }


    public void erase(int idx, int i)
    {
        Node<AnyType> p, q;
        if(idx == 0)
            p = beginMarker;
        else
            p = getNode(idx-1);
        if(idx + i == theSize)
            q = endMarker;
        else
            q = getNode(idx+i);
        p.next = q;
        q.prev = p;

        /*Node<AnyType> p = getNode(idx);
        for(int n = 0; n < i; n++)
        {
            p.next.prev = p.prev;
            p.prev.next = p.next;
            p = p.next;
            theSize--;
            modCount++;
        }*/
        return;
    }


    public void insertList(int idx, MyLinkedList<AnyType> lst)
    {
        Node<AnyType> p = getNode(idx-1);
        Node<AnyType> q = getNode(idx);
        p.next = lst.beginMarker.next;
        lst.beginMarker.next.prev = p;
        q.prev = lst.endMarker.prev;
        lst.endMarker.prev.next = q;
        theSize = theSize + lst.size();
        modCount++;
        return;
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext( )
        {
            return current != endMarker;
        }

        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( );

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );

            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }

        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
            lst.add( i );
        for( int i = 20; i < 30; i++ )
            lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );

        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
            itr.next( );
            itr.remove( );
            System.out.println( lst );
        }

        /* Test swap function. */
        for( int i = 0; i < 15; i++ )
            lst.add( i );
        System.out.println( lst );
        lst.swap(3,10);
        System.out.println( lst );
        lst.doClear();

        /* Test shift function. */
        for( int i = 0; i < 15; i++ )
            lst.add( i );
        System.out.println( lst );
        lst.shift(5);   //shift forward.
        System.out.println( lst );
        lst.doClear();

        for( int i = 0; i < 15; i++ )
            lst.add( i );
        System.out.println( lst );
        lst.shift(-5);   //shift backward.
        System.out.println( lst );
        lst.doClear();

        /* Test erase function. */
        for( int i = 0; i < 15; i++ )
            lst.add( i );
        System.out.println( lst );
        lst.erase(0,10);
        System.out.println( lst );
        lst.doClear();

        /* Test insertList function. */
        for( int i = 0; i < 15; i++ )
            lst.add( i );
        System.out.println( lst );
        MyLinkedList<Integer> lst2 = new MyLinkedList<>( );
        for( int j = 20; j < 30; j++ )
            lst2.add( j );
        System.out.println( lst2 );
        lst.insertList(7, lst2);
        System.out.println( lst );
    }
}
