package model.builders;

import model.score.Score;
import model.score.ScoreType;

public class ScoreBuilder {

    private Score buildScore;

    public ScoreBuilder createScore() {
        this.buildScore = new Score();
        return this;
    }

    public Score build() {
        return this.buildScore;
    }

    public ScoreBuilder withValue(Double value) {
        this.buildScore.setValue(value);
        return this;
    }

    public ScoreBuilder withScoreType(ScoreType scoreType) {
        this.buildScore.setScoreType(scoreType);
        return this;
    }
}
