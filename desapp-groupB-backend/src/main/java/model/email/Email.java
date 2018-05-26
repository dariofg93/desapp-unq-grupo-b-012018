package model.email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Email implements Serializable {

	private List<MailCarpnd> received;
	private String accountName;

	public Email() {
		this.received = new ArrayList<>();
	}

	public Email(String accountName) {
		this.received = new ArrayList<>();
		this.accountName = accountName;
	}

	public void addMailCarpnd(MailCarpnd anyMail) {
		this.received.add(anyMail);
	}

	/** Setters and Getters **/
	public List<MailCarpnd> getReceived() {
		return this.received;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String anAccountName) {
		this.accountName = anAccountName;
	}
	
	public static Email fromCode( String emailAdrressName) {
		return new Email(emailAdrressName);
	}

}
