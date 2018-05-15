package model.creditsaccount;

import model.utils.Entity;

public class CreditsAccount extends Entity{

    private Double amount;

    public CreditsAccount(){
        this.amount = 0.0;
    }

	public void addCredits(Double anyCredits) {
        this.amount+= anyCredits;
    }

    public void sustractCredits(Double anyCredits) {
        if(anyCredits <= this.amount)
            this.amount-= anyCredits;
    }

    /** Setters and Getters **/

    public Double getAmount() {
        return this.amount;
    }
    
    public void setAmount(Double amount) {
  		this.amount = amount;
  	}
}
