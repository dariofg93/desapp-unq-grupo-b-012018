package persistence;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.vehicle.Vehicle;
import model.vehicleType.Category;
import service.VehicleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
public class VehicleRepositoryTest {

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void testSave() {
    	
    	Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0." , new ArrayList<BufferedImage>(), 5);
    	
    	vehicleService.save(vehicle);
        Assert.assertEquals(1, vehicleService.retriveAll().size());
    }

}