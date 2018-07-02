package model.score;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.utils.Entity;

public class Score extends Entity{

    private Double value; 
    

	public Score() {
    	value = 5.0d;
    }

    public void setValue(Double aValue) {
    	value = aValue;
    }
    public Double getValue() {
        return value;
    }
    
}
