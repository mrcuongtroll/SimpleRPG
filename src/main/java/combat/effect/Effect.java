package combat.effect;

public abstract class  Effect {
     private final String[] effectImagePaths;

     protected Effect(String[] effectImagePaths) {
          this.effectImagePaths = effectImagePaths;
     }
     public String[] getEffectImagePaths(){
          return effectImagePaths;
     }
}
