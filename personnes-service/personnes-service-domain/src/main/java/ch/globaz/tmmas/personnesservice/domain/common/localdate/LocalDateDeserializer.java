package ch.globaz.tmmas.personnesservice.domain.common.localdate;


import ch.globaz.tmmas.personnesservice.domain.common.GlobalParams;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private final static DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(GlobalParams.DATE_FORMATTER_PATTER.value);


    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }


    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        return LocalDate.parse(jp.readValueAs(String.class),formatter);
    }

}

