package ch.globaz.tmmas.rentesservice.infrastructure.spi.localdate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static ch.globaz.tmmas.rentesservice.domain.common.localdate.DateFormatter.DATE_FORMAT;


/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class ZonedDateDeserializer extends StdDeserializer<ZonedDateTime> {




    protected ZonedDateDeserializer() {
        super(ZonedDateTime.class);
    }


    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException{
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        String val = jp.readValuesAs(String.class).toString();

        return ZonedDateTime.parse(jp.readValueAs(String.class),formatter);
    }

}

