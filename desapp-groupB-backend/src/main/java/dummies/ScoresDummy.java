package dummies;

import model.builders.ScoreBuilder;
import model.score.LesseeScoreType;
import model.score.OwnerScoreType;
import model.score.Score;
import persistence.generic.GenericService;

import java.util.ArrayList;
import java.util.List;

public class ScoresDummy implements DummyData{

    private List<Score> scores = new ArrayList<>();
    private ScoreBuilder builder = new ScoreBuilder();
    private GenericService<Score> service;

    public ScoresDummy(){
        Score score1 = builder.createScore()
                .withValue(4.5)
                .withScoreType(new OwnerScoreType())
                .build();
        this.scores.add(score1);

        Score score2 = builder.createScore()
                .withValue(4.0)
                .withScoreType(new LesseeScoreType())
                .build();
        this.scores.add(score2);
    }

    public void setScoreBuilder(ScoreBuilder scoreBuilder) { this.builder= scoreBuilder; }
    public ScoreBuilder getScoreBuilder() { return this.builder; }

    public void setScores(List<Score> scores) { this.scores = scores; }
    public List<Score> getScores() { return this.scores; }

    public void setService(GenericService<Score> service) {
        this.service = service;
    }
    public GenericService<Score> getService() {
        return this.service;
    }

    public void instantiateDataMock(){
        this.scores.forEach(
                (Score score) -> this.service.save(score)
        );
    }
}
