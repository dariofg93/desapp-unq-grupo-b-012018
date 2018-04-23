package webService;

public class JsonReturn<T> {

    public T notFoundError(String msj){
        return (T) ("{Error: " + msj + " }");
    }
}
