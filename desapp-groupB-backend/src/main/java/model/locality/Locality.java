package model.locality;

public class Locality {

    private String name;

    public Boolean isSame(Locality anyLocality) {
        return this.name.equals(anyLocality.getName());
    }

    /** Setters and Getters **/

    public String getName() {
        return name;
    }
}
