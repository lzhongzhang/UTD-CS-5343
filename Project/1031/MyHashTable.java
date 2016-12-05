import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashTable<AnyType>
{
    //Data domain
	private ArrayList <LinkedList<AnyType>> hashTable;
	private int table_size = 10000;

	//Constructor
	public MyHashTable()
	{
		hashTable = new ArrayList<>(nextPrime(table_size));

		for( int i = 0; i < nextPrime(table_size); i++ )
		{
			hashTable.add(new LinkedList<AnyType>());
		//System.out.println(hashTable.get(i));
		}


	}

	public void insert(AnyType x)
	{
		int index = this.hash(x);

		if(!(hashTable.get(index).contains(x)))
			hashTable.get(index).add(x);
	}

    public static int nextPrime(int n)
    {
        if(n % 2 == 0)
            n++;

        for(; !isPrime(n); n += 2)
            ;

        return n;
    }

    public static boolean isPrime(int n)
    {
        if(n == 2 || n == 3)
            return true;

        if(n == 1 || n % 2 == 0)
            return false;

        for(int i = 3; i * i <= n; i += 2)
            if( n % i == 0 )
                return false;

        return true;
    }

    public boolean contains(AnyType x)
    {
        LinkedList<AnyType> targetLocation = hashTable.get(hash(x));
        return targetLocation.contains(x);
    }

	public int hash(AnyType key)
	{
	   int hashVal = 0;
	   for(int index = 0; index < String.valueOf(key).length(); index++)
	   {
		   hashVal = 37 * hashVal + String.valueOf(key).charAt(index);
	   }
	   hashVal %= table_size;

	   //handle if overflow made hashVal negative
	   if(hashVal < 0)
	   {
		   hashVal += table_size;
	   }
	   return hashVal;
   }

	//output hash table
	public void print()
	{
		int max = 0;
		for (int i = 0; i < hashTable.size(); i++)
		{
			System.out.print(i + "\t");
			java.util.Iterator<AnyType> itr = hashTable.get(i).iterator();
			int count = 0;
			while ( itr.hasNext() )
			{
				System.out.print(itr.next() + "\t");
				count++;
			}
			max = max < count ? count : max;
			System.out.println();
		}
		System.out.println("Max: "+ max);
	}
}
