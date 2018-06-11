package persistence;

import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.booking.BookingRequest;
import service.bookingrequest.BookingRequestService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml", "/META-INF/spring-application-context.xml"})
public class BookigRequestRepositoryServiceTest {
	
		
	    @Autowired
	    private BookingRequestService bookingRequestService;
	    
	    @Before
	    public void setUp(){
	    	this.cleanDatabase();
	    }
	    
		private void cleanDatabase() {
			bookingRequestService.retriveAll().stream().forEach(request -> bookingRequestService.delete(request));
		}

		@After
		public void tearDown() {
			this.cleanDatabase();
		}
		
		@Test
	    public void testSave() {

	    	BookingRequest bookingRequest = new BookingRequest();

	    	bookingRequestService.save(bookingRequest);
	        assertEquals(1, bookingRequestService.retriveAll().size());
	    }


}
