package model.bookingState;

import model.exceptions.NoAceptedException;

public abstract class BookingState {

    public abstract BookingState setAcepted();

    public abstract BookingState setRejected();

    /** Setters and Getters **/

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