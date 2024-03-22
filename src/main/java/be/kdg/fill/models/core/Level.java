package be.kdg.fill.models.core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Level implements Comparable<Level> {

    private int id;
    private int[][] pattern;
    private int[] startPos;
    private World world;

    // CONSTRUCTORS

    /**
     * Constructor for the Level class.
     * Uses a JSONObject to create a Level object.
     * @param JSONObject levelObject
     * @param World world
     */
    public Level(JSONObject levelObject, World world)
    {
        this.id = ((Long) levelObject.get("id")).intValue();
        this.pattern = new int[((JSONArray) levelObject.get("pattern")).size()][((JSONArray) ((JSONArray) levelObject.get("pattern")).get(0)).size()];
        for (int i = 0; i < ((JSONArray) levelObject.get("pattern")).size(); i++)
        {
            JSONArray row = (JSONArray) ((JSONArray) levelObject.get("pattern")).get(i);
            for (int j = 0; j < row.size(); j++)
            {
                this.pattern[i][j] = ((Long) row.get(j)).intValue();
            }
        }
        this.startPos = new int[] {((Long) ((JSONArray) levelObject.get("start")).get(0)).intValue(), ((Long) ((JSONArray) levelObject.get("start")).get(1)).intValue()};
        this.world = world;
    }

    /**
     * Constructor for the Level class.
     * Uses an id, pattern and start position to create a Level object.
     * @param int id
     * @param int[][] pattern
     * @param int[] startPos
     * @throws IllegalArgumentException
     */
    public Level(int id, int[][] pattern, int[] startPos) throws IllegalArgumentException
    {
        this.setId(id);
        this.setPattern(pattern);
        this.setStartPos(startPos);
    }


    // GETTERS

    /**
     * getId
     * gets the id of the level.
     * @return int
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * getPattern
     * gets the pattern of the level.
     * @return int[][]
     */
    public int[][] getPattern()
    {
        return this.pattern;
    }

    /**
     * getWorld
     * gets the world of the level.
     * @return World
     */
    public World getWorld()
    {
        return this.world;
    }

    /**
     * getStartPos
     * gets the start position of the level.
     * @return int[]
     */
    public int[] getStartPos() 
    {
        return startPos;
    }


    // SETTERS

    /**
     * setId
     * sets the id of the level.
     * @param int id
     * @return Level
     * @throws IllegalArgumentException
     */
    public Level setId(int id) throws IllegalArgumentException
    {
        if (id < 1) {
            throw new IllegalArgumentException("Id cannot be less than one.");
        }

        this.id = id;
        return this;
    }

    /**
     * setPattern
     * sets the pattern of the level.
     * @param int[][] pattern
     * @return Level
     * @throws IllegalArgumentException
     */
    public Level setPattern(int[][] pattern) throws IllegalArgumentException
    {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null.");
        }

        // check if all the columns have the same length
        int firstRowLength = pattern[0].length;
        for (int i = 1; i < pattern.length; i++) {
            if (pattern[i].length != firstRowLength) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }

        // check if at least 2 one's are present in the pattern next to each other
        boolean found = false;
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length - 1; j++) {
                // check right
                if (pattern[i][j] == 1 && pattern[i][j + 1] == 1) {
                    found = true;
                    break;
                }
                // check down
                if (i < pattern.length - 1 && pattern[i][j] == 1 && pattern[i + 1][j] == 1) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Pattern must contain at least 2 one's next to each other in order to be solveable.");
        }

        this.pattern = pattern;
        return this;
    }

    /**
     * setStartPos
     * sets the start position of the level.
     * @param int[] startPos
     * @return Level
     * @throws IllegalArgumentException
     */
    public Level setStartPos(int[] startPos) throws IllegalArgumentException
    {
        if (this.pattern == null) {
            throw new IllegalArgumentException("Pattern must be set before setting the start position.");
        } else if (startPos == null) {
            throw new IllegalArgumentException("Start position cannot be null.");
        } else if (startPos.length != 2) {
            throw new IllegalArgumentException("Start position must have 2 coordinates.");
        }

        // check if the start position is on a one in the pattern
        if (this.pattern[startPos[0]][startPos[1]] != 1) {
            throw new IllegalArgumentException("Start position must be on a one in the pattern.");
        }

        this.startPos = startPos;
        return this;
    }

    /**
     * setWorld
     * sets the world of the level.
     * @param World world
     * @return Level
     */
    public Level setWorld(World world)
    {
        this.world = world;
        return this;
    }

    // METHODS

    /**
     * compareTo
     * compares two levels based on their id.
     * @param Level level
     * @return int
     */
    @Override
    public int compareTo(Level level)
    {
        return Integer.compare(this.id, level.id);
    }
}
