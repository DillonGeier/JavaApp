

public abstract class Character
{
   private String name;
   private int level, str, con, agi, intel, coins;
   protected int hp;
   
   public Character(String name, int level, int str, int agi, int con, int intel, int coins)
   {
      this.name = name;
      this.level = level;
      this.str = str;
      this.agi = agi;
      this.con = con;
      this.intel = intel;
      this.hp = calcHp();
   }
   
   public int getLevel(){return this.level;}
   public int getCon(){return this.con;}
   public int getStr(){return this.str;}
   public int getAgi(){return this.agi;}
   public int getIntel(){return this.intel;}
   public int getHp(){return this.hp;}
   public int getCoins(){return this.coins;}
   public String getName(){return this.name;}
   
   public void setName(String name){this.name = name;}
   public void setLevel(int level){this.level = level;}
   public void setStr(int str){this.str = str;}
   public void setCon(int con){this.con = con;}
   public void setAgi(final int agi){this.agi = agi;}
   public void setIntel(final int intel){this.intel = intel;}
   public void setCoins(final int coins){if(coins < 0){this.coins = 0;}else{this.coins = coins;}}
   public void setHp(int hp){this.hp = hp;}
   
   public int calcHp()  //HP = 10+5*level + (Con-5)*2
   {
      return (10+(5*this.getLevel())+((this.getCon()-5)*2));
   }
   
   public int calcEhp() //HP = 10 * level + Con
   {
      return (10*this.getLevel()+this.getCon());
   }
   
   protected abstract void initialCreation(int id);
}