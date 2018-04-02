package model.email;

public class MailCarpnd {

    private String subject;
    private MailBody body;

    /** Setters and Getters **/

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(MailBody body) {
        this.body = body;
    }

    public String getSubject() {
        return this.subject;
    }

    public MailBody getBody() {
        return this.body;
    }
}
