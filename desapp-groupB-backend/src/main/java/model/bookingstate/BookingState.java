package model.bookingstate;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;


import model.exceptions.NoAceptedException;
import model.utils.Entity;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY , property = "concretType")
@JsonSubTypes({
    @Type(value = Approved.class, name = "APP"),

    @Type(value = AwaitingApproval.class, name = "AWA"),
    
    @Type(value = Rejected.class, name = "REJ")}
)
public abstract class BookingState extends Entity{
	
	protected String description;

	public abstract BookingState acept();

    public abstract BookingState reject();

    /** Setters and Getters **/

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @JsonIgnore
	public abstract boolean isApproved();

}