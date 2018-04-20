package ch.globaz.tmmas.rentesservice.infrastructure.spi.localdate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static ch.globaz.tmmas.rentesservice.domain.common.localdate.DateFormatter.DATE_FORMAT;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class ZonedDateSerializer extends StdSerializer<ZonedDateTime> {


    public ZonedDateSerializer(){
        super(ZonedDateTime.class);
    }

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        gen.writeString(value.format(formatter));
    }
}