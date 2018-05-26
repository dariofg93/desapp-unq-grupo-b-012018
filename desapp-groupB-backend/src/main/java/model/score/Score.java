package model.score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.utils.Entity;

public class Score extends Entity{

    private Double value;
    @JsonIgnore
    private ScoreType scoreType;
    
    
    public Score() {
		super();
	}

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
    
	public ScoreType getScoreType() {
		return scoreType;
	}
	
	public void setScoreType(ScoreType scoreType) {
		this.scoreType = scoreType;
	}
    
    public String description() {
    	return this.scoreType.description();
    }
    
    public static ScoreType fromCode(String description) {
        for (ScoreType type : avaliableScoreType()){
            if (type.description().equals(description)){
                return type;
            }
        }
        throw new UnsupportedOperationException();
    }

	private static List<ScoreType> avaliableScoreType() {
		List<ScoreType> avaliableScoreType = new ArrayList<ScoreType>();
		avaliableScoreType.add(new LesseeScoreType());
		avaliableScoreType.add(new OwnerScoreType());
		avaliableScoreType.add(new VehicleScoreType());
		return avaliableScoreType;
	}
}
