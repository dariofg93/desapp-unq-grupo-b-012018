package model.city;

import junit.framework.TestCase;
import org.junit.Before;

public class CityTest extends TestCase {

    private City city;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.city = new City("Quilmes");
	}

    public void testFilterAndOrder(){
        City city2 = new City("Bernal");

        assertFalse(city.isSame(city2));
    }
}
