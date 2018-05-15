package model.score;

import java.io.Serializable;

public class OwnerScoreType implements Serializable, ScoreType{
	
	@Override
	public String description() {
		
		return "Owner Score";
	}
}
