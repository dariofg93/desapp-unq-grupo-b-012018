package model.city;

public class City {

    private String name;

    public Boolean isSame(City anyCity) {
        return this.name.equals(anyCity.getName());
    }

    /** Setters and Getters **/

    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
}
