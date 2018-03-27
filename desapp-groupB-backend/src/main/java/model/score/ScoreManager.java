package model.score;

import java.util.List;

public class ScoreManager {

    private List<Score> scores;

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
}
