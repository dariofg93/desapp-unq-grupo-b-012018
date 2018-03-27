package model.exceptions;

public class BannedException extends Exception {

    public BannedException(String str){
        super("You are banned: " + str);
    }
}
