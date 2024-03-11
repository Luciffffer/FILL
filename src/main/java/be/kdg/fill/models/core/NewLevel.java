package be.kdg.fill.models.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewLevel {
    public int[][] parseMatrix(String matrixText) {
        List<List<Integer>> matrixList = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");
        Matcher matcher = pattern.matcher(matrixText);

        while (matcher.find()) {
            String rowText = matcher.group(1);
            String[] elements = rowText.split(",");
            List<Integer> rowList = new ArrayList<>();

            for (String element : elements) {
                rowList.add(Integer.parseInt(element.trim()));
            }

            matrixList.add(rowList);
        }
        int[][] matrix = new int[matrixList.size()][];
        for (int i = 0; i < matrixList.size(); i++) {
            List<Integer> rowList = matrixList.get(i);
            matrix[i] = new int[rowList.size()];
            for (int j = 0; j < rowList.size(); j++) {
                matrix[i][j] = rowList.get(j);
            }
        }

        return matrix;
    }
}