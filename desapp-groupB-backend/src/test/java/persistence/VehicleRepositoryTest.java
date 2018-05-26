package persistence;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.vehicle.Vehicle;
import model.vehicletype.Category;
import service.vehicle.VehicleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml", "/META-INF/spring-application-context.xml"})
public class VehicleRepositoryTest {
	
    @Autowired
    private VehicleService vehicleService;
    
    @Before
    public void setUp(){
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
    	assertEquals(restoredVehicle.getCategory().getName(), vehicle.getCategory().getName());

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
    	assertEquals(restoredVehicle.getCategory().getName(), vehicle.getCategory().getName());

    }
    
    @Test
    public void testUpdateVehicleWhenNotHaveId() {
    	Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0." , new ArrayList<BufferedImage>(), 5);
    	Vehicle anotherVehicle = new Vehicle(Category.car(), "Deportivo" , new ArrayList<BufferedImage>(), 2);
    	
    	vehicleService.save(vehicle);
    			
    	anotherVehicle.setId(vehicle.getId());
    	
    	vehicleService.update(anotherVehicle);
    	
    	Vehicle restoredVehicle = vehicleService.searchById(vehicle.getId());
    	
    	assertEquals(restoredVehicle.getPassengerCapacity(), anotherVehicle.getPassengerCapacity());
    	assertEquals(restoredVehicle.getDescription(), anotherVehicle.getDescription());
       	assertEquals(restoredVehicle.getCategory().getName(), anotherVehicle.getCategory().getName());

    }    
    
    @Test
    public void testUpdateVehicleById() {
    	Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0." , new ArrayList<BufferedImage>(), 5);
    	Vehicle anotherVehicle = new Vehicle(Category.car(), "Deportivo" , new ArrayList<BufferedImage>(), 2);
    	
    	vehicleService.save(vehicle);
    			
    	anotherVehicle.setId(null);
    	
    	vehicleService.updateById(vehicle.getId(), anotherVehicle);
    	
    	Vehicle restoredVehicle = vehicleService.searchById(vehicle.getId());
    	
    	assertEquals(restoredVehicle.getPassengerCapacity(), anotherVehicle.getPassengerCapacity());
    	assertEquals(restoredVehicle.getDescription(), anotherVehicle.getDescription());

    }


}