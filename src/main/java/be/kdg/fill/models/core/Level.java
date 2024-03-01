package be.kdg.fill.models.core;

import org.json.simple.JSONObject;

public class Level implements Comparable<Level> {
    
    private int id;
    
    public Level(JSONObject levelObject) 
    {
        this.id = ((Long) levelObject.get("id")).intValue();
    }


    // GETTERS

    /**
     * getId
     * gets the id of the level.
     * @return int
     */
    public int getId()
    {
        return id;
    }


    // METHODS

    @Override
    public int compareTo(Level o) 
    {
        return Integer.compare(this.id, o.id);
    }

}
