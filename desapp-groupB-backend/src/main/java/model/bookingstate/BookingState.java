package model.bookingstate;

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


    public Boolean getConfirmRetreatBuyer() throws NoAceptedException{
        throw new NoAceptedException();
    }

    public Boolean getConfirmRetreatSeller() throws NoAceptedException{
        throw new NoAceptedException();
    }

    public Boolean getConfirmReturnBuyer() throws NoAceptedException {
        throw new NoAceptedException();
    }

    public Boolean getConfirmReturnSeller() throws NoAceptedException {
        throw new NoAceptedException();
    }

    public void setConfirmRetreatBuyer(Boolean confirmRetreatBuyer) throws NoAceptedException{
        throw new NoAceptedException();
    }

    public void setConfirmRetreatSeller(Boolean confirmRetreatSeller) throws NoAceptedException{
        throw new NoAceptedException();
    }

    public void setConfirmReturnBuyer(Boolean confirmReturnBuyer) throws NoAceptedException {
        throw new NoAceptedException();
    }

    public void setConfirmReturnSeller(Boolean confirmReturnSeller) throws NoAceptedException {
        throw new NoAceptedException();
    }

	public abstract boolean isApproved();
}