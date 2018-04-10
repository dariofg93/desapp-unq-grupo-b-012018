package model.maps;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GeographicZoneDescriptionTest {
	
	GeographicZoneDescription zone;

	 @Before
		public void setUp() throws Exception {
		 zone = new GeographicZoneDescription();
	 }
	@Test
	public void testCreation() {
		assertFalse(zone == null);
	}

}
