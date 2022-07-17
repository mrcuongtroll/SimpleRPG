package saveload;

import entity.*;
import entity.Character;
import entity.item.equiment.Armor;
import entity.item.equiment.Weapon;
import main.SimpleRPG;
import views.GameView;
import world.World;
import world.WorldHome;
import world.WorldOutside;
import world.WorldOutside2;

import java.io.*;
import java.util.ArrayList;

public class SaveLoad {

    public static void saveState(SimpleRPG master) {
        String pathPlayer = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "player.txt";

        String pathWorld = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "world.txt";

        ArrayList<CharacterSave> playerAllyList = new ArrayList<>();
        for (Character character: master.getPlayer().getAllyList()) {
            playerAllyList.add(new CharacterSave(character.getName(), "ally", (int) character.getX(), (int) character.getY(),
                    character.getLevel(), character.getExp(), character.getAttackSpeed(), character.getAttackPoint(), character.getDefensePoint(),
                    character.getManaPoint(), character.getHealthPoint(), character.getMaxHealthPoint(), character.getMaxManaPoint(), character.getImagePath(), null));
        }

        CharacterSave player = new CharacterSave(master.getPlayer().getName(), "player", (int) master.getPlayer().getX(), (int) master.getPlayer().getY(),
                master.getPlayer().getLevel(), master.getPlayer().getExp(), master.getPlayer().getAttackSpeed(),
                master.getPlayer().getAttackPoint(), master.getPlayer().getDefensePoint(), master.getPlayer().getManaPoint(),
                master.getPlayer().getHealthPoint(), master.getPlayer().getMaxHealthPoint(), master.getPlayer().getMaxManaPoint(),
                master.getPlayer().getImagePath(), playerAllyList);

        WorldSave world = null;
        if (master.getWorld() instanceof WorldHome worldHome) {
            world = new WorldSave(worldHome.getDy(), worldHome.getDx(), worldHome.getX(), worldHome.getY(),
                    "home",
                    worldHome.getBgImagePath(), worldHome.getOverlayImagePath(),
                    worldHome.getShadingImagePath(), worldHome.getMaskPath(),
                    WorldHome.tutorialViewed, WorldHome.doorUnlocked, WorldHome.keyObtained,
                    WorldOutside.ally1Recruited, WorldOutside.enemy1Defeated, WorldOutside.enemy2Defeated, WorldOutside.enemy3Defeated,
                    WorldOutside2.boss1Defeated, WorldOutside2.boss2Defeated);
        } else if (master.getWorld() instanceof WorldOutside worldOutside) {
            world = new WorldSave(worldOutside.getDy(), worldOutside.getDx(), worldOutside.getX(), worldOutside.getY(),
                    "outside",
                    worldOutside.getBgImagePath(), worldOutside.getOverlayImagePath(),
                    worldOutside.getShadingImagePath(), worldOutside.getMaskPath(),
                    WorldHome.tutorialViewed, WorldHome.doorUnlocked, WorldHome.keyObtained,
                    WorldOutside.ally1Recruited, WorldOutside.enemy1Defeated, WorldOutside.enemy2Defeated, WorldOutside.enemy3Defeated,
                    WorldOutside2.boss1Defeated, WorldOutside2.boss2Defeated);
        } else if (master.getWorld() instanceof WorldOutside2 worldOutside) {
            world = new WorldSave(worldOutside.getDy(), worldOutside.getDx(), worldOutside.getX(), worldOutside.getY(),
                    "outside2",
                    worldOutside.getBgImagePath(), worldOutside.getOverlayImagePath(),
                    worldOutside.getShadingImagePath(), worldOutside.getMaskPath(),
                    WorldHome.tutorialViewed, WorldHome.doorUnlocked, WorldHome.keyObtained,
                    WorldOutside.ally1Recruited, WorldOutside.enemy1Defeated, WorldOutside.enemy2Defeated, WorldOutside.enemy3Defeated,
                    WorldOutside2.boss1Defeated, WorldOutside2.boss2Defeated);
        }
        try (FileOutputStream f = new FileOutputStream(new File(pathPlayer))) {
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Write objects to file
            o.writeObject(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileOutputStream f = new FileOutputStream(new File(pathWorld))) {
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Write objects to file
            o.writeObject(world);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveStateBeforeBattle(SimpleRPG master) {
        String pathPlayer = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "playerBeforeBattle.txt";

        String pathWorld = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "worldBeforeBattle.txt";

        ArrayList<CharacterSave> playerAllyList = new ArrayList<>();
        for (Character character: master.getPlayer().getAllyList()) {
            playerAllyList.add(new CharacterSave(character.getName(), "ally", (int) character.getX(), (int) character.getY(),
                    character.getLevel(), character.getExp(), character.getAttackSpeed(), character.getAttackPoint(), character.getDefensePoint(),
                    character.getManaPoint(), character.getHealthPoint(), character.getMaxHealthPoint(), character.getMaxManaPoint(), character.getImagePath(), null));
        }

        CharacterSave player = new CharacterSave(master.getPlayer().getName(), "player", (int) master.getPlayer().getX(), (int) master.getPlayer().getY(),
                master.getPlayer().getLevel(), master.getPlayer().getExp(), master.getPlayer().getAttackSpeed(),
                master.getPlayer().getAttackPoint(), master.getPlayer().getDefensePoint(), master.getPlayer().getManaPoint(),
                master.getPlayer().getHealthPoint(), master.getPlayer().getMaxHealthPoint(), master.getPlayer().getMaxManaPoint(),
                master.getPlayer().getImagePath(), playerAllyList);

        WorldSave world = null;
        if (master.getWorld() instanceof WorldHome worldHome) {
            world = new WorldSave(worldHome.getDy(), worldHome.getDx(), worldHome.getX(), worldHome.getY(),
                    "home",
                    worldHome.getBgImagePath(), worldHome.getOverlayImagePath(),
                    worldHome.getShadingImagePath(), worldHome.getMaskPath(),
                    WorldHome.tutorialViewed, WorldHome.doorUnlocked, WorldHome.keyObtained,
                    WorldOutside.ally1Recruited, WorldOutside.enemy1Defeated, WorldOutside.enemy2Defeated, WorldOutside.enemy3Defeated,
                    WorldOutside2.boss1Defeated, WorldOutside2.boss2Defeated);
        } else if (master.getWorld() instanceof WorldOutside worldOutside) {
            world = new WorldSave(worldOutside.getDy(), worldOutside.getDx(), worldOutside.getX(), worldOutside.getY(),
                    "outside",
                    worldOutside.getBgImagePath(), worldOutside.getOverlayImagePath(),
                    worldOutside.getShadingImagePath(), worldOutside.getMaskPath(),
                    WorldHome.tutorialViewed, WorldHome.doorUnlocked, WorldHome.keyObtained,
                    WorldOutside.ally1Recruited, WorldOutside.enemy1Defeated, WorldOutside.enemy2Defeated, WorldOutside.enemy3Defeated,
                    WorldOutside2.boss1Defeated, WorldOutside2.boss2Defeated);
        } else if (master.getWorld() instanceof WorldOutside2 worldOutside) {
            world = new WorldSave(worldOutside.getDy(), worldOutside.getDx(), worldOutside.getX(), worldOutside.getY(),
                    "outside2",
                    worldOutside.getBgImagePath(), worldOutside.getOverlayImagePath(),
                    worldOutside.getShadingImagePath(), worldOutside.getMaskPath(),
                    WorldHome.tutorialViewed, WorldHome.doorUnlocked, WorldHome.keyObtained,
                    WorldOutside.ally1Recruited, WorldOutside.enemy1Defeated, WorldOutside.enemy2Defeated, WorldOutside.enemy3Defeated,
                    WorldOutside2.boss1Defeated, WorldOutside2.boss2Defeated);
        }
        try (FileOutputStream f = new FileOutputStream(new File(pathPlayer))) {
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Write objects to file
            o.writeObject(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileOutputStream f = new FileOutputStream(new File(pathWorld))) {
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Write objects to file
            o.writeObject(world);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Player loadNewPlayer(SimpleRPG master) {
        //return default if cant find save file
        return new Player(master, 570, 230, "Player",
                "/assets/test/player", 1, 6,
                25, 10, 80, 100, 100, 100,
                new Weapon("Wooden sword",
                        0, 25,0,0,0,
                        "example_armor.png"),
                new Armor("Wooden armor",
                        0, 0, 5,0,0,
                        "example_armor.png"));
    }

    public static Player loadPlayer(SimpleRPG master) {
        String path = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "player.txt";

        try(FileInputStream fi = new FileInputStream(path)) {
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            CharacterSave loadedPlayer = (CharacterSave) oi.readObject();
            System.out.println(loadedPlayer.getName());
            Player player = new Player(master, (int) loadedPlayer.getX(), (int) loadedPlayer.getY(), loadedPlayer.getName(), loadedPlayer.getImagePath(),
                    loadedPlayer.getLevel(), loadedPlayer.getExp(), loadedPlayer.getAttackSpeed(), loadedPlayer.getAttackPoint(), loadedPlayer.getDefensePoint(),
                    loadedPlayer.getHealthPoint(), loadedPlayer.getManaPoint(), loadedPlayer.getMaxHealthPoint(),
                    loadedPlayer.getMaxManaPoint(),
                    new Weapon("Wooden sword",
                            0, 25,0,0,0,
                            "example_armor.png"),
                    new Armor("Wooden armor",
                            0, 0, 5,0,0,
                            "example_armor.png"));
            for (CharacterSave characterSave: loadedPlayer.getAllyList()) {
                player.getAllyList().add(new Ally((World) master.getWorld(), master, (int) characterSave.getX(), (int) characterSave.getY(),
                        (int) (characterSave.getX() + ((World) master.getWorld()).getX()), (int) (characterSave.getY() + ((World) master.getWorld()).getY()),
                        characterSave.getName(), characterSave.getImagePath(), characterSave.getLevel(), characterSave.getExp(), characterSave.getAttackSpeed(),
                        characterSave.getAttackPoint(), characterSave.getDefensePoint(), characterSave.getHealthPoint(),
                        characterSave.getManaPoint(), characterSave.getMaxHealthPoint(), characterSave.getMaxManaPoint()
                ));
            }
//            new GameView(master);
            return player;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //return default if cant find save file
        return new Player(master, 570, 230, "Player",
                "/assets/test/player", 1, 6,
                25, 10, 80, 100, 100, 100,
                new Weapon("Wooden sword",
                        0, 25,0,0,0,
                        "example_armor.png"),
                new Armor("Wooden armor",
                        0, 0, 5,0,0,
                        "example_armor.png"));
    }

    public static boolean isPlayerSaveFileExist() {
        String path = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "player.txt";

        try(FileInputStream fi = new FileInputStream(path)) {
            ObjectInputStream oi = new ObjectInputStream(fi);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isWorldSaveFileExist() {
        String path = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "world.txt";

        try(FileInputStream fi = new FileInputStream(path)) {
            ObjectInputStream oi = new ObjectInputStream(fi);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Player loadPlayerAfterBattle(SimpleRPG master) {
        String path = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "playerBeforeBattle.txt";

        try(FileInputStream fi = new FileInputStream(path)) {
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            CharacterSave loadedPlayer = (CharacterSave) oi.readObject();
            System.out.println(loadedPlayer.getName());
            Player player = new Player(master, (int) loadedPlayer.getX(), (int) loadedPlayer.getY(), loadedPlayer.getName(), loadedPlayer.getImagePath(),
                    loadedPlayer.getLevel(), loadedPlayer.getExp(), loadedPlayer.getAttackSpeed(), loadedPlayer.getAttackPoint(), loadedPlayer.getDefensePoint(),
                    loadedPlayer.getHealthPoint(), loadedPlayer.getManaPoint(), loadedPlayer.getMaxHealthPoint(),
                    loadedPlayer.getMaxManaPoint(),
                    new Weapon("Wooden sword",
                            0, 25,0,0,0,
                            "example_armor.png"),
                    new Armor("Wooden armor",
                            0, 0, 5,0,0,
                            "example_armor.png"));
            for (CharacterSave characterSave: loadedPlayer.getAllyList()) {
                player.getAllyList().add(new Ally(master, (int) characterSave.getX(), (int) characterSave.getY(),
                        (int) (characterSave.getX() + 0), (int) (characterSave.getY() + 0),
                        characterSave.getName(), characterSave.getImagePath(), characterSave.getLevel(), characterSave.getExp(), characterSave.getAttackSpeed(),
                        characterSave.getAttackPoint(), characterSave.getDefensePoint(), characterSave.getHealthPoint(),
                        characterSave.getManaPoint(), characterSave.getMaxHealthPoint(), characterSave.getMaxManaPoint()
                ));
            }
//            new GameView(master);
            return player;
        } catch (Exception e) {
            e.printStackTrace();
            return new Player(master, 570, 230, "Player",
                    "/assets/test/player", 1, 6,
                    25, 10, 80, 100, 100, 100,
                    new Weapon("Wooden sword",
                            0, 25,0,0,0,
                            "example_armor.png"),
                    new Armor("Wooden armor",
                            0, 0, 5,0,0,
                            "example_armor.png"));
        }
        //return default if cant find save file

    }

    public static World loadNewWorld(SimpleRPG master) {
        //return default if cant find save file
        WorldHome.keyObtained = false;
        WorldHome.doorUnlocked = false;
        WorldHome.tutorialViewed = false;
        WorldOutside.enemy3Defeated = false;
        WorldOutside.enemy2Defeated = false;
        WorldOutside.enemy1Defeated = false;
        WorldOutside.ally1Recruited = false;
        WorldOutside.ally2Recruited = false;
        WorldOutside2.boss2Defeated = false;
        WorldOutside2.boss1Defeated = false;
        return new WorldHome(master, master.getPlayer().getX(), master.getPlayer().getY());
    }

    public static World loadWorld(SimpleRPG master) {
        String path = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "world.txt";

        try(FileInputStream fi = new FileInputStream(path)) {
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            WorldSave loadedWorld = (WorldSave) oi.readObject();
            World world = null;
            WorldHome.tutorialViewed = loadedWorld.isTutorialViewed();
            WorldHome.doorUnlocked = loadedWorld.isDoorUnlocked();
            WorldHome.keyObtained = loadedWorld.isKeyObtained();
            WorldOutside.ally1Recruited = loadedWorld.isAlly1Recruited();
            WorldOutside.enemy1Defeated = loadedWorld.isEnemy1Defeated();
            WorldOutside.enemy2Defeated = loadedWorld.isEnemy2Defeated();
            WorldOutside.enemy3Defeated = loadedWorld.isEnemy3Defeated();
            WorldOutside2.boss1Defeated = loadedWorld.isBoss1Defeated();
            WorldOutside2.boss2Defeated = loadedWorld.isBoss2Defeated();
            if (loadedWorld.getWorldType().equals("home")) {
                world = new WorldHome(master, master.getPlayer().getX(), master.getPlayer().getY());
            } else if (loadedWorld.getWorldType().equals("outside")){
                world = new WorldOutside(master, master.getPlayer().getX(), master.getPlayer().getY());
            } else if (loadedWorld.getWorldType().equals("outside2")){
                world = new WorldOutside2(master, master.getPlayer().getX(), master.getPlayer().getY());
            }

            return world;
        } catch (Exception e) {
//            e.printStackTrace();

        }
        //return default if cant find save file
        return new WorldHome(master, master.getPlayer().getX(), master.getPlayer().getY());
    }

    public static World loadWorldAfterBattle(SimpleRPG master) {
        String path = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "worldBeforeBattle.txt";

        try(FileInputStream fi = new FileInputStream(path)) {
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            WorldSave loadedWorld = (WorldSave) oi.readObject();
            World world = null;
            WorldHome.tutorialViewed = loadedWorld.isTutorialViewed();
            WorldHome.doorUnlocked = loadedWorld.isDoorUnlocked();
            WorldHome.keyObtained = loadedWorld.isKeyObtained();
            if (loadedWorld.getWorldType().equals("home")) {
                world = new WorldHome(master, master.getPlayer().getX(), master.getPlayer().getY());
            } else if (loadedWorld.getWorldType().equals("outside")){
                world = new WorldOutside(master, master.getPlayer().getX(), master.getPlayer().getY());
            } else if (loadedWorld.getWorldType().equals("outside2")){
                world = new WorldOutside2(master, master.getPlayer().getX(), master.getPlayer().getY());
            }

            return world;
        } catch (Exception e) {
//            e.printStackTrace();

        }
        //return default if cant find save file
        return new WorldHome(master, master.getPlayer().getX(), master.getPlayer().getY());
    }
}
