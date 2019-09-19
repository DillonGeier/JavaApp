
/*Explanation of the Equipable Class
  An equipable is a child class of the abstract parent class "Item".
  The string object "equiptype" tells the player and game if the equipable
  is a weapon or armor. A player can equip one of each.
  statMods[] holds the names of the stats which the item modifies (eg STR, INT, ARMOR, ect)
  modValues[] holds the correponding real modified value (eg 5, -3, 8, ect)
  A finished equipable may look like this:
  
  equipType = "Armor";
  statMods [] = {STR, HP, null}
  modValues [] = {4, 10, 0}*/
  
public class Equipable extends Item
{
   private String equipType;
   private String [] statMods;
   private int [] modValues;
   private int eid;
   
   public Equipable(int id)
   {
      super("Unk", "Unk", 0);
      this.eid = id;
      this.createEquipable(id);
   }
   
   public String getType(){return this.equipType;}
   public String [] getStatMod(){return this.statMods;}
   public int [] getModValue(){return this.modValues;}
   public int getId(){return this.eid;}
   
   public void setType(String equipType){this.equipType = equipType;}
   public void setStatMods(String [] statMods){this.statMods = statMods;}
   public void setModValues(int [] modValues){this.modValues = modValues;}
   
   //Takes care of super values
   private void buildE(String name, String desc, int sell, String equipType)
   {
      this.setName(name);
      this.setDesc(desc);
      this.setSell(sell);
      this.equipType = equipType;
   }
   
   //Fills the stat array with the names of stats modified by the Equipable
   private String [] buildStat(String first, String second, String third)
   {
      String [] toFill = new String[3];
      if(first == null)
      {
         toFill[0] = null;
         return toFill;
      }
      toFill[0] = first;
      toFill[1] = second;
      toFill[2] = third;
      return toFill;
   }
   
   //Fills the mod array with the value of stats modified by the Equipable
   private int [] buildMod(int first, int second, int third)
   {
      int [] toFill = new int[3];
      toFill[0] = first;
      toFill[1] = second;
      toFill[2] = third;
      return toFill;
   }
   
   private void createEquipable(int id)
   {
      if(id == 0)
      {
         this.buildE("Nothing", "", 0, "Nothing");
         this.setFlag(0);
      }
      this.statMods = new String[3];
      this.modValues = new int[3];
      if(id == 1)
      {
         this.buildE("Plain Clothes", "A simple set of cotton clothes.", 10, "Armor");
         this.statMods = this.buildStat("DEF", null, null);
         this.modValues = this.buildMod(1, 0, 0);
      }
      if(id == 2)
      {
         this.buildE("Worn Dagger", "A shoddy, blunt dagger. Looks pitiful. (STR +1)", 10, "Weapon");
         this.statMods = this.buildStat("STR", null, null);
         this.modValues = this.buildMod(1, 0, 0);
      }
      if(id == 3)
      {
         this.buildE("Leather Jerkin", "Hard, boiled leather armor. Sturdy.", 25, "Armor");
         this.statMods = this.buildStat("DEF", "CON", null);
         this.modValues = this.buildMod(2, 1, 0);
      }
      if(id == 4)
      {
         this.buildE("Shortsword", "A simple shortsword. (STR +2, AGI +1)", 25, "Weapon");
         this.statMods = this.buildStat("STR", "AGI", null);
         this.modValues = this.buildMod(2, 1, 0);
      }
   }
   
}