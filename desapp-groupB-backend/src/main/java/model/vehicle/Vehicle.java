package model.vehicle;

import model.utils.Entity;
import model.vehicleType.Category;

import java.awt.image.BufferedImage;
import java.util.List;


public class Vehicle extends Entity{
	
	private static final long serialVersionUID = -7816019452762349441L;

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
    
    public Vehicle() {
    	
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
	
	
	/*		GETTERS Y SETTERS		*/

    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BufferedImage> getPictures() {
		return pictures;
	}

	public void setPictures(List<BufferedImage> pictures) {
		this.pictures = pictures;
	}

	public Integer getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(Integer passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

    
}
