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

        CharacterSave player = new CharacterSave(master.getPlayer().getName(), "player", (int) master.getPlayer().getX(), (int) master.getPlayer().getY(),
                master.getPlayer().getLevel(), master.getPlayer().getExp(), master.getPlayer().getAttackSpeed(),
                master.getPlayer().getAttackPoint(), master.getPlayer().getDefensePoint(), master.getPlayer().getManaPoint(),
                master.getPlayer().getHealthPoint(), master.getPlayer().getMaxHealthPoint(), master.getPlayer().getMaxManaPoint(),
                master.getPlayer().getImagePath());

        WorldSave world = null;
        if (master.getWorld() instanceof WorldHome worldHome) {
            world = new WorldSave(worldHome.getDy(), worldHome.getDx(), worldHome.getX(), worldHome.getY(),
                    "home",
                    worldHome.getBgImagePath(), worldHome.getOverlayImagePath(),
                    worldHome.getShadingImagePath(), worldHome.getMaskPath(), worldHome.getNpcSaveList(),
                    WorldHome.tutorialViewed, WorldHome.doorUnlocked, WorldHome.keyObtained);
        } else if (master.getWorld() instanceof WorldOutside worldOutside) {
            world = new WorldSave(worldOutside.getDy(), worldOutside.getDx(), worldOutside.getX(), worldOutside.getY(),
                    "outside",
                    worldOutside.getBgImagePath(), worldOutside.getOverlayImagePath(),
                    worldOutside.getShadingImagePath(), worldOutside.getMaskPath(), worldOutside.getNpcSaveList(),
                    WorldHome.tutorialViewed, WorldHome.doorUnlocked, WorldHome.keyObtained);
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
                    loadedPlayer.getLevel(), loadedPlayer.getAttackSpeed(), loadedPlayer.getAttackPoint(), loadedPlayer.getDefensePoint(),
                    loadedPlayer.getHealthPoint(), loadedPlayer.getManaPoint(), loadedPlayer.getMaxHealthPoint(),
                    loadedPlayer.getMaxManaPoint(),
                    new Weapon("Wooden sword",
                            0, 25,0,0,0,
                            "example_armor.png"),
                    new Armor("Wooden armor",
                            0, 0, 5,0,0,
                            "example_armor.png"));
//            new GameView(master);
            return player;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //return default if cant find save file
        return new Player(master, 570, 230, "Player",
                (new File("./assets/test/player")).getAbsolutePath(), 1, 6,
                25, 10, 80, 100, 100, 100,
                new Weapon("Wooden sword",
                        0, 25,0,0,0,
                        "example_armor.png"),
                new Armor("Wooden armor",
                        0, 0, 5,0,0,
                        "example_armor.png"));
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
            if (loadedWorld.getWorldType().equals("home")) {
                world = new WorldHome(master, master.getPlayer().getX(), master.getPlayer().getY());
                world.setDx(loadedWorld.getDx());
                world.setDy(loadedWorld.getDy());
                world.setX(loadedWorld.getX());
                world.setY(loadedWorld.getY());
            } else if (loadedWorld.getWorldType().equals("outside")){
                world = new WorldOutside(master, master.getPlayer().getX(), master.getPlayer().getY());
                world.setDx(loadedWorld.getDx());
                world.setDy(loadedWorld.getDy());
                world.setX(loadedWorld.getX());
                world.setY(loadedWorld.getY());
                ArrayList<NPC> npcList = new ArrayList<>();
                for (CharacterSave npc: loadedWorld.getNpcList()) {
                    if (npc.getType().equals("ally")) {
                        npcList.add(new Ally(world, master, (int) npc.getX(), (int) npc.getY(),
                                (int) (npc.getX() + world.getX()), (int) (npc.getY() + world.getY()),
                                npc.getName(), npc.getImagePath(), npc.getLevel(), npc.getAttackSpeed(),
                                npc.getAttackPoint(), npc.getDefensePoint(), npc.getHealthPoint(),
                                npc.getManaPoint(), npc.getMaxHealthPoint(), npc.getMaxManaPoint()
                        ));
                    } else if (npc.getType().equals("enemy")) {
                        npcList.add(new Enemy(world, master, (int) npc.getX(), (int) npc.getY(),
                                (int) (npc.getX() + world.getX()), (int) (npc.getY() + world.getY()),
                                npc.getName(), npc.getImagePath(), npc.getLevel(), npc.getAttackSpeed(),
                                npc.getAttackPoint(), npc.getDefensePoint(), npc.getHealthPoint(),
                                npc.getManaPoint(), npc.getMaxHealthPoint(), npc.getMaxManaPoint()
                        ));
                    }
                }
                ((WorldOutside) world).initiateNPCList(npcList);
            }

            return world;
        } catch (Exception e) {
//            e.printStackTrace();

        }
        //return default if cant find save file
        return new WorldHome(master, master.getPlayer().getX(), master.getPlayer().getY());
    }
}
