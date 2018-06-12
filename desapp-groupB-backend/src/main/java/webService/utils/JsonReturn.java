package webService.utils;

public class JsonReturn {

    public static String notFoundError(String msj){
        return "{\"error\": \"" + msj + "\" }";
    }
}
