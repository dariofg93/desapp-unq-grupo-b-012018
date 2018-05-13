package service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import model.vehicle.Vehicle;
import model.vehicleType.Category;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({  "/META-INF/spring-application-context.xml", "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
@WebAppConfiguration
public class VehicleRestTest {
 
    private MockMvc mockMvc;
 
    @Autowired
    private VehicleService vehicleService;
    
    private VehicleService spiedVehicleService;
    
    @Before
    public void setUp() {
    	spiedVehicleService = spy(vehicleService);
    }
    
//	@After
//	public void tearDown() {
//		vehicleService.retriveAll().stream().forEach(user -> vehicleService.delete(user));
//	}

    @Test
    public void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception {
      	Vehicle vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0." , new ArrayList<BufferedImage>(), 5);
      	Vehicle anotherVehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0." , new ArrayList<BufferedImage>(), 5);
      	
      	List<Vehicle> vehicles = new ArrayList<Vehicle>();
      	vehicles.add(vehicle);
      	vehicles.add(anotherVehicle);
      	
     //   when(spiedVehicleService.retriveAll()).thenReturn(vehicles);
 
        mockMvc.perform(get("/vehicles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$[0].title", is("Foo")))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$[1].title", is("Bar")));
 
        verify(vehicleService, times(1)).retriveAll();
        verifyNoMoreInteractions(vehicleService);
    }
}
