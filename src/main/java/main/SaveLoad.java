package main;

import entity.Player;
import views.GameView;
import world.World;

import java.io.*;

public class SaveLoad {

    public static void saveState(SimpleRPG master) {
        String path = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "test.txt";

        try (FileOutputStream f = new FileOutputStream(new File(path))) {
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Write objects to file
            o.writeObject(master.getPlayer());
            o.writeObject(master.getWorld());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadState(SimpleRPG master) {
        String path = System.getProperty("user.home") + System.getProperty("file.separator")
                + "AppData" + System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
                + "test.txt";

        try(FileInputStream fi = new FileInputStream(new File(path))) {
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            master.setPlayer((Player) oi.readObject());
            master.setWorld((World) oi.readObject());
            new GameView(master);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
