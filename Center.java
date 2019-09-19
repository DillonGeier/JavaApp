import java.util.*;

public class Center
{
   public static int guaranteeInt()
   {
      int x = 0;
      boolean boola = true;
      do
      {
         try
            {
               Scanner kb = new Scanner(System.in);
               x = kb.nextInt();
               boola = false;
            }
         catch(InputMismatchException e)
            {
               System.out.println("Numbers only.");
            }
      }while(boola);
      return x;
   }
}



/*the try/catch block lets you guarantee what type of input you receive, error checking without throwing
a runtime error. no need to end the program in other words
a loop is required to make it run more than once, do/while seems to be the best choice*/