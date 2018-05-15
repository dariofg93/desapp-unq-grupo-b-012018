package model.score;

import static org.junit.Assert.*;

import org.junit.Test;


public class ScoreTest {
	
	Score score;
	VehicleScoreType vehicleScoreType;
	LesseeScoreType lesseeScoreType;
	OwnerScoreType ownerScoreType;


	@Test
	public void testCreation() {
		ownerScoreType = new OwnerScoreType();
		score = new Score(ownerScoreType);
	
		assertEquals(score.getValue(), new Double(5.0));
		assertEquals(score.description(), ownerScoreType.description());
	}
	
	
	@Test
	public void testSetValue() {
		ownerScoreType = new OwnerScoreType();
		score = new Score(ownerScoreType);
		
		score.setValue(new Double(4));
		
		assertEquals(score.getValue(), new Double(4));
	}
	

	@Test
	public void testScoreTypeDescriptions() {
		ownerScoreType = new OwnerScoreType();
		lesseeScoreType = new LesseeScoreType();
		vehicleScoreType = new VehicleScoreType();
		
		
		assertEquals(ownerScoreType.description(),"Owner Score");
		assertEquals(lesseeScoreType.description(),"Lessee Score");
		assertEquals(vehicleScoreType.description(), "Vehicle Score");
	}

}
