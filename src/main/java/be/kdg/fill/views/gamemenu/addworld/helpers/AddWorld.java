package be.kdg.fill.views.gamemenu.addworld.helpers;

import be.kdg.fill.models.core.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddWorld {
    private String name;
    private String imagePath;
    private String difficulty;
    private List<Level> levels;

    public AddWorld(String name, String difficulty) {
        this.name = name;
        this.imagePath = "images/admin-foto.jpg";
        this.difficulty = difficulty;
        this.levels = new ArrayList<>();
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public void saveToJson() {
        int fileCount = countFiles("src/main/resources/be/kdg/fill/data/worlds");

        String filename = "world-" + (fileCount + 1) + ".json";
        int worldAndDifId = fileCount + 1;

        Map<String, Object> difficultyObject = new LinkedHashMap<>();
        difficultyObject.put("id", worldAndDifId);
        difficultyObject.put("name", difficulty);

        Map<String, Object> worldMap = new LinkedHashMap<>();
        worldMap.put("id", worldAndDifId);
        worldMap.put("name", name);
        worldMap.put("imagePath", imagePath);
        worldMap.put("difficulty", difficultyObject);

        JSONArray levelsArray = new JSONArray();
        for (Level level : levels) {
            Map<String, Object> levelMap = new LinkedHashMap<>();
            levelMap.put("id", level.getId());
            levelMap.put("pattern", convertPatternToJsonArray(level.getPattern()));
            levelMap.put("start", level.getStartPosJson());
            levelsArray.add(levelMap);
        }
        worldMap.put("levels", levelsArray);

        String jsonString = JSONObject.toJSONString(worldMap);
        jsonString = jsonString.replace("\\/", "/");

        String directoryPath = "src/main/resources/be/kdg/fill/data/worlds";

        File directory = new File(directoryPath);
        File file = new File(directory, filename);
        try (FileWriter fileWriter = new FileWriter(file)) {
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

    private int countFiles(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            return files.length;
        }
        return 0;
    }
}
