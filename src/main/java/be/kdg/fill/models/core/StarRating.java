package be.kdg.fill.models.core;

public enum StarRating {
    ZERO(0, Double.POSITIVE_INFINITY, "Solved in more than 3 minutes"),
    ONE(1, 180, "Solved in 3 minutes"), 
    TWO(2, 60, "Solved in 1 minute"), 
    THREE(3, 30, "Solved in 30 seconds"), 
    FOUR(4, 20, "Solved in 20 seconds"), 
    FIVE(5, 10, "Solved in 10 seconds");

    private final int value;
    private final double secondsSolved;
    private final String explanation;

    StarRating(int value, double secondsSolved, String explanation) 
    {
        this.value = value;
        this.secondsSolved = secondsSolved;
        this.explanation = explanation;
    }

    public int getValue() 
    {
        return value;
    }

    public double getSecondsSolved() 
    {
        return secondsSolved;
    }

    public String getExplanation() 
    {
        return explanation;
    }
}
