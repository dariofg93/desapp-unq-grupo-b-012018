package model.email;

import model.booking.BookingRequest;

public class MailCarpnd {
    private String subject;
    private BookingRequest request;

    /** Setters and Getters **/

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setRequest(BookingRequest request) {
        this.request = request;
    }
}
