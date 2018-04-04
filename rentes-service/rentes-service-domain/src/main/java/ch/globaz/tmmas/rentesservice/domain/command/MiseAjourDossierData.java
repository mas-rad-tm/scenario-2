package ch.globaz.tmmas.rentesservice.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@ToString
public class MiseAjourDossierData {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate dateValidation;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate dateCloture;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String raisonCloture;

	MiseAjourDossierData(){};
}
