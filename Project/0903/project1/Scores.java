import java.util.Scanner;

public class Scores
{
   public static void main(String args[])
   {
      System.out.println("Please input the name and 5 quiz scores for each of 10 different people, \n(eg. Mary 99 98 97 96 95 John 90 95 96 93 90...) : ");
      Scanner sc = new Scanner(System.in);
      String name[] = new String[10];
      Double score[][] = new Double[10][5];
      double averageScore;
      for (int i = 0; i < 10; i++){
         name[i] = sc.next();
         averageScore = 0.0;
         for (int j = 0; j < 5; j++){
            score[i][j] = sc.nextDouble();
            averageScore += score[i][j];
         }
         averageScore = averageScore / 5;
         System.out.println(name[i]+"'s average score is " + averageScore);
      }
   }
}