import java.util.*;

public class charCreation
{
   /*The battle function enters a menu, takes an option, and continuously
   enters said menu until one fighter is dead or one flees*/
   public static void battle(Hero you, Enemy bad)     //battle takes a hero and enemy and enters a submenu
   {
      int choice;
      do
      {
         int pdmg = 0;
         String pause = null;
         Scanner kb = new Scanner(System.in);
         System.out.println("What will you do?");
         System.out.println("1. Attack");
         System.out.println("2. Examine " + bad.getName());
         System.out.println("3. Run");
         choice = Main.guaranteeInt();
         while(choice <=0 || choice >3)
            choice = Main.guaranteeInt();
         if(choice == 1)
         {
            pdmg = you.getStr() + you.critStrike();
            System.out.println("You attack, dealing " + pdmg + " damage.");
            bad.setHp((bad.getHp() - pdmg));
            if(bad.getHp() <= 0)
            {
               System.out.println("You have slain " + bad.getName() + "! You've earned " + bad.getCoins() + " coins.");
               you.setCoins(you.getCoins() + bad.getCoins());
               choice = 4;
               you.setExp(you.getExp()+(10*bad.getLevel()/you.getLevel()));
               if(you.getExp() >= 100)
                  you.levelUp();
            }
            pause = kb.nextLine();
            if(bad.getHp() > 0)
            {
               System.out.println(bad.getName() + " attacks!");
               you.setHp(you.getHp() - bad.getStr());
               System.out.println("You take " + bad.getStr() + " damage and have " + you.getHp() + " hp remaining.");
               if( you.getHp() <= 0)
               {
                  choice = 4;
                  System.out.println("You have been defeated!");
                  you.setHp(1);
               } 
            }
         }
         if(choice == 2)
            System.out.println(bad);
         if(choice == 3)
         {
            choice = (int)Math.floor((Math.random()*3)+you.getLevel()-bad.getLevel()); //1-3 + your level - enemy level (escape chance)
            System.out.println("You attempt to run away.");
            if(choice >= 3)
               System.out.println("Got away safely!");
            else
            {
               System.out.println("But your enemy intercepted you!");
               System.out.println(bad.getName() + " attacks!");
               int damage = (bad.getStr() - you.getDef());
               if(damage < 0)
                  damage = 0;
               you.setHp(you.getHp() - damage);
               System.out.println("You take " + damage + " damage and have " + you.getHp() + " hp remaining.");
               if( you.getHp() <= 0)
               {
                  choice = 4;
                  System.out.println("You have been defeated!");
                  you.setHp(1);
               }
            } 
         }
      }while(choice < 3);
   }//end battle
   
   //Empties an item slot for consumation or deletion
   public static ArrayList<Item> emptSlot(ArrayList<Item> list, int pos, int typeFlag)
   {
      if(emptCheck(pos) == false)
      {
         System.out.println("Your bag isn't that big. Shoulda bought that bag of holding after all.");
         return list;
      }
      if(list.get(pos-1).getFlag() == 0)
      {
         System.out.println("That item slot is empty.");
         return list;
      }
      else
      {
         Item removed = list.get(pos-1);
         if(typeFlag == 0)
         {
            System.out.println("You scarf down the " + removed.getName() + ".");
         }
         else if(typeFlag == 1)
         {
            System.out.println("'Thanks you for the business good master!'");
            System.out.println("Received " + list.get(pos-1).getSell() + " coins.");
         }
         else
         {
            System.out.println("You throw away the " + removed.getName() + ".");
         }
         list.remove(pos-1);
         list.add(pos-1, new Consumable(0));
         return list;
      }
   }
   
   //Removes an "empty" item from the inventory and replaces it with item.
   public static ArrayList<Item> fillSlot(ArrayList<Item> list, Item item)
   {
      for(int i = 0; i<list.size(); i++)
      {
         Item check = list.get(i);
         if(check.getFlag() == 0)
         {
            list.remove(i);
            list.add(i, item);
            return list;
         }
      }
      return list;
   }
   
   /*Checks inventory to see if any slots have a "empty" item in them
   If an empty slot is found the function returns false, ie
   the inventory is not full */
   public static boolean invFull(ArrayList<Item> list)
   {
      for(int i = 0; i<list.size(); i++)
      {
         Item check = list.get(i);
         if(check.getFlag() == 0)
            return false;
      }
      return true;
   }
   
   /*Error checking method for selling. Makes sure the player
   isn't entering a number outside the range of the inventroy. */
   public static boolean emptCheck(int pos)
   {
      if(pos>=1 && pos<=10)
         return true;
         
      return false;
   }
   
}