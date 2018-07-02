package model.booking;

import org.codehaus.jackson.annotate.JsonIgnore;

import model.utils.Entity;

public class BookingStatus extends Entity{

	private Boolean confirmRetreatSeller;
	private Boolean confirmRetreatBuyer;
	private Boolean confirmReturnBuyer;
	private Boolean confirmReturnSeller;
	private Long request;

	public BookingStatus(Long request) {
		this.confirmRetreatSeller = false;
		this.confirmRetreatBuyer = false;
		this.confirmReturnBuyer = false;
		this.confirmReturnSeller = false;
		this.request = request;
	}
	
	public BookingStatus() {

	}

	 
	public Boolean getConfirmRetreatBuyer() {
		return this.confirmRetreatBuyer;
	}

	 
	public Boolean getConfirmRetreatSeller() {
		return this.confirmRetreatSeller;
	}

	 
	public Boolean getConfirmReturnBuyer() {
		return this.confirmReturnBuyer;
	}

	 
	public Boolean getConfirmReturnSeller() {
		return this.confirmReturnSeller;
	}

	 
	public void setConfirmRetreatBuyer(Boolean confirmRetreatBuyer) {
		this.confirmRetreatBuyer = confirmRetreatBuyer;
	}

	 
	public void setConfirmRetreatSeller(Boolean confirmRetreatSeller) {
		this.confirmRetreatSeller = confirmRetreatSeller;
	}

	 
	public void setConfirmReturnBuyer(Boolean confirmReturnBuyer) {
		this.confirmReturnBuyer = confirmReturnBuyer;
	}

	 
	public void setConfirmReturnSeller(Boolean confirmReturnSeller) {
		this.confirmReturnSeller = confirmReturnSeller;
	}
	
	@JsonIgnore
	public Long getRequest() {
		return request;
	}

	public void setRequest(Long request) {
		this.request = request;
	}
	
	
}
