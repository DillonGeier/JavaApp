import java.util.*;

public class Main
{
   public static void main(String [] args)
   {
      int gen, homeMenu;
      homeMenu = 0;
      Scanner kb = new Scanner(System.in);
      //initial character creation. happens once per game
      System.out.println("Welcome to placehold.");
      Hero you = new Hero();
      System.out.println(you.toString());
      do
      {  //main camp menu. always return here until quitting
         System.out.println("You are at the camp.\n1. Venture out.\n2. Examine character.\n3. Manage Inventory.\n4. Rest.\n5. Quit Game.");
         homeMenu = guaranteeInt();
         if(homeMenu == 1)
         {
            int destination = 0;
            System.out.println("Where will you go?\n1.Forest\n2.Shop");
            destination = guaranteeInt();
            if(destination == 1)
               goForest(you);
            else
               goShop(you);
         }
         if(homeMenu == 2)
            System.out.println(you.toString());
         if(homeMenu == 3)
         {
            manageInv(you);
         }
         if(homeMenu == 4)
         {
            System.out.println("You rest for the night.");
            you.setHp(you.calcHp());
            System.out.println("Your hp is fully restored to " + you.getHp());
         }
         else if(homeMenu<1 || homeMenu >5)
            System.out.println("Whatever you just decided to do, it was pointless and you accomplished nothing.");
      }while(homeMenu != 5);
   }//end main
   
   private static Hero goForest(Hero you)
   {
      int action = (int)Math.floor((Math.random()*10)+1);
      if(action >= 1 && action <=7)
      {
         System.out.println("You encounter enemy goblin!");
         Enemy bad = new Enemy(0);
         charCreation.battle(you, bad);
      }
      if(action >7 && action <= 9)
      {
         System.out.println("You are ambushed by Ziz the Mighty!");
         Enemy bad = new Enemy(1);
         charCreation.battle(you, bad);
      }
      if(action > 9 && action <=10)
      {
         System.out.println("You get lost.");
      }
      return you;
   }//end goForest
   
   private static Hero goShop(Hero you)
   {
      int action = -1;
      ArrayList<Item> tempInv = you.getInv();
      System.out.println("A seedy looking individual stands before you.\n 'Welcome good Master, what can I interest you in?'");
      do
      {
         System.out.println("1. Buy.");
         System.out.println("2. Sell.");
         System.out.println("3. Leave.");
         action = guaranteeInt();
         if(action == 1)
         {
            int buyConfirm = -1;
            System.out.println("'Here are my wares.'");
            System.out.println("1.Bread (5 coins)\n2.Leather Jerkin (35 coins)\n3.Shortsword (35 Coins)\n4.Exit");
            while(buyConfirm != 4)
            {
               buyConfirm = guaranteeInt();
               if(buyConfirm>4)
                  System.out.println("'Regretfully good master I do not stock Rocky Mountain Oysters.'");
               if(buyConfirm == 1)
               {
                  if(charCreation.invFull(tempInv) == true)
                     System.out.println("Your inventory is full!");
                  else if(you.getCoins() < 5)
                     System.out.println("'You're too poor to afford that.'");
                  else
                  { 
                     Consumable bread = new Consumable(1);
                     System.out.println("Bread get!");
                     charCreation.fillSlot(tempInv, bread);
                     you.setCoins(you.getCoins() - 5);
                  }
               }
               if(buyConfirm == 2)
               {
                  if(charCreation.invFull(tempInv) == true)
                     System.out.println("Your inventory is full!");
                  else if(you.getCoins() < 35)
                     System.out.println("'You're too poor to afford that.'");
                  else
                  { 
                     Equipable ss = new Equipable(3);
                     System.out.println(ss.getName()+" get!");
                     charCreation.fillSlot(tempInv, ss);
                     you.setCoins(you.getCoins() - 35);
                  }
               }
               if(buyConfirm == 3)
               {
                  if(charCreation.invFull(tempInv) == true)
                     System.out.println("Your inventory is full!");
                  else if(you.getCoins() < 35)
                     System.out.println("'You're too poor to afford that.'");
                  else
                  { 
                     Equipable ss = new Equipable(4);
                     System.out.println(ss.getName()+" get!");
                     charCreation.fillSlot(tempInv, ss);
                     you.setCoins(you.getCoins() - 35);
                  }
               }
            }
         }
         if(action == 2)
         {
            int toSell = -1;
            System.out.println("What item will you sell?");
            System.out.println(you.invList(tempInv, 1));
            toSell = guaranteeInt();
            if(charCreation.emptCheck(toSell))
               you.setCoins(you.getCoins() + tempInv.get(toSell-1).getSell());
            charCreation.emptSlot(tempInv, toSell, 1);
         }
         if(action == 3)
         {
            System.out.println("'Farewell good Master! I hope to do buisness again soon!'");
         }
         if(action >3 || action <1)
            System.out.println("The shopkeeper examines you quizzacly while you perform\nsome unknowable and foolish action.");
      }while(action != 3);
      return you;
   }//end goShop
   
   /*This function covers inventory management
   Includes: Consume, Equip, Delete*/
   public static Hero manageInv(Hero you)
   {
      ArrayList<Item> tempInv = you.getInv();
      int action = 0;
      int choice = -1;
      do
      {
         System.out.print(you.invList(you.getInv(), 1));
         System.out.print("What do you want to do?\n 1. Consume item 2. Equip weapon/armor 3. Delete item 4. Quit\n");
         action = guaranteeInt();
         if(action == 1)
         {
            System.out.println("Which item will you consume?");
            choice = guaranteeInt();
            if(tempInv.get(choice-1) instanceof Consumable)
            {
               Consumable eat = (Consumable) tempInv.get(choice-1);
               you.useConsumable(eat);
               charCreation.emptSlot(tempInv, choice, 0);
            }
            else
               System.out.println("Can't consume that.");
         }
         if(action == 2)
         {
            System.out.println("Which item will you equip?");
            choice = guaranteeInt();
            if(charCreation.emptCheck(choice))
            {
               if(tempInv.get(choice-1) instanceof Equipable)
               {
                  Equipable replace = you.changeGear((Equipable)tempInv.get(choice-1));
                  System.out.println("Equipped!");
                  tempInv.set(choice-1, replace);
               }
               else
                  System.out.println("Can't equip that.");
            }
            else
               System.out.println("Your bag isn't that big.");
         } 
         if(action == 3)
         {
            System.out.println("Which item will you throw away?");
            choice = guaranteeInt();
            charCreation.emptSlot(tempInv, choice, 2);
         } 
      }while(action != 4);
      return you;
   } 
   
   public static int guaranteeInt()    //guaranteeint is a standalone error checking method
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
   }//end method
}