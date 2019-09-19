

public class Spell
{
   private String spellDetails, name;
   
   public Spell(String spellDetails, String name)
   {
      this.spellDetails = spellDetails;
      this.name = name;
   }
   
   /*Spells are added only if the player's stat is past the required threshold 
     the spell requires to learn it. e.g. "Cold Touch" requires an int of 8.*/
   public Spell addSpell(String type, int value)
   {
      if(type == "INT")
      {
         if(value >= 8)
            return new Spell("i-2", "Cold Touch");
      }
      return new Spell("", "No Spell");
   }
   
   /*Since a spell's damage changes depending on Player stats, we must calculate
     the spel's damage based on the player's current stats.*/
   public int determineDamage(Spell cast, Hero player)
   {
      return 0;
   }
}