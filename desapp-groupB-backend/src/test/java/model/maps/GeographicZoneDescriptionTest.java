package model.maps;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GeographicZoneDescriptionTest {

	private GeographicZoneDescription zone;

	@Before
	public void setUp() throws Exception {
		zone = new GeographicZoneDescription();
	}

	@Test
	public void testCreation() {
		assertFalse(zone == null);
	}

	@Test
	public void testCreationAndSomeSpecificalAdrress() {

		/*
		 * Direccion: Av. las Flores 1600, Wilde, Buenos Aires, Argentina
		 */
		Double longitud = -58.302840100000026;
		Double latitud = -34.6907607;

		GeographicZoneDescription anotherZone = new GeographicZoneDescription(latitud, longitud);

		assertEquals(anotherZone.getLatitud(), latitud);
		assertEquals(anotherZone.getLongitud(), longitud);
	}

	@Test
	public void testCreationAndSetSomeSpecificalAdrress() {

		/*
		 * Direccion: Av. las Flores 1600, Wilde, Buenos Aires, Argentina
		 */
		Double longitud = -58.302840100000026;
		Double latitud = -34.6907607;

		zone.setLatitud(latitud);
		zone.setLongitud(longitud);

		assertEquals(zone.getLatitud(), latitud);
		assertEquals(zone.getLongitud(), longitud);
	}

	@Test
	public void testEquals() {

		/*
		 * Direccion: Av. las Flores 1600, Wilde, Buenos Aires, Argentina
		 */
		Double longitud = -58.302840100000026;
		Double latitud = -34.6907607;

		zone.setLatitud(latitud);
		zone.setLongitud(longitud);

		assertFalse(zone.equals(new GeographicZoneDescription(0.0, longitud)));
		assertFalse(zone.equals(new GeographicZoneDescription(latitud, 0.0)));
		assertEquals(zone, (new GeographicZoneDescription(latitud, longitud)));
	}

}