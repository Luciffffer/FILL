package be.kdg.fill.models.core;

import java.time.LocalDateTime;

import org.json.simple.JSONArray;

public class Game {
    private Level level;
    private JSONArray state;
    private LocalDateTime startTime;


    // CONSTRUCTORS

    public Game(Level level) {
        this.level = level;
        this.state = level.getPattern();
        this.startTime = LocalDateTime.now();
    }


    // GETTERS

    public String getElapsedTime() {
        LocalDateTime now = LocalDateTime.now();
        long seconds = java.time.Duration.between(this.startTime, now).getSeconds();
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
