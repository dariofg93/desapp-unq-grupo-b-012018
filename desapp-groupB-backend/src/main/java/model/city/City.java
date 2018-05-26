package model.city;

import java.io.Serializable;


public class City implements Serializable {

	private String name;

	public City(String name) {
		this.name = name;
	}

	public Boolean isSame(City anyCity) {
		return this.name.equals(anyCity.getName());
	}

	/** Setters and Getters **/

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Construct object from data base
	 */

	public static City fromCode(String name) {
		return new City(name);
	}
}
