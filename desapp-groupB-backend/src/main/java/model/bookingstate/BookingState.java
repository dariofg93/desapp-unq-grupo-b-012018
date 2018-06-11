package model.bookingstate;

import org.codehaus.jackson.annotate.JsonIgnore;

import model.exceptions.NoAceptedException;
import model.utils.Entity;


public abstract class BookingState extends Entity{
	
	protected String description;

	public abstract BookingState setAcepted();

    public abstract BookingState setRejected();

    /** Setters and Getters **/
    
    public String getDescription() {
		return description;
	}

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