package model.email;

import java.util.ArrayList;
import java.util.List;

public class Email {

    private List<MailCarpnd> received;

    public Email(){
        this.received = new ArrayList<>();
    }

    public void addMailCarpnd(MailCarpnd anyMail) {
        this.received.add(anyMail);
    }

    public List<MailCarpnd> getReceived() {
        return this.received;
    }
}
