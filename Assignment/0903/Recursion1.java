import java.util.Scanner;

public class Recursion1
{
   public static void print(int n)
   {
      System.out.print(n + " ");
      if (n > 0){
         print(n - 1);
         System.out.print(n + " ");
         return;   
      }
      else if (n == 0)
         return;
   }
   
   public static void main(String args[])
   {
      Scanner num = new Scanner(System.in);
      System.out.println("Enter a number: "); 
      int n = num.nextInt();
      print(n);
   }
}
   