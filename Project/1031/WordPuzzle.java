import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;


public class WordPuzzle
{
    public static void main(String[] args)
    {
       Scanner sc = new Scanner(System.in);
       System.out.println("Please input a value for the rows and columns of the grid: ");
       int num = sc.nextInt();
       char a[][] = new char[10000][10000];

       MyLinkedList<String> lst = new MyLinkedList<String>( );
       AvlTree<String> t = new AvlTree<String>( );
       MyHashTable<String> mht = new MyHashTable<String>();

    //create a grid
    System.out.println("Created grid of random characters: ");
    for(int i = 0; i < num; i++)
       {
         for(int j = 0; j < num; j++)
         {
             a[i][j] = (char) (Math.random() * 26 + 'a');
          System.out.print(a[i][j] + " ");
         }
         System.out.print('\n');
       }

       //read dictionary
       try
       {
            FileReader fr = new FileReader("dictionary.txt");
            BufferedReader br = new BufferedReader(fr);

            String dicWord;
            while((dicWord = br.readLine()) != null)
            {
                //the dictionary list is kept in a linked list
                lst.add(dicWord);
                //the dictionary list is kept in a tree
                t.insert(dicWord);
                //the dictionary list is kept in a hash table
                mht.insert(dicWord);
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("File not found");
        }

       //read words form the grid and write them into a array list
       ArrayList<String> arrayList = new ArrayList<>();
       String newWord = new String();
       for(int i = 0; i < num; i++)
       {
           for(int j = 0; j < num; j++)
           {
               newWord = "";
               for(int m = j; m >= 0; m--)
               {
                   newWord = newWord + String.valueOf(a[i][m]);
                   arrayList.add(newWord);
               }

               newWord = String.valueOf(a[i][j]);
               for(int m = j + 1; m < num; m++)
               {
                   newWord = newWord + String.valueOf(a[i][m]);
                   arrayList.add(newWord);
               }

               newWord = String.valueOf(a[i][j]);
               for(int m = i - 1; m >= 0; m--)
               {
                   newWord = newWord + String.valueOf(a[m][j]);
                   arrayList.add(newWord);
               }

               newWord = String.valueOf(a[i][j]);
               for(int m = i + 1; m < num; m++)
               {
                   newWord = newWord + String.valueOf(a[m][j]);
                   arrayList.add(newWord);
               }

               newWord = String.valueOf(a[i][j]);
               for(int q = i - 1, r = j - 1; q >= 0 && r >= 0; q--, r--)
               {
                       newWord = newWord + String.valueOf(a[q][r]);
                       arrayList.add(newWord);

               }

               newWord = String.valueOf(a[i][j]);
               for(int q = i - 1, r = j + 1; q >= 0 && r < num; q--, r++)
               {
                   newWord = newWord + String.valueOf(a[q][r]);
                   arrayList.add(newWord);
               }

               newWord = String.valueOf(a[i][j]);
               for(int q = i + 1, r = j - 1; q < num && r >= 0; q++, r--)
               {
                   newWord = newWord + String.valueOf(a[q][r]);
                   arrayList.add(newWord);
               }

               newWord = String.valueOf(a[i][j]);
               for(int q = i + 1, r = j + 1; q < num && r < num; q++, r++)
               {
                   newWord = newWord + String.valueOf(a[q][r]);
                   arrayList.add(newWord);
               }

           }
       }

       //output the words
       System.out.println('\n' + "Words in this grid");
       for(int m = 0; m < arrayList.size(); m++)
       {
           System.out.println(arrayList.get(m));
       }

       System.out.println('\n' + "Words existed in the dictionary");
       //check if any of the words exist in the grid by searching linked list
       for(int m = 0; m < arrayList.size(); m++)
       {
         for (java.util.Iterator<String> itr = lst.iterator();itr.hasNext();)
         {
           if(itr.next().compareTo(arrayList.get(m)) == 0)
           {
             System.out.println("Searched word from linked list dictionary is: " + arrayList.get(m));
           }
         }
       }

       //check if any of the words exist in the grid by searching tree
       for(int m = 0; m < arrayList.size(); m++)
       {
           if(t.contains(arrayList.get(m)))
               System.out.println("Searched word from tree dictionary is: " + arrayList.get(m));
       }

       //check if any of the words exist in the grid by searching hash table
       for(int m = 0; m < arrayList.size(); m++)
       {
           if(mht.contains(arrayList.get(m)))
               System.out.println("Searched word from hash table dictionary is: " + arrayList.get(m));
       }
       System.out.println("END");
    }
}
