package combat.effect;

public abstract class  Effect {
     private String[] effectImagePath;
     private String name;
     protected Effect(String[] effectImagePath, String name) {
          this.effectImagePath = effectImagePath;
          this.name = name;
     }
     public String[] getEffectImagePath(){
          return effectImagePath;
     }
     public String getName() {
          return this.name;
     }
}
