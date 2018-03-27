package model.exceptions;

public class RequestNoExistException extends Exception {

    public RequestNoExistException(){
        super("A request that matches the publication has not been found");
    }
}
