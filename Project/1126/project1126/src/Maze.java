import java.util.*;

public class Maze
{
    class Square
    {
        public boolean up;
        public boolean down;
        public boolean left;
        public boolean right;

        public Square()
        {
            down = true;
            right = true;
        }

        public void print()
        {
            if(down == true)
                System.out.print("_");
            else
                System.out.print(" ");
            if(right == true)
                System.out.print("|");
            else
                System.out.print(" ");
        }
    }

    private Square[] self;
    private int row;
    private int column;
    private int length;

    public Maze(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.length = row * column;
        this.self = new Square[this.length];

        for(int i = 0; i < this.length; i++)
        {
            self[i] = new Square();
        }
        
        DisjSets ds = new DisjSets(length);
        
        for(int count = 1; count <= length - 1;  )
        {
        	for(boolean flag = false; !flag; )
        	{
        		int a;
        		int i = (int) (Math.random() * length);
        		int j;
        		if(i == 0)
    			{
    				a = (int)(Math.random() * 2);
    				if(a == 0)
    					j = i + 1;
    				else
    					j = i + column;
    			}
        		else if(i == column - 1)
        		{
    				a = (int)(Math.random() * 2);
    				if(a == 0)
    					j = i - 1;
    				else
    					j = i + column;
    			}
        		else if(i == length - column)
        		{
    				a = (int)(Math.random() * 2);
    				if(a == 0)
    					j = i - column;
    				else
    					j = i + 1;
    			}	
        		else if(i == length - 1)
        		{
    				a = (int)(Math.random() * 2);
    				if(a == 0)
    					j = i - 1;
    				else
    					j = i - column;
    			}
        		else if(i > 0 && i < column - 1)
        		{
        			a = (int)(Math.random() * 3);
    				if(a == 0)
    					j = i - 1;
    				else if(a == 1)
    					j = i + 1; 	
    				else
    					j = i + column;
        		}
        		else if(i % column == 0)
        		{
        			a = (int)(Math.random() * 3);
    				if(a == 0)
    					j = i - column;
    				else if(a == 1)
    					j = i + 1; 	
    				else
    					j = i + column;
        		}	
        		else if(i > length - column && i < length - 1)
        		{
        			a = (int)(Math.random() * 3);
    				if(a == 0)
    					j = i - column;
    				else if(a == 1)
    					j = i - 1; 	
    				else
    					j = i + 1;
        		}
        		else if(i % column == column - 1)
        		{
        			a = (int)(Math.random() * 3);
    				if(a == 0)
    					j = i - column;
    				else if(a == 1)
    					j = i - 1; 	
    				else
    					j = i + column;
        		}
        		else
        		{
        			a = (int)(Math.random() * 4);
    				if(a == 0)
    					j = i - column;
    				else if(a == 1)
    					j = i - 1; 	
    				else if(a == 2)
    					j = i + 1;
    				else
    					j = i + column;
        		}
        		int set1 = ds.find(i);
        		int set2 = ds.find(j);
        		if(set1 != set2)
        		{
        			ds.union(set1, set2);
        			if(j == i - column)
        				this.self[j].down = false;
        			else if(j == i - 1)
        				this.self[j].right = false;
        			else if(j == i + 1)
        				this.self[i].right = false;
        			else if(j == i + column)
        				this.self[i].down = false;
        			else
        				;
            		flag = true;
            		count++;
        		}       			       			
        	}      	
        }
    }

    public void print()
    {
        System.out.print("  ");

        for(int i = 1; i < this.column; i++)
        {
            System.out.print(" _");
        }
        System.out.println();

        for(int i = 0; i < this.length - 1; i++)
        {
            if(i % this.column == 0)
            {
            	if(i == 0)
            		System.out.print(" ");
            	else
            		System.out.print("|");
            }            
            
            this.self[i].print();
            
            if(i % this.column == this.column - 1)
            {
                System.out.println();
            }
        }
    }
    
}
