package be.kdg.fill.models.core;

import java.util.Collections;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import be.kdg.fill.FillApplication;

public class World implements Comparable<World> {
    private int id;
    private String name;
    private String imagePath;
    private short difficultyId;
    private String difficultyName;
    private LinkedList<Level> levels;
    private String filePath;


    // CONSTRUCTORS

    /**
     * Constructor for the World class.
     * @param worldObject
     * @param filePath
     */
    public World(JSONObject worldObject, String filePath) 
    {
        this.id = ((Long) worldObject.get("id")).intValue();
        this.name = (String) worldObject.get("name");
        this.imagePath = FillApplication.class.getResource((String) worldObject.get("imagePath")).toExternalForm();
        this.levels = new LinkedList<>();
        this.filePath = filePath;

        JSONObject difficulty = (JSONObject) worldObject.get("difficulty");
        this.difficultyId = ((Long) difficulty.get("id")).shortValue();
        this.difficultyName = (String) difficulty.get("name");

        JSONArray levelsArray = (JSONArray) worldObject.get("levels");
        for (Object levelObject: levelsArray) {
            JSONObject level = (JSONObject) levelObject;
            this.levels.add(new Level(level, this));
        }

        Collections.sort(this.levels);
    }


    // GETTERS

    /**
     * getName
     * gets the name of the world.
     * @return String
     */
    public String getName() 
    {
        return name;
    }

    /**
     * getImagePath
     * gets the image path of the world.
     * @return String
     */
    public String getImagePath() 
    {
        return imagePath;
    }

    /**
     * getDifficultyName
     * gets the difficulty name of the world.
     * @return String
     */
    public String getDifficultyName() 
    {
        return difficultyName;
    }

    /**
     * getLevelCount
     * gets the level count of the world.
     * @return int
     */
    public int getLevelCount() 
    {
        return (int) levels.size();
    }

    /**
     * getLevels
     * gets the levels of the world.
     * @return LinkedList<Level>
     */
    public LinkedList<Level> getLevels()
    {
        return levels;
    }

    /**
     * getId
     * gets the id of the world.
     * @return int
     */
    public int getId() 
    {
        return id;
    }


    // METHODS

    @Override
    public int compareTo(World world) 
    {
        return this.difficultyId - world.difficultyId;
    }
}
