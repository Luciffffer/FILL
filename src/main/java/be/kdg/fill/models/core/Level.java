package be.kdg.fill.models.core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Level implements Comparable<Level> {

    private int id;
    private int[][] pattern;
    private int[] startPos;
    private World world;

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

    public Level(int id, int[][] pattern, int[] startPos) {
        this.id = id;
        this.pattern = pattern;
        this.startPos = startPos;
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
    public int[] getStartPos() {
        return startPos;
    }


    // SETTERS

    /**
     * setWorld
     * sets the world of the level.
     * @param world
     */
    public void setWorld(World world)
    {
        this.world = world;
    }

    // METHODS

    @Override
    public int compareTo(Level o)
    {
        return Integer.compare(this.id, o.id);
    }
}
