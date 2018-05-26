package model.score;

import java.util.List;

import model.utils.Entity;

public class ScoreManager extends Entity {
	
	private static final long serialVersionUID = 532916824793782845L;

	private List<Score> scores;

	public ScoreManager(List<Score> someList) {
		super();
    	scores = someList;
    } 

    public Double minimumScoreAccepted() {
        return 4.0;
    }
    public Double maximumPossibleScore() {
        return 5.0;
    }

    public Double averageScore() {
        if(this.scores.isEmpty())
            return maximumPossibleScore();
        else
            return this.scores.stream().mapToDouble(Score::getValue).sum() / this.scores.size();
    }

    public void addScore(Score anyScore) {
        this.scores.add(anyScore);
    }
    
    public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
    
    public ScoreManager() {
		super();
    }
}
