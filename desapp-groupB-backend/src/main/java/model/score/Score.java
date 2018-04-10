package model.score;

public class Score {

    private Double value;
    private ScoreType scoreType;
    
    public Score(ScoreType aScoreType) {
    	scoreType = aScoreType;
    	value = 5.0d;
    }

    public void setValue(Double aValue) {
    	value = aValue;
    }
    public Double getValue() {
        return value;
    }
    
    public String description() {
    	return this.scoreType.description();
    }
}
