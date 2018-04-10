package model.vehicle;

import model.vehicleType.Category;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private Category category;
    private String description;
    private List<BufferedImage> pictures; 
    private Integer passengerCapacity;
    // Por ahora modelamos las imaganes con BufferedImage. Si despues hay uqe cambiar la clase vemos.

    public Vehicle(Category aCategory , String aDescription, List<BufferedImage> somePictures, Integer aNumberOfPassenger) {
    	category = aCategory;
    	description = aDescription;
    	pictures = somePictures;
    	passengerCapacity = aNumberOfPassenger;
    }
    
    public Boolean itsCategory(Category anyCategory) {
        return this.category.isSame(category);
    }
    
    public Integer passengerCapacity() {
    	return passengerCapacity;
    }
    
    public Category category() {
    	return category;
    }
    
    public  List<BufferedImage> pictures(){
    	return pictures;
    }
    
    public String description() {
    	return description;
    }

	public void addPicture(BufferedImage img) {
		pictures.add(img);
	}
    
}
