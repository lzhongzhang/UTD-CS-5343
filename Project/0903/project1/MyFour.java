import java.util.Scanner;

public class MyFour<T>
{
   private T item1, item2, item3, item4;
   
   MyFour(T a, T b, T c, T d){
      item1 = a;
      item2 = b;
      item3 = c;
      item4 = d;
   }
   
   public boolean allEqual(){
      return item1.equals(item2) && item2.equals(item3) && item3.equals(item4);
   }
   
   public void shiftLeft(){
      T temp;
      temp  = item1;
      item1 = item2;
      item2 = item3;
      item3 = item4;
      item4 = temp;
   }
   
   public String toString(){
      return "(" + item1 + "," + item2 + "," + item3 +"," + item4 +")";
   }
   
   public static void main(String args[]){
      Scanner sc = new Scanner(System.in);
      String str1 = sc.next();
      String str2 = sc.next();
      String str3 = sc.next();
      String str4 = sc.next();
      MyFour<String> test_str = new MyFour<>(str1, str2, str3, str4);
      System.out.println(test_str);
      if (test_str.allEqual()){
         System.out.println("TRUE");
      }
      else{ 
         System.out.println("FALSE");
         test_str.shiftLeft();
         System.out.println(test_str);
      }
   }
}