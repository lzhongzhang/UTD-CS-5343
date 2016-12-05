import java.util.Scanner;

public class EvaluateTemperature
{
   public static void main(String args[])
   {
      Scanner sc = new Scanner(System.in);
      System.out.println("Input an integer representing a temperature and a string \nof either C or F for Celsius or Fahrenheit(eg. 34 C or 55 F): ");
      Double t = sc.nextDouble();
      String s = sc.next();
      //System.out.println("input is " + t + s);
      Convert(t, s);
   }

   public static void Convert(Double t, String s)
   {
      String str = "C";
      if (s.equals(str)){
         t = 1.8*t + 32;
         System.out.println("New t is " + t);
         Compare(t);
      }
      else
         Compare(t);       
   }
   
   public static void Compare(Double t)
   {
      if (t > 100) 
         System.out.println("Very hot");
      else if (t > 90)
         System.out.println("Hot");
      else if (t > 70)
         System.out.println("Warm");
      else if (t > 50)
         System.out.println("Mild");
      else if (t > 32)
         System.out.println("Cold");
      else if (t >= 0)
         System.out.println("Very cold");
      else
         System.out.println("Extremely cold");
    }
} 