package model.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<LocalDateTime> {
  
    private SimpleDateFormat formatter 
      = new SimpleDateFormat("yyyy-MM-dd");
 
    public CustomDateSerializer() {
        this(null);
    }
 
    @SuppressWarnings("unchecked")
    
	public CustomDateSerializer( Class t) {
        super(t);
    }
     
    @Override
    public void serialize (LocalDateTime value, JsonGenerator gen, SerializerProvider arg2)
      throws IOException, JsonProcessingException {
        gen.writeString(formatter.format(value));
    }

}
