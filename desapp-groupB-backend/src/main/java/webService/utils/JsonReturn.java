package webService.utils;

public class JsonReturn {

    public static String notFoundError(String msj){
        return "{\"error\": \"" + msj + "\" }";
    }
    
    public static String success(String msj){
        return "{\"success\": \"" + msj + "\" }";
    }
}
