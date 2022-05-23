package dialogue;

import main.SimpleRPG;
import entity.Character;

public class Dialogue {
    private SimpleRPG master;

    private String inputDialogue;

    private Character character;


    public String getInputDialogue() {
        return this.inputDialogue;
    }

    public Character getCharacter() {
        return this.character;
    }

    public String getName() {
        return this.character.getName();
    }

    public String getImagePath() {
        return this.character.getImagePath() +  this.character.DEFAULT_IMAGE_PATH + "1.png";
    }

    public Dialogue(String inputDialogue, Character character) {
        this.inputDialogue = inputDialogue;
        this.character = character;
    }

    public Dialogue(String inputDialogue) {
        this.inputDialogue = inputDialogue;
    }

//    public String getName() {
//        return name;
//    }
//
//    public List<String> getOptionList() {
//        return optionList;
//    }
//
//    public String getPotraitPath() {
//        return potraitPath;
    }

//    public Dialogue(String inputDialogue, String name, String potraitPath) {
//        this.inputDialogue = inputDialogue;
//        this.name = name;
//        this.potraitPath = potraitPath;
//    }



