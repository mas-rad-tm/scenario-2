package ch.globaz.tmmas.rentesservice.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@ToString
public class MiseAjourDossierData {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ZonedDateTime dateValidation;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ZonedDateTime dateCloture;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String raisonCloture;

	MiseAjourDossierData(){};
}
