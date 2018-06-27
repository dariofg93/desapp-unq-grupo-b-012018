package dto;

import model.booking.BookingRequest;
import model.publication.Publication;

public class PublicationRequestDto {
	
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	public BookingRequest getRequest() {
		return request;
	}
	public void setRequest(BookingRequest request) {
		this.request = request;
	}
	private Publication publication;
	private BookingRequest request;

}
