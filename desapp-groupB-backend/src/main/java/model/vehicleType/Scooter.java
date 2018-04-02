package model.vehicleType;

public class Scooter extends Category {

    public Scooter(){
        this.name = "Scooter";
    }

    @Override
    public Boolean isSame(Category anyCategory) {
        return this.name.equals(anyCategory.getName());
    }
}
