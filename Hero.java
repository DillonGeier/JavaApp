import java.util.*;
import java.math.*;

public class Hero extends Character //unique modifiers: race, inventory(Item), exp, stats, weapon & armor (Equipable), defense
{
   private String race;
   private int exp, defense;
   private ArrayList<Item> inventory = null;
   private Equipable wpn, armr;
   public int [] playerFlags = new int[20]; //used for saving characters, as well as tracking all global data
   //Base Info: level(0)-str(1)-agi(2)-con(3)-int(4)-coins(5)-arnor(6)-weapon(7)-class(8)-curse(9)-hp(10)-blank(11)-blank(12)-blank(13-17)
   //Spell Info (Flags): ColdTouch(18)-MightyChop(19)
   
   public Hero()  //Hero's have default stats. After initilization, we choose a name and race
   {
      super("Unknown", 1, 5, 5, 5, 5, 0);
      this.exp = 0;
      this.defense = 0;
      this.inventory = new ArrayList<Item>(10);
      this.initialCreation(0);
      this.initialStats();
      this.inventory = invNew(inventory);
      this.wpn = new Equipable(0);
      this.armr = new Equipable(0);
      this.changeGear(new Equipable(2));
      this.changeGear(new Equipable(1));
   }
   
   @Override
   public String toString()
   {
      String strn = "";
      strn += "You are " + super.getName();
      strn += " the " + race;
      strn += ". You are level " + super.getLevel() +" with " + super.getHp() + " hp and your stats are: " + super.getStr() + " STR " + super.getAgi() + " AGI " + super.getIntel() + " INT " + super.getCon() + " CON.";
      strn += "\nYou have " + exp + " out of 100 experience, and " + super.getCoins() + " coins.";
      strn += "\nYou are wielding " + this.wpn.getName() + " and are wearing " + this.armr.getName() + " which provides " + this.getDef() + " defense bonus.";
      return strn;
   }//end toString
   
   //recursively prints contents of inventory, requires a start point of 1
   public String invList(ArrayList<Item> list, int pos)
   {
      if(pos<list.size())
         return "" + pos + ":" + list.get(pos-1).getName() + " " + this.invList(list, pos+1);
   
      return "" + pos + ":" + list.get(pos-1).getName() + "\n";
   }
   
   //get methods
   public int getExp(){return this.exp;}
   public int getDef(){return this.defense;}
   public ArrayList<Item> getInv(){return this.inventory;}
   public Equipable getWpn(){return this.wpn;}
   public Equipable getArmr(){return this.armr;}
   
   //set methods
   public void setRace(String race){this.race = race;}
   public void setExp(final int exp){this.exp = exp;}
   public void setDef(final int defense){this.defense = defense;}
   public void setInv(final ArrayList<Item> inventory){this.inventory = inventory;}
   /*The following three methods work together to remove and add gear (equipables) to the players,
     making sure to change stats accordingly as they do so.*/
   private void equipGear(Equipable cur, int flag)
   {
      if(flag == 0)
         this.wpn = cur;
      else
         this.armr = cur;
      if(cur.getFlag() == 0)
         return;
      String [] stats = cur.getStatMod();
      int [] mods = cur.getModValue();
      for(int i = 0; stats[i] != null && i < stats.length; i++)
      {
         if(stats[i] == "STR")
            this.setStr(this.getStr() + mods[i]);
         if(stats[i] == "AGI")
            this.setAgi(this.getAgi() + mods[i]);
         if(stats[i] == "INT")
            this.setIntel(this.getIntel() + mods[i]);
         if(stats[i] == "CON")
         {
            this.setCon(this.getCon() + mods[i]);
            super.setHp(super.calcHp());
         }
      }
   }
   private void removeGear(int flag)
   {
      Equipable cur;
      if(flag == 0)
         cur = this.getWpn();
      else
         cur = this.getArmr();
      if(cur.getFlag() == 0)
         return;
      String [] stats = cur.getStatMod();
      int [] mods = cur.getModValue();
      for(int i = 0; stats[i] != null && i < stats.length; i++)
      {
         if(stats[i] == "STR")
            this.setStr(this.getStr() - mods[i]);
         if(stats[i] == "AGI")
            this.setAgi(this.getAgi() - mods[i]);
         if(stats[i] == "INT")
            this.setIntel(this.getIntel() - mods[i]);
         if(stats[i] == "CON")
         {
            this.setCon(this.getCon() - mods[i]);
            super.setHp(super.calcHp());
         }
      }
   }
   public Equipable changeGear(Equipable newItem)
   {
      Equipable unequip = new Equipable(0);
      if(newItem.getType() == "Weapon")
      {
         unequip = this.getWpn();
         this.removeGear(0);
         this.equipGear(newItem, 0);
      }
      else
      {
         unequip = this.getArmr();
         this.removeGear(1);
         this.equipGear(newItem, 1);
      }
      return unequip;
   }
   /*This function deals with using onsumable items. It uses the statMod and value
     of the cnsumable to determine exactly what to do.*/
   public void useConsumable(Consumable yum)
   {
      String type = yum.getMod();
      if(type == "HP")
      {
         this.setHp(this.getHp() + yum.getValue());
         if(this.getHp() > this.calcHp())
            this.setHp(this.calcHp());
      }
         
   }
   protected void initialCreation(int dummy) //we choose a name and race
   {
      Scanner kb = new Scanner(System.in);
      System.out.print("Enter your character's name: ");
      String nam = kb.nextLine();
      super.setName(nam);
      System.out.println("Choose your race.");
      int rac;
      do
      { 
         System.out.println("1. Elf (bonus AGI and INT)\n2. Human (bonus STR and AGI)\n3. Test");
         rac = Main.guaranteeInt();
         if(rac>3 || rac<1)
            System.out.println("You must enter a number between 1 and 2");
      }while(rac>3 || rac<1);
      if(rac == 1)
         this.race = "elf";
      else if(rac == 2)
         this.race = "human";
      else
         this.race = "test";
   }
   
   private void initialStats() //races get certain bonus stats which are assigned here
   {
      if(this.race.equals("human"))
      {
         super.setStr(super.getStr() + 2);
         super.setAgi(super.getAgi() + 2);
      }
      else if(this.race.equals("elf"))
      {
         super.setAgi(super.getAgi() + 2);
         super.setIntel(super.getIntel() + 2);
      }
      else
      {
         this.setStr(100);
         this.setCon(100);
         this.setAgi(100);
         this.setIntel(100);
         this.setCoins(10000);
      }
   }
   
   //Fills the inventory with "empty" dummy items.
   protected ArrayList<Item> invNew(ArrayList<Item> list)
   {
      for(int i =0; i<10; i++)
         list.add(i, new Consumable(0));
      return list;
   }
   
   /*When leveling up a character can increase their stats by 2*/
   public void levelUp()
   {  
      this.setLevel(this.getLevel() + 1);
      System.out.println("You have leveled up to level " + this.getLevel());
      int stats = 0;
      int choice = 0;
      Scanner kb = new Scanner(System.in);
      do
      {
         System.out.println("Choose a stat to increase.\n1. Strength (Increases base damage)\n2. Agility (Increases base critical strike chance and damage)\n3. Intellect (Increases base spell damage)\n4. Constitution (Grants bonus hp in addition to base hp)");
         choice = Main.guaranteeInt();
         while(choice <1 && choice > 4)
            choice = Main.guaranteeInt();
         if(choice == 1)
         {
            super.setStr(super.getStr() + 1);
            choice = 0;
            stats++;
         }
         if(choice == 2)
         {
            this.setAgi(this.getAgi() + 1);
            choice = 0;
            stats++;
         }
         if(choice == 3)
         {
            this.setIntel(this.getIntel() + 1);
            choice = 0;
            stats++;
         }
         if(choice == 4)
         {
            super.setCon(super.getCon() + 1);
            choice = 0;
            stats++;
         }
      }while(stats<2);
      this.setExp(0);
      super.setHp(super.calcHp());
   }
   
   public void setFlags(int setType)
   {
      this.playerFlags[0] = this.getLevel();
      this.playerFlags[1] = this.getStr();
      this.playerFlags[2] = this.getAgi();
      this.playerFlags[3] = this.getCon();
      this.playerFlags[4] = this.getIntel();
      this.playerFlags[5] = this.getCoins();
      this.playerFlags[6] = this.armr.getId();
      this.playerFlags[7] = this.wpn.getId();
      this.playerFlags[8] = 0;
      this.playerFlags[9] = 0;
      this.playerFlags[10]= this.getHp();
   }
   
   /*A critical strike is calculated. Requires that the random number generated between 1 and 100
   is less than the character's AGI. Damage is based on select character's AGI
   Formula = AGI*2 */
   public int critStrike()
   {
      int chance = (int)Math.floor((Math.random()*100));
      if(chance <= this.getAgi())
      {
         System.out.println("A critical hit!");
         return this.getAgi()*2;
      }
      else
         return 0;
   }
}