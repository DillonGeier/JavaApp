/*Item class extends into consumable and stat item*/

public abstract class Item
{

   private String name, desc;
   private int emptFlag, sell;//flag determines whether or not the item in question exists, or is an "empty" item.
   
   public Item(String name, String desc, int sell)
   {
      this.name = name;
      this.desc = desc;
      this.emptFlag = 1;
      this.sell = sell;
   }
   
   public String getName(){return this.name;}
   public String getDesc(){return this.desc;}
   public int getFlag(){return this.emptFlag;}
   public int getSell(){return this.sell;}
   
   public void setName(String name){this.name = name;}
   public void setDesc(String desc){this.desc = desc;}
   public void setFlag(final int emptFlag){this.emptFlag = emptFlag;}
   public void setSell(final int sell){this.sell = sell;}
   
   public String toString()
   {
      return this.getName() + ": " + this.getDesc();
   }
}