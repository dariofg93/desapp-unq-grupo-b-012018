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

import model.user.User;
import model.vehicle.Vehicle;
import model.vehicletype.Category;
import service.bookingrequest.BookingRequestService;
import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml",
		"/META-INF/spring-application-context.xml" })
public class VehicleRepositoryTest {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private UserService userService;
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private BookingRequestService bookingRequestService;

	@Before
	public void setUp() {
		this.cleanDatabase();
	}

	private void cleanDatabase() {
		publicationService.retriveAll().stream().forEach(a -> publicationService.delete(a));
		bookingRequestService.retriveAll().stream().forEach(a -> bookingRequestService.delete(a));
		vehicleService.retriveAll().stream().forEach(a -> vehicleService.delete(a));
		userService.retriveAll().stream().forEach(a -> userService.delete(a));

	}

	@After
	public void tearDown() {
		this.cleanDatabase();
	}

	@Test
	public void testSave() {
		User user = new User();
		userService.save(user);

		Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0.",
				new ArrayList<BufferedImage>(), 5, user);

		vehicleService.save(vehicle);
		assertEquals(1, vehicleService.retriveAll().size());
	}

	@Test
	public void testRestoreFromDataBaseVehicle() {
		User user = new User();
		userService.save(user);
		Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0.",
				new ArrayList<BufferedImage>(), 5, user);

		vehicleService.save(vehicle);

		Vehicle restoredVehicle = vehicleService.searchById(vehicle.getId());

		assertEquals(restoredVehicle.getPassengerCapacity(), vehicle.getPassengerCapacity());
		assertEquals(restoredVehicle.getDescription(), vehicle.getDescription());
		assertEquals(restoredVehicle.getCategory().getName(), vehicle.getCategory().getName());

	}

	@Test
	public void testUpdateVehicle() {
		User user = new User();
		userService.save(user);
		Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0.",
				new ArrayList<BufferedImage>(), 5, user);

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
		User user = new User();
		userService.save(user);
		Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0.",
				new ArrayList<BufferedImage>(), 5, user);
		Vehicle anotherVehicle = new Vehicle(Category.car(), "Deportivo", new ArrayList<BufferedImage>(), 2);

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
		User user = new User();
		userService.save(user);

		Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0.",
				new ArrayList<BufferedImage>(), 5, user);
		Vehicle anotherVehicle = new Vehicle(Category.car(), "Deportivo", new ArrayList<BufferedImage>(), 2);

		vehicleService.save(vehicle);

		anotherVehicle.setId(null);

		vehicleService.updateById(vehicle.getId(), anotherVehicle);

		Vehicle restoredVehicle = vehicleService.searchById(vehicle.getId());

		assertEquals(restoredVehicle.getPassengerCapacity(), anotherVehicle.getPassengerCapacity());
		assertEquals(restoredVehicle.getDescription(), anotherVehicle.getDescription());

	}

}