package model.creditsAccount;

import model.exceptions.NotEnoughCreditsException;

public class CreditsAccount {

    private Double amount;

    public CreditsAccount(){
        this.amount = 0.0;
    }

    public void addCredits(Double anyCredits) {
        this.amount+= anyCredits;
    }

    public void sustractCredits(Double anyCredits) throws NotEnoughCreditsException {
        if(anyCredits <= this.amount)
            this.amount-= anyCredits;
        else
            throw new NotEnoughCreditsException();
    }

    /** Setters and Getters **/

    public Double getAmount() {
        return this.amount;
    }
}
