package ch.globaz.tmmas.rentesservice.domain.model;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalField;

public class DateUTCTest {

    @Test
    public void testUtc(){

        ZonedDateTime instant = Instant.now().atZone(ZoneId.of("Europe/Zurich"));

        ZonedDateTime instant2 = ZonedDateTime.now();

        System.out.println(instant);
        System.out.println(instant2);

        //Instant instant2 = Instant.parse("12.12.2012");


    }
}
