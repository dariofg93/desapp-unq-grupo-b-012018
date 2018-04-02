package model.email;

import java.util.List;

public class Email {

    private List<MailCarpnd> received;

    public void addMailCarpnd(MailCarpnd anyMail) {
        this.received.add(anyMail);
    }
}
