package model.builders;

import model.booking.BookingRequest;
import model.email.MailCarpnd;

public class MailCarpndBuilder {

    private MailCarpnd buildMail;

    public MailCarpndBuilder createMail() {
        this.buildMail = new MailCarpnd();
        return this;
    }

    public MailCarpnd build() {
        return buildMail;
    }

    public MailCarpndBuilder withSubject(String anySubject) {
        this.buildMail.setSubject(anySubject);
        return this;
    }

    public MailCarpndBuilder withRequest(BookingRequest anyRequest) {
        this.buildMail.setRequest(anyRequest);
        return this;
    }
}
