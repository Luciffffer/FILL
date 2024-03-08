package be.kdg.fill.models.core;

import org.json.simple.JSONArray;

public class Game {
    private Level level;
    private JSONArray state;

    public Game(Level level) {
        this.level = level;
        this.state = level.getPattern();
    }
}
