package be.kdg.fill.models.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import be.kdg.fill.FillApplication;
import be.kdg.fill.models.core.Level;
import be.kdg.fill.models.core.World;

public class WorldLoader {
    private LinkedList<World> worlds;
    private String directoryPath;
    private String fullDirectoryPath;


    // CONSTRUCTORS

    /**
     * WorldLoader
     * creates a new WorldLoader
     * @param directoryPath
     */
    public WorldLoader(String directoryPath) 
    {
        this.directoryPath = directoryPath;
        this.loadWorlds();
        this.sortWorlds();
    }


    // GETTERS

    /**
     * getWorlds
     * gets the worlds
     * @return LinkedList<World>
     */
    public LinkedList<World> getWorlds()
    {
        return worlds;
    }

    /**
     * getWorld
     * gets a world by name
     * @param name
     * @return World
     */
    public World getWorld(String name)
    {
        for (World world: worlds) {
            if (world.getName().equals(name)) {
                return world;
            }
        }

        return null;
    }


    // SETTERS

    /**
     * addWorld
     * adds a world
     * @param world
     */
    public void addWorld(World world)
    {
        int id = worlds.size() + 1;
        Short difficultyId = (short) (worlds.size() + 1);
        world.setId(worlds.size() + 1);
        world.setDifficultyId(difficultyId);
        world.setFileName("world-" + id + ".json");
        worlds.add(world);
    }


    // METHODS

    /**
     * loadWorlds
     * loads the worlds from the files
     */
    public void loadWorlds() throws RuntimeException
    {
        if (worlds != null) {
            worlds.clear();
        } else {
            worlds = new LinkedList<>();
        }

        JSONParser parser = new JSONParser();

        try {
            // load worlds from files

            File directory = new File(FillApplication.class.getResource("data/" + this.directoryPath).getFile());
            this.fullDirectoryPath = directory.getPath();

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
    public void sortWorlds()
    {
        Collections.sort(worlds);
    }

    /**
     * saveWorld
     * saves a world
     * @param worldId
     */
    public void saveWorld(int worldId)
    {
        for (World world: worlds) {
            if (world.getId() == worldId) {
                
                JSONObject worldObject = this.convertWorldToJson(world);

                String jsonString = JSONObject.toJSONString(worldObject);
                jsonString = jsonString.replace("\\/", "/");

                File directory = new File(this.fullDirectoryPath);
                File file = new File(directory, world.getFileName());

                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(jsonString);
                } catch (IOException e) {
                    
                    System.err.println(e);
                    ErrorLog error = new ErrorLog(e);
                    error.save();
                    throw new RuntimeException("Something went wrong saving the world, please check the error log.");

                }
            }
        }
    }

    /**
     * convertWorldToJson
     * converts a world to a json object
     * @param world
     * @return JSONObject
     */
    private JSONObject convertWorldToJson(World world)
    {
        JSONObject worldObject = new JSONObject();
        worldObject.put("id", world.getId());
        worldObject.put("name", world.getName());
        worldObject.put("imagePath", world.getImagePath());

        JSONObject difficultyObject = new JSONObject();
        difficultyObject.put("id", world.getDifficultyId());
        difficultyObject.put("name", world.getDifficultyName());

        worldObject.put("difficulty", difficultyObject);

        JSONArray levelsArray = new JSONArray();

        for (Level level: world.getLevels()) {
            JSONObject levelObject = new JSONObject();
            levelObject.put("id", level.getId());

            JSONArray patternArray = new JSONArray();

            for (int[] row: level.getPattern()) {
                JSONArray rowArray = new JSONArray();
                for (int value: row) {
                    rowArray.add(value);
                }
                patternArray.add(rowArray);
            }

            levelObject.put("pattern", patternArray);

            JSONArray startPosArray = new JSONArray();
            startPosArray.add(level.getStartPos()[0]);
            startPosArray.add(level.getStartPos()[1]);

            levelObject.put("start", startPosArray);
            levelsArray.add(levelObject);
        }

        worldObject.put("levels", levelsArray);

        return worldObject;
    }
}
