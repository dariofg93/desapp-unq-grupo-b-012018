package model.bookingstate;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;


import model.exceptions.NoAceptedException;
import model.utils.Entity;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Approved.class, name = "APP"),

    @JsonSubTypes.Type(value = AwaitingApproval.class, name = "AWA"),
    
    @JsonSubTypes.Type(value = Rejected.class, name = "REJ")}
)
public abstract class BookingState extends Entity{
	
	protected String description;

	public abstract BookingState acept();

    public abstract BookingState reject();

    /** Setters and Getters **/
    @JsonIgnore
    public String getDescription() {
		return description;
	}
    @JsonIgnore
	public void setDescription(String description) {
		this.description = description;
	}

	 @JsonIgnore
    public Boolean getConfirmRetreatBuyer() throws NoAceptedException{
        throw new NoAceptedException();
    }
    @JsonIgnore
    public Boolean getConfirmRetreatSeller() throws NoAceptedException{
        throw new NoAceptedException();
    }
    @JsonIgnore
    public Boolean getConfirmReturnBuyer() throws NoAceptedException {
        throw new NoAceptedException();
    }
    @JsonIgnore
    public Boolean getConfirmReturnSeller() throws NoAceptedException {
        throw new NoAceptedException();
    }
    @JsonIgnore
    public void setConfirmRetreatBuyer(Boolean confirmRetreatBuyer) throws NoAceptedException{
        throw new NoAceptedException();
    }
    @JsonIgnore
    public void setConfirmRetreatSeller(Boolean confirmRetreatSeller) throws NoAceptedException{
        throw new NoAceptedException();
    }
    @JsonIgnore
    public void setConfirmReturnBuyer(Boolean confirmReturnBuyer) throws NoAceptedException {
        throw new NoAceptedException();
    }
    @JsonIgnore
    public void setConfirmReturnSeller(Boolean confirmReturnSeller) throws NoAceptedException {
        throw new NoAceptedException();
    }

	public abstract boolean isApproved();
}