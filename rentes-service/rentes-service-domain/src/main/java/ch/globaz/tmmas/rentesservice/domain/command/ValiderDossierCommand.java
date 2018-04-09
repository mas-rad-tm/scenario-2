package ch.globaz.tmmas.rentesservice.domain.command;

import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@Getter
@ToString
public class ValiderDossierCommand implements DomainCommand,ValueObject<ValiderDossierCommand>{

	@NotNull
	private ZonedDateTime dateValidation;

	public ValiderDossierCommand(){}

	public ValiderDossierCommand(ZonedDateTime dateValidation){

	}

	@Override
	public boolean sameValueAs(ValiderDossierCommand other) {
		return this.equals(other);
	}
}
