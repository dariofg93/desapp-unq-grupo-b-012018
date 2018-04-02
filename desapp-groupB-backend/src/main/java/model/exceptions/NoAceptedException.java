package model.exceptions;

public class NoAceptedException extends Throwable {

    public NoAceptedException() {
        super("You can't confirm a reservation that was not accepted");
    }
}
