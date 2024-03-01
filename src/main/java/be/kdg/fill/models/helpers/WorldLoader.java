package be.kdg.fill.models.helpers;

import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import be.kdg.fill.FillApplication;
import be.kdg.fill.models.core.World;

public abstract class WorldLoader {
    private static LinkedList<World> worlds;

    /**
     * static constructor
     */
    static 
    {
        loadWorlds();
        sortWorlds();
    }


    // GETTERS

    /**
     * getWorlds
     * gets the worlds
     * @return LinkedList<World>
     */
    public static LinkedList<World> getWorlds()
    {
        return worlds;
    }

    /**
     * getWorld
     * gets a world by name
     * @param name
     * @return World
     */
    public static World getWorld(String name)
    {
        for (World world: worlds) {
            if (world.getName().equals(name)) {
                return world;
            }
        }

        return null;
    }


    // METHODS

    /**
     * loadWorlds
     * loads the worlds from the files
     */
    private static void loadWorlds() throws RuntimeException
    {
        if (worlds != null) {
            worlds.clear();
        } else {
            worlds = new LinkedList<>();
        }

        JSONParser parser = new JSONParser();

        try {
            // load worlds from files

            File directory = new File(FillApplication.class.getResource("data/worlds").getFile());
            File[] files = directory.listFiles();

            for (File file: files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    // load world from file
                    FileReader reader = new FileReader(file);

                    JSONObject json = (JSONObject) parser.parse(reader);

                    World world = new World(json, file.getPath());
                    worlds.add(world);
                }
            }

        } catch (Exception e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong loading the worlds, please check the error log.");

        }
    }

    /**
     * sortWorlds
     * sorts the worlds
     */
    public static void sortWorlds()
    {
        Collections.sort(worlds);
    }
}
