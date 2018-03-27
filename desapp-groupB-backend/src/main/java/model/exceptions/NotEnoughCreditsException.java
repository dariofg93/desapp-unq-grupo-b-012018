package model.exceptions;

public class NotEnoughCreditsException extends Exception {

    public NotEnoughCreditsException(){
        super("You do not have enough credits to carry out the operation");
    }
}
