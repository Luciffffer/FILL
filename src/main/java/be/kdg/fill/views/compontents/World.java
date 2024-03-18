package be.kdg.fill.views.compontents;

import be.kdg.fill.models.core.Level;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class World {
    private String name;
    private String imagePath;
    private String difficulty;
    private List<Level> levels;

    public World(String name, String difficulty) {
        this.name = name;
        this.imagePath = "images/admin-foto.jpg";
        this.difficulty = difficulty;
        this.levels = new ArrayList<>();
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public void saveToJson() {
        JSONObject worldObject = new JSONObject();
        worldObject.put("name", name);
        worldObject.put("imagePath", imagePath);
        worldObject.put("difficulty", difficulty);

        JSONArray levelsArray = new JSONArray();
        for (Level level : levels) {
            JSONObject levelObject = new JSONObject();
            levelObject.put("id", level.getId());
            levelObject.put("pattern", convertPatternToJsonArray(level.getPattern()));
            levelObject.put("start", (level.getStartPos()));
            levelsArray.add(levelObject);
        }
        worldObject.put("levels", levelsArray);

        String jsonString = worldObject.toJSONString();
        jsonString = jsonString.replace("\\/", "/");

        String directoryPath = "src/main/resources/be/kdg/fill/data/worlds";
        String filename = name.toLowerCase() + "_world.json";

        File directory = new File(directoryPath);
        File file = new File(directory, filename);
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray convertPatternToJsonArray(int[][] pattern) {
        JSONArray patternArray = new JSONArray();
        for (int[] row : pattern) {
            JSONArray rowArray = new JSONArray();
            for (int value : row) {
                rowArray.add(value);
            }
            patternArray.add(rowArray);
        }
        return patternArray;
    }
}

