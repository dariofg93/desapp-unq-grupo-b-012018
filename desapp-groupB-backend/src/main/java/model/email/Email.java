package model.email;

import java.util.ArrayList;
import java.util.List;

public class Email {

    private List<MailCarpnd> received;
    private String account;

    public Email(){
        this.received = new ArrayList<>();
    }

    public void addMailCarpnd(MailCarpnd anyMail) {
        this.received.add(anyMail);
    }

    /** Setters and Getters **/
    public List<MailCarpnd> getReceived() {
        return this.received;
    }

    public String getAccount() { return this.account; }
}
