package be.kdg.fill.models.core;

import java.util.Collections;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class World implements Comparable<World> {
    
    private int id;
    private String name;
    private String imagePath;
    private short difficultyId;
    private String difficultyName;
    private LinkedList<Level> levels;
    private String fileName;


    // CONSTRUCTORS

    /**
     * Constructor for the World class.
     * Uses a JSONObject to create a World object.
     * @param JSONObject worldObject
     * @param String fileName
     */
    public World(JSONObject worldObject, String fileName) 
    {
        this.id = ((Long) worldObject.get("id")).intValue();
        this.name = (String) worldObject.get("name");
        this.imagePath = (String) worldObject.get("imagePath");
        this.levels = new LinkedList<>();
        this.fileName = fileName;

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

    /**
     * Constructor for the World class.
     * Uses a name, image path and difficulty name to create a World object.
     * @param String name
     * @param String imagePath
     * @param String difficultyName
     * @throws IllegalArgumentException
     */
    public World(String name, String imagePath, String difficultyName) throws IllegalArgumentException
    {
        this.setName(name);
        this.setDifficultyName(difficultyName);
        this.imagePath = imagePath;
        this.levels = new LinkedList<>();
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
        return this.id;
    }

    /**
     * getDifficultyId
     * gets the difficulty id of the world.
     * @return short
     */
    public short getDifficultyId()
    {
        return this.difficultyId;
    }

    /**
     * getFileName
     * gets the file name of the world.
     * @return String
     */
    public String getFileName()
    {
        return this.fileName;
    }

    // SETTERS

    /**
     * setName
     * sets the name of the world.
     * @param String name
     * @return World
     * @throws IllegalArgumentException
     */
    public World setName(String name) throws IllegalArgumentException
    {
        this.validateName(name);
        this.name = name;
        return this;
    }

    /**
     * setDifficultyName
     * sets the difficulty name of the world.
     * @param String difficultyName
     * @return World
     * @throws IllegalArgumentException
     */
    public World setDifficultyName(String difficultyName) throws IllegalArgumentException
    {   
        this.validateName(difficultyName);
        this.difficultyName = difficultyName;
        return this;
    }

    /**
     * setId
     * sets the id of the world.
     * @param int id
     * @return World
     * @throws IllegalArgumentException
     */
    public World setId(int id) throws IllegalArgumentException
    {
        if (id < 1) {
            throw new IllegalArgumentException("Id cannot be less than one.");
        }

        this.id = id;
        return this;
    }

    /**
     * setDifficultyId
     * sets the difficulty id of the world.
     * @param short difficultyId
     * @return World
     * @throws IllegalArgumentException
     */
    public World setDifficultyId(short difficultyId) throws IllegalArgumentException
    {
        if (difficultyId < 1) {
            throw new IllegalArgumentException("Difficulty id cannot be less than one.");
        }

        this.difficultyId = difficultyId;
        return this;
    }

    /**
     * setFileName
     * sets the file name of the world.
     * @param String fileName
     * @return World
     */
    public World setFileName(String fileName)
    {
        this.fileName = fileName;
        return this;
    }


    // METHODS

    /**
     * validateName
     * validates the name of the world.
     * @param String name
     * @throws IllegalArgumentException
     */
    private void validateName(String name) throws IllegalArgumentException
    {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        } else if (name.length() > 15 || name.length() < 3) {
            throw new IllegalArgumentException("Name must be between 3 and 15 characters long.");
        }
    }

    /**
     * addLevel
     * adds a level to the world.
     * @param Level level
     */
    public void addLevel(Level level)
    {
        level.setWorld(this);
        levels.add(level);
    }

    /**
     * compareTo
     * compares the difficulty id of the world.
     * @param World world
     * @return int
     */
    @Override
    public int compareTo(World world) 
    {
        return this.difficultyId - world.difficultyId;
    }
}
