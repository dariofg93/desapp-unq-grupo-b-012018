package dummies;

import model.builders.VehicleBuilder;
import model.vehicle.Vehicle;
import model.vehicletype.Car;
import model.vehicletype.Scooter;
import service.user.UserService;
import service.vehicle.VehicleService;

import java.util.ArrayList;
import java.util.List;


public class VehiclesDummy implements DummyData{

    private List<Vehicle> vehicles = new ArrayList<>();
    private VehicleBuilder builder = new VehicleBuilder();
    private VehicleService service;
    private UserService userService;

    public void setVehicleBuilder(VehicleBuilder vehicleBuilder) { this.builder= vehicleBuilder; }
    public VehicleBuilder getVehicleBuilder() { return this.builder; }

    public void setVehicles(List<Vehicle> vehicles) { this.vehicles = vehicles; }
    public List<Vehicle> getVehicles() { return this.vehicles; }

    public void setService(VehicleService service) {
        this.service = service;
    }
    public VehicleService getService() {
        return this.service;
    }

    public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void instantiateDataMock(){
		
		this.initializeContext();
		
        this.vehicles.forEach(
                (Vehicle vehicle) -> this.service.save(vehicle)
        );
    }

	private void initializeContext() {
		
		List<String> someList = this.listWith("https://img.clasf.com.ar/2016/11/15/FORD-KA-MOTOR-1-0-FULL-MOD-2000-20161115024340.jpg");
		someList.add("http://www.cars-directory.net/pics/ford/ka/2000/ford_ka_a1247741434b2854979_3_orig.jpg");
		someList.add("http://i.autos.com.ar/fotos/2010/1122/Ford-Ka-10-IMAGE-2000-201011220308263.jpg");
		someList.add("https://i.ytimg.com/vi/mwGpWPsVjKk/hqdefault.jpg");
		
	    Vehicle vehicle1 = builder.createVehicle()
                .withDescription("Ford KA usadito")
                .withPassengerCapacity(2)
                .withCategory(new Car())
                .withPictures(someList)
                .withOwner(userService.retriveAll().get(0))
                .build();
        this.vehicles.add(vehicle1);
       
        Vehicle vehicle2 = builder.createVehicle()
                .withDescription("Kawasaki ninja")
                .withPassengerCapacity(1)
                .withCategory(new Scooter())
                .withPictures(this.listWith("http://3.bp.blogspot.com/-2Y-TefVuSck/VhXrMPUO8gI/AAAAAAAABwo/uHvVy0K4hxw/s1600/Fotor_143640089326160.jpg"))
                .withOwner(userService.retriveAll().get(1))
                .build();
        this.vehicles.add(vehicle2);

        Vehicle vehicle3 = builder.createVehicle()
                .withDescription("Renault 12")
                .withPassengerCapacity(2)
                .withCategory(new Scooter())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(1))
                .build();
        this.vehicles.add(vehicle3);

        Vehicle vehicle4 = builder.createVehicle()
                .withDescription("Mondial 150cc")
                .withPassengerCapacity(1)
                .withCategory(new Scooter())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(1))
                .build();
        this.vehicles.add(vehicle4);

        Vehicle vehicle5 = builder.createVehicle()
                .withDescription("Toyota Hilux 2009")
                .withPassengerCapacity(1)
                .withCategory(new Car())
                .withPictures(this.listWith("https://autos.superclasificados.hn/uploads/advices/images/gallery_1515613973-9332.jpg"))
                .withOwner(userService.retriveAll().get(1))
                .build();
        this.vehicles.add(vehicle5);

        Vehicle vehicle6 = builder.createVehicle()
                .withDescription("Chevrolet Cruze")
                .withPassengerCapacity(1)
                .withCategory(new Car())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(3))
                .build();
        this.vehicles.add(vehicle6);

        Vehicle vehicle7 = builder.createVehicle()
                .withDescription("Renault Sandero 2017")
                .withPassengerCapacity(2)
                .withCategory(new Car())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(0))
                .build();
        this.vehicles.add(vehicle7);

        Vehicle vehicle8 = builder.createVehicle()
                .withDescription("Yamaha FZ")
                .withPassengerCapacity(1)
                .withCategory(new Scooter())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(1))
                .build();
        this.vehicles.add(vehicle8);

        Vehicle vehicle9 = builder.createVehicle()
                .withDescription("Ford Focus 2008")
                .withPassengerCapacity(4)
                .withCategory(new Car())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(0))
                .build();
        this.vehicles.add(vehicle9);

        Vehicle vehicle10 = builder.createVehicle()
                .withDescription("Gilera 250")
                .withPassengerCapacity(1)
                .withCategory(new Scooter())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(1))
                .build();
        this.vehicles.add(vehicle10);

        Vehicle vehicle11 = builder.createVehicle()
                .withDescription("Vespa 1998")
                .withPassengerCapacity(1)
                .withCategory(new Scooter())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(5))
                .build();
        this.vehicles.add(vehicle11);

        Vehicle vehicle12 = builder.createVehicle()
                .withDescription("Vento 2.0")
                .withPassengerCapacity(4)
                .withCategory(new Car())
                .withPictures(new ArrayList<>())
                .withOwner(userService.retriveAll().get(7))
                .build();
        this.vehicles.add(vehicle12);
		
	}
	private List<String> listWith(String string) {
		List<String> list = new ArrayList<String>();
		list.add(string);
		return list;
	}
}
