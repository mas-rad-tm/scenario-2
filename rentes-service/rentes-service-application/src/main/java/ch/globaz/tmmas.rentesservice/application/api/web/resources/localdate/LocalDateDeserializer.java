package ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.DateFormatter.DATE_FORMAT;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {




    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }


    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT
        );

        return LocalDate.parse(jp.readValueAs(String.class),formatter);
    }

}

