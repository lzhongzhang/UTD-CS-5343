import java.util.Scanner;

public class Recursion2
{
   public static void main(String args[])
   {
      int arr[] = {0,1,2,3,4,5,6,7,8,9};
      for (int b = 0; b < arr.length; b++){
          System.out.print(arr[b]);
      }
      Scanner num = new Scanner(System.in);
      System.out.print("\nEnter a number: "); 
      int n = num.nextInt();
      int i = countOdd(arr, n);
      System.out.println("The count of odd numbers in the array is "+ i);
   }
   
   public static int countOdd(int []array, int n)
   {
      if (n < array.length){
         if (array[n] % 2 != 0)
            return 1 + countOdd(array, n+1);
         else
            return countOdd(array, n+1);
      }      
      else 
         return 0;          
   }
}