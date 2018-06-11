package persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javassist.expr.NewArray;
import model.builders.UserBuilder;
import model.creditsaccount.CreditsAccount;
import model.email.Email;
import model.exceptions.BookingNotFoundException;
import model.maps.GeographicZoneDescription;
import model.score.OwnerScoreType;
import model.score.Score;
import model.score.ScoreManager;
import model.user.User;
import persistence.generic.GenericService;
import service.bookingrequest.BookingRequestService;
import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml",
		"/META-INF/spring-application-context.xml" })

public class UserRepositoryServiceTest {

	@Autowired(required = true)
	UserService userService;
    @Autowired
    private VehicleService vehicleService;
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private BookingRequestService bookingRequestService;
	

	private Double creditAmount;
	private CreditsAccount account;
	private String cuil;

	private User anyUser;

	@Before
	public void setUp() {
		cleanDatabase();
		createUser();
	}

	@After
	public void tearDown() {
		this.cleanDatabase();
	}

	@Test
	public void testSave() {
		userService.save(anyUser);
		assertEquals(1, userService.retriveAll().size());
	}

	@Test
	public void testSaveAndRestoreSomeUser() {
		userService.save(anyUser);
		User restoredUser = userService.searchById(anyUser.getId());
		assertEquals(restoredUser.getCreditsAccount().getAmount(), creditAmount);
		assertEquals(cuil, restoredUser.getCuil());
		assertEquals(restoredUser.getEmail().getAccountName(), "pepe-bueno@hotmail.com");
		assertEquals(restoredUser.getFirstName(), "Pepe");
		assertEquals(restoredUser.getLastName(), "Bueno");
		assertEquals(restoredUser.getMovementsOfMonth().allHistory(), "Hoy alquile\n" + "Hoy alquile otro auto\n");
	
	
	}

	@Test
	public void testGetUserForEmailName() {
		userService.save(anyUser);
		
		User restoredUser = userService.searchUserByEmailNamed("pepe-bueno@hotmail.com").get(0);
		
		assertEquals(restoredUser.getCreditsAccount().getAmount(), creditAmount);
		assertEquals(cuil, restoredUser.getCuil());
		assertEquals(restoredUser.getEmail().getAccountName(), "pepe-bueno@hotmail.com");
		assertEquals(restoredUser.getFirstName(), "Pepe");
		assertEquals(restoredUser.getLastName(), "Bueno");
		assertEquals(restoredUser.getMovementsOfMonth().allHistory(), "Hoy alquile\n" + "Hoy alquile otro auto\n");
	}

	
	/*
	 * 		SUPPORT
	 */
	
	private void createUser() {
		creditAmount = 3000.0;
		account = new CreditsAccount();
		GeographicZoneDescription zone = new GeographicZoneDescription(-58.302840100000026, -34.6907607);
		cuil = "20-35317288-7";
		Score score = new Score(new OwnerScoreType());
		List<Score>  scoreList = new ArrayList<Score>();
		scoreList.add(score);

		anyUser = new UserBuilder().createUser().withScoreManager(new ScoreManager(scoreList)).build();

		anyUser.setAddress(zone);
		anyUser.setCreditsAccount(account);
		anyUser.depositCredits(creditAmount);
		anyUser.setCuil(cuil);
		anyUser.setFirstName("Pepe");
		anyUser.setLastName("Bueno");
		anyUser.setEmail(new Email("pepe-bueno@hotmail.com"));
		anyUser.getMovementsOfMonth().addToHistory("Hoy alquile");
		anyUser.getMovementsOfMonth().addToHistory("Hoy alquile otro auto");
	}

	private void cleanDatabase() {
//		List<GenericService<?>> list = new ArrayList<GenericService<?>>();
//		list.add(publicationService);
//		list.add(vehicleService);
//		list.add(bookingRequestService);
//		list.add(userService);
//		
//		list.forEach((service) -> service.retriveAll().stream().forEach((T object) -> service.delete(object)));
		publicationService.retriveAll().stream().forEach(a -> publicationService.delete(a));
		bookingRequestService.retriveAll().stream().forEach(a -> bookingRequestService.delete(a));
		vehicleService.retriveAll().stream().forEach(a -> vehicleService.delete(a));
		userService.retriveAll().stream().forEach(a -> userService.delete(a));
	}
}
