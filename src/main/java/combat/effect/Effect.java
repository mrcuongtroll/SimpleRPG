package combat.effect;

import java.io.File;

public abstract class Effect {
     private String effectImagePath;
     private int numEffectFrame;
     private String name;
     protected Effect(String effectImagePath, String name, int numEffectFrame) {
          this.effectImagePath = effectImagePath;
          this.name = name;
          this.numEffectFrame = numEffectFrame;
     }
     public String getEffectImagePath(){
          return (new File(effectImagePath)).getAbsolutePath();
     }
     public int getNumEffectFrame() {
          return this.numEffectFrame;
     }
     public String getName() {
          return this.name;
     }
}
