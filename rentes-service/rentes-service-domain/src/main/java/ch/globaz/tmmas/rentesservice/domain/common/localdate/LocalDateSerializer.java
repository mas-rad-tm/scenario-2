package ch.globaz.tmmas.rentesservice.domain.common.localdate;

import ch.globaz.tmmas.rentesservice.domain.common.GlobalParams;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class LocalDateSerializer extends StdSerializer<LocalDate> {

    private final static DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(GlobalParams.DATE_FORMATTER_PATTER.value);


    public LocalDateSerializer(){
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException {

        gen.writeString(value.format(formatter));
    }
}