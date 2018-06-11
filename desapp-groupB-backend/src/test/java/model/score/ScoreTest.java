package model.score;

import static org.junit.Assert.*;

import org.junit.Test;


public class ScoreTest {
	

	@Test
	public void testCreation() {
		OwnerScoreType ownerScoreType = new OwnerScoreType();
		Score score = new Score(ownerScoreType);
	
		assertEquals(score.getValue(), new Double(5.0));
		assertEquals(score.description(), ownerScoreType.description());
	}
	
	
	@Test
	public void testSetValue() {
		OwnerScoreType ownerScoreType = new OwnerScoreType();
		Score score = new Score(ownerScoreType);
		
		score.setValue(new Double(4));
		
		assertEquals(score.getValue(), new Double(4));
	}
	

	@Test
	public void testScoreTypeDescriptions() {
		OwnerScoreType ownerScoreType = new OwnerScoreType();
		LesseeScoreType lesseeScoreType = new LesseeScoreType();
		VehicleScoreType vehicleScoreType = new VehicleScoreType();
		
		
		assertEquals(ownerScoreType.description(),"Owner Score");
		assertEquals(lesseeScoreType.description(),"Lessee Score");
		assertEquals(vehicleScoreType.description(), "Vehicle Score");
	}

}
