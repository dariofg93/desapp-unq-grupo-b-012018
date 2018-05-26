package model.vehicle;

import model.utils.Entity;
import model.vehicletype.Car;
import model.vehicletype.Category;
import model.vehicletype.Scooter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Vehicle extends Entity{
	
	private static final long serialVersionUID = -7816019452762349441L;

	private Category category;
    private String description;
    private List<BufferedImage> pictures; 
    private Integer passengerCapacity;
    
    // Por ahora modelamos las imaganes con BufferedImage. Si despues hay uqe cambiar la clase vemos.

    public Vehicle(Category aCategory , String aDescription, List<BufferedImage> somePictures, Integer aNumberOfPassenger) {
		this.category = aCategory;
		this.description = aDescription;
		this.pictures = somePictures;
		this.passengerCapacity = aNumberOfPassenger;
    }
    
    public Vehicle() {}
    
    public Boolean itsCategory(Category anyCategory) {
        return this.category.isSame(anyCategory);
    }

	public void addPicture(BufferedImage img) {
		pictures.add(img);
	}

	/** Setters and Getters **/
    public Category getCategory() {
		return this.category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<BufferedImage> getPictures() {
		return this.pictures;
	}
	public void setPictures(List<BufferedImage> pictures) {
		this.pictures = pictures;
	}

	public Integer getPassengerCapacity() {
		return this.passengerCapacity;
	}
	public void setPassengerCapacity(Integer passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	 
    public static Category fromCode(String description) {
        for (Category category : avaliableCategories()){
            if (category.getName().equals(description)){
                return category;
            }
        }
        throw new UnsupportedOperationException();
    }

	private static List<Category> avaliableCategories() {
		List<Category> avaliableCategories = new ArrayList<Category>();
		avaliableCategories.add(new Car());
		avaliableCategories.add(new Scooter());
		return avaliableCategories;
	}   
    
}
