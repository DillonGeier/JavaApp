import java.util.*;

public class Enemy extends Character  //Unique Modifiers: ID, loot
{
   private String dscrp;
   private int id;
   
   public Enemy(int id)
   {
      super("Unknown", 0, 0, 0, 0, 0, 0);
      this.initialCreation(id);
      super.hp = calcEhp();
   }//enemy EVC
   
   @Override
   public String toString()
   {
      String strn = "";
      strn += "It's " + super.getName() + "!\n";
      strn += "It appears to have " + super.getHp() +" hp.\n";
      //strn += "It is probably worth " + 5 + " experience.";
      return strn;
   }
   
   //buildE performs the actual building using the stats associated with each ID
   public void buildE(String name, int level, int str, int con, int agi, int intel, int coins)
   {
      this.setName(name);
      this.setLevel(level);
      this.setStr(str);
      this.setCon(con);
      this.setAgi(agi);
      this.setIntel(intel);
      this.setCoins(coins);
   }
   
   /*Every enemy upon creation is created with an ID number. This
   function will use this ID to create a monster's stats and name.*/
   public void initialCreation(int id)
   {
      if(id == 0)
      {
         this.buildE("a Goblin", 1, (int)Math.floor((Math.random()*3)+4), 4, 1, 1, (int)Math.floor((Math.random()*3)+2)); //4+(1-3) dmg, 2+(1-3) coins
      }
      if(id == 1)
      {
         this.buildE("Ziz the Mighty", 3, (int)Math.floor((Math.random()*4)+7), 10, 1, 1, (int)Math.floor((Math.random()*5)+8)); //7+(1-4) dmg, 8+(1-5) coins
      }
   }

}
