package model.movements;

import model.email.MailBody;

public class MovementsOfMonth implements MailBody {

    private String history;

    public MovementsOfMonth(){
        this.history = "";
    }

    public void addToHistory(String historyFragment) {
        this.history += historyFragment + "\n";
    }

    public void cleanHistory() {
        this.history = "";
    }
}
