package model.category;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.vehicletype.Category;

public class CategoryTest {
	
	private Category carCategory;
	private Category scooterCategory;
	
	 @Before
		public void setUp() throws Exception {
		 carCategory =  Category.car();
		 scooterCategory = Category.scooter();
	 }
	@Test
	public void testGetName() {
		assertEquals(carCategory.getName(), "Car");
		assertEquals(scooterCategory.getName(), "Scooter");
	}
	
	@Test
	public void testIsSame() {
		assertTrue(carCategory.isSame(Category.car()));
		assertFalse(carCategory.isSame(Category.scooter()));
		
		assertTrue(scooterCategory.isSame(Category.scooter()));
		assertFalse(scooterCategory.isSame(Category.car()));
	}

}
