import java.util.*;
import java.math.*;

public class Consumable extends Item
{
   private String statMod;
   private int value, cid; //Value is the how much the parameter specified by Stat is modified.
   
   public Consumable(int id)
   {
      super("Unk.", "Unk.", 0);
      this.cid = id;
      this.createConsumable(id);   
   }
   
   public int getValue(){return this.value;}
   public int getID(){return this.cid;}
   public String getMod(){return this.statMod;}
   
   public void setValue(final int value){this.value = value;}
   public void setID(final int id){this.cid = id;}
   public void setMod(final String statMod){this.statMod = statMod;}
   
   public void buildC(String name, String desc, String statMod, int sell, int value)
   {
      this.setName(name);
      this.setDesc(desc);
      this.setMod(statMod);
      this.setSell(sell);
      this.setValue(value);
   }
   public void createConsumable(int id)
   {
      if(id == 0)//This is a dummy value which is considered the "empty" item. Special Case.
      {
         this.setName("Empty");
         this.setDesc("");
         this.setValue(0);
         this.setFlag(0);
      }
      if(id == 1)
         this.buildC("Bread", "A loaf of bread. Restores 10 hp.","HP", 2, 10);
   }
}