import java.util.*;

public class MazeTest
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input two values for the rows and columns: ");
        int row = sc.nextInt();
        int column = sc.nextInt();
        Maze m = new Maze(row, column);
        m.print();
    }
}

