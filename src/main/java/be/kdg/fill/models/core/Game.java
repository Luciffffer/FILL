package be.kdg.fill.models.core;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Stack;

public class Game {
    private Level level;
    private int[][] pattern; // 0 = empty, 1 = block, 2 = start
    private Stack<int[]> line; // [row, col, relative position of element against element before (0 = right, 1 = down, 2 = left, 3 = up)]
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int blockCount;
    private boolean gameFinished;


    // CONSTRUCTORS

    /**
     * Constructor for a new game
     * @param Level level
     */
    public Game(Level level) 
    {
        this.level = level;
        this.pattern = level.getPattern();
        this.line = new Stack<>();
        this.line.push(level.getStartPos());
        this.pattern[level.getStartPos()[0]][level.getStartPos()[1]] = 2;
        this.startTime = LocalDateTime.now();

        this.blockCount = 0;
        for (int[] row : this.pattern) {
            for (int col : row) {
                if (col == 1) {
                    this.blockCount++;
                }
            }
        }
    }


    // GETTERS

    /**
     * GetElapsedTime
     * returns the time elapsed since the start of the game
     * @return String MM:SS
     */
    public String getElapsedTime() 
    {
        LocalDateTime now;

        if (this.endTime != null) {
            now = this.endTime;
        } else {
            now = LocalDateTime.now();
        }

        long seconds = Duration.between(this.startTime, now).getSeconds();
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * GetPattern
     * returns the pattern of the game.
     * 0 = empty, 1 = block, 2 = start
     * @return int[][] pattern
     */
    public int[][] getPattern() 
    {
        return this.pattern;
    }

    /**
     * GetLine
     * returns the line of the game
     * @return Stack<int[]> line
     */
    public Stack<int[]> getLine() 
    {
        return this.line;
    }

    /**
     * GetTopOfLine
     * returns the top of the line.
     * [row, col, relative position of element against element before (0 = right, 1 = down, 2 = left, 3 = up)]
     * @return int[] top of line
     */
    public int[] getTopOfLine() 
    {
        return this.line.peek();
    }

    /**
     * IsFinished
     * returns whether the game is finished
     * @return boolean gameFinished
     */
    public boolean isFinished() 
    {
        return this.gameFinished;
    }

    /**
     * GetLevel
     * returns the level of the game
     * @return Level level
     */
    public Level getLevel()
    {
        return this.level;
    }

    /**
     * getStars
     * returns the amount of stars the player has earned out of 5
     * @return StarRating
     * @throws IllegalStateException
     */
    public StarRating getStarRating() throws IllegalStateException
    {
        if (!this.gameFinished) {
            throw new IllegalStateException("Game is not finished yet");
        }

        long seconds = Duration.between(this.startTime, this.endTime).getSeconds();
        
        if (seconds <= StarRating.FIVE.getSecondsSolved()) {
            return StarRating.FIVE;
        } else if (seconds <= StarRating.FOUR.getSecondsSolved()) {
            return StarRating.FOUR;
        } else if (seconds <= StarRating.THREE.getSecondsSolved()) {
            return StarRating.THREE;
        } else if (seconds <= StarRating.TWO.getSecondsSolved()) {
            return StarRating.TWO;
        } else if (seconds <= StarRating.ONE.getSecondsSolved()) {
            return StarRating.ONE;
        } else {
            return StarRating.ZERO;
        }
    }


    // METHODS

    /**
     * UpdateLine
     * updates the line of the game if a valid move is made.
     * If all blocks are connected, sets gameFinished to true.
     * will do nothing if the game is already finished, the move is out of bounds, the move is on an empty space or the move is not next to the last element in the line.
     * @param int row
     * @param int col
     */
    public void updateLine(int row, int col)
    {
        if (this.gameFinished) {
            return;
        }

        // check if out of bounds
        if (row < 0 || row >= this.pattern.length || col < 0 || col >= this.pattern[0].length) {
            return;
        }

        // check if not empty
        if (this.pattern[row][col] == 0) {
            return;
        }

        // check if in stack
        for (int i = 0; i < this.line.size(); i++) {
            if (this.line.get(i)[0] == row && this.line.get(i)[1] == col) {
                // remove from stack
                while (this.line.size() > i + 1) {
                    this.line.pop();
                }
                return;
            }
        }

        // check if next to last element in stack
        int[] last = this.line.peek();
        if (Math.abs(last[0] - row) + Math.abs(last[1] - col) == 1) {

            // this do be kinda ugly
            if (last[0] - row == -1) {
                this.line.push(new int[]{row, col, 3});
            } else if (last[0] - row == 1) {
                this.line.push(new int[]{row, col, 1});
            } else if (last[1] - col == -1) {
                this.line.push(new int[]{row, col, 2});
            } else if (last[1] - col == 1) {
                this.line.push(new int[]{row, col, 0});
            }

        }

        if (this.line.size() == this.blockCount + 1) {
            this.gameFinished = true;
            this.endTime = LocalDateTime.now();
        }
    }
}
