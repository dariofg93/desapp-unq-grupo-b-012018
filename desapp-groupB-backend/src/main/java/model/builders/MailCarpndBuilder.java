package model.builders;

import model.email.MailBody;
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

    public MailCarpndBuilder withRequest(MailBody anyMailBody) {
        this.buildMail.setBody(anyMailBody);
        return this;
    }
}
