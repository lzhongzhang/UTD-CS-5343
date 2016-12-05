import java.util.Scanner;

public class TestSquare
{
    public static void main(String args[])
    {
      Scanner sc = new Scanner(System.in);
      Square s = new Square();
      System.out.println("Square1 with a fixed value 10 as a side length : " + s);
      // If there is no toString in Square.java, then 's' should be replaced by 's.getArea()'.
      System.out.println("Please intput a parameter value as the other side length: ");
      int sd = sc.nextInt();
      Square s1 = new Square(sd);
      System.out.println("Square2: " + s1);
      // If there is no toString in Square.java, then 's1' should be replaced by 's1.getArea()'.

    }
}