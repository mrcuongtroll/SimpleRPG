package combat.effect;

import java.io.File;

public abstract class Effect {
     public static final String SELF_APPLY_EFFECT = "SELF";
     public static final String OPPONENT_APPLY_EFFECT = "OPPONENT";
     private String effectImagePath;
     private int numEffectFrame;
     private String name;
     private String effectType;
     protected Effect(String effectImagePath, String name, int numEffectFrame, String effectType) {
          this.effectImagePath = effectImagePath;
          this.name = name;
          this.numEffectFrame = numEffectFrame;
          this.effectType = effectType;
     }
     public String getEffectImagePath(){
          return effectImagePath;
     }
     public int getNumEffectFrame() {
          return this.numEffectFrame;
     }
     public String getName() {
          return this.name;
     }

     public String getEffectType() {
          return effectType;
     }
}
