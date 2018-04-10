package model.score;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ScoreManagerTest {
	

    private ScoreManager scoreManager;
    private List<Score> scores;
    
    @Before
    public void setUp() {
    	scores = spy(new ArrayList<Score>());
    	scoreManager = new ScoreManager(scores);
    }
    
	@Test
	public void testCreation() {
		assertEquals(scoreManager.minimumScoreAccepted(), new Double(4));
		assertEquals(scoreManager.maximumPossibleScore(), new Double(5));
	}
	
	@Test
	public void testAddScore() {
		scoreManager.addScore(new Score(new OwnerScoreType()));
		verify(scores).add(any(Score.class));
	}
	
	@Test
	public void testAverageScoreIsMmaximumPossibleScoreWhenScoresIsEmpty() {
		assertEquals(scoreManager.averageScore(), scoreManager.maximumPossibleScore());
	}
	
	@Test
	public void testAverageScore() {
		Score score = new Score(new OwnerScoreType());
		score.setValue(4d);
		Score anotherScore = new Score(new OwnerScoreType());
		anotherScore.setValue(5d);
		
		scoreManager.addScore(anotherScore);
		scoreManager.addScore(score);
		assertEquals(scoreManager.averageScore(), new Double(4.5));
	}

}
