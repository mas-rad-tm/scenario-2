package ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.DateFormatter.DATE_FORMAT;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class ZonedDateDeserializer extends StdDeserializer<ZonedDateTime> {




    protected ZonedDateDeserializer() {
        super(LocalDate.class);
    }


    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT
        );

        return ZonedDateTime.parse(jp.readValueAs(String.class),formatter);
    }

}

