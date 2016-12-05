public class Square
{    
   private int side;
   Square(){
      side = 10;
   }
   Square(int parameter){
      side = parameter;
   }
   
   public int getArea()
   {
       return side*side;
   }
   public String toString()
   {
     return "Area is " + getArea();
   }

}