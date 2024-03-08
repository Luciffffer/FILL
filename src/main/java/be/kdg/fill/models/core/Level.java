package be.kdg.fill.models.core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Level implements Comparable<Level> {
    
    private int id;
    private JSONArray pattern;
    private JSONArray startPos;
    
    public Level(JSONObject levelObject) 
    {
        this.id = ((Long) levelObject.get("id")).intValue();
        this.pattern = (JSONArray) levelObject.get("pattern");
        this.startPos = (JSONArray) levelObject.get("startPos");
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
     * @return JSONArray
     */
    public JSONArray getPattern()
    {
        return this.pattern;
    }


    // METHODS

    @Override
    public int compareTo(Level o) 
    {
        return Integer.compare(this.id, o.id);
    }

}
