package model.vehicletype;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "concretType")
@JsonSubTypes({
    @Type(value = Car.class, name = "Car"),

    @Type(value = Scooter.class, name = "Scooter")}
)

public abstract class Category implements Serializable {
	
	public static Category scooter() {
		return new Scooter();
	}
	
	public static Category car() {
		return new Car();
	}


    public Boolean isSame(Category anyCategory) {
        return this.getName().equals(anyCategory.getName());
    }
    @JsonIgnore
	public abstract String getName();
	   
}
