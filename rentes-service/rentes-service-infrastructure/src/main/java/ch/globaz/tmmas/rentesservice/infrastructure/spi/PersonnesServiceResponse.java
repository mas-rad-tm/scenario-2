package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
public class PersonnesServiceResponse {

	private PersonneMoraleResource data;

	PersonnesServiceResponse(){}
}
