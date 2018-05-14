package persistence;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.After;
<<<<<<< HEAD
import org.junit.AfterClass;
import org.junit.Before;
=======
>>>>>>> c23571953125c05208dddc297727aa9c50c5b2bc
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.vehicle.Vehicle;
import model.vehicleType.Category;
import service.vehicle.VehicleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml", "/META-INF/spring-application-context.xml"})
public class VehicleRepositoryTest {
	
    @Autowired
    private VehicleService vehicleService;
    
    @Before
    public void seuTp(){
    	this.cleanDatabase();
    }
    
	private void cleanDatabase() {
		vehicleService.retriveAll().stream().forEach(user -> vehicleService.delete(user));
	}

	@After
	public void tearDown() {
		this.cleanDatabase();
	}
    
    @Test
    public void testSave() {

    	Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0." , new ArrayList<BufferedImage>(), 5);

    	vehicleService.save(vehicle);
        assertEquals(1, vehicleService.retriveAll().size());
    }
    

    @Test
    public void testRestoreFromDataBaseVehicle() {
    	Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0." , new ArrayList<BufferedImage>(), 5);
    	
    	vehicleService.save(vehicle);
    	
    	Vehicle restoredVehicle = vehicleService.searchById(vehicle.getId());
    	
    	assertEquals(restoredVehicle.getPassengerCapacity(), vehicle.getPassengerCapacity());
    	assertEquals(restoredVehicle.getDescription(), vehicle.getDescription());

    }
    
    @Test
    public void testUpdateVehicle() {
    	Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0." , new ArrayList<BufferedImage>(), 5);
    	
    	vehicleService.save(vehicle);
    	
    	Vehicle restoredVehicle = vehicleService.searchById(vehicle.getId());
    	
    	String newDescription = "No es tan grande pero si muy espacioso. Motor 2.0";
    	
    	restoredVehicle.setDescription(newDescription);
    	
    	vehicleService.update(restoredVehicle);
    	
    	restoredVehicle = vehicleService.searchById(vehicle.getId());
    	
    	assertEquals(restoredVehicle.getPassengerCapacity(), vehicle.getPassengerCapacity());
    	assertEquals(restoredVehicle.getDescription(), newDescription);

    }


}